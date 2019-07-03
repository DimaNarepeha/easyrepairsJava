package com.softserve.demo.util;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.model.Order;
import com.softserve.demo.model.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ContractMaker {

    private static final int QR_SIZE = 75;
    private static final float ABSOLUTE_Y_POSITION = 30f;
    private static final float CUSTOMER_SIGNATURE_X_POSITION = 440f;
    private static final float PROVIDER_SIGNATURE_X_POSITION = 150f;
    private static final String PATH = System.getProperty("user.dir");
    private static final String SEPARATOR = System.getProperty("file.separator");

    @Value("${contract.template}")
    private String contractTemplateFile;
    @Value("${contract.folder}")
    private String contractsFolder;

    public void createContract(Order order) {
        createContractFolder();
        String contractFileName = getContractFileName(order);
        log.info("Create contract with name: [{}]", contractFileName);
        PdfReader pdfReader = null;
        PdfStamper pdfDocument = null;
        try (
                FileInputStream fileInputStream = new FileInputStream(getContractFile(contractTemplateFile));
                FileOutputStream fileOutputStream = new FileOutputStream(getContractFile(contractFileName))
        ) {
            pdfReader = new PdfReader(fileInputStream);
            pdfDocument = new PdfStamper(pdfReader, fileOutputStream);
            setContractFields(order, pdfDocument.getAcroFields());
            PdfContentByte content = pdfDocument.getOverContent(pdfReader.getNumberOfPages());
            signContract(content, order);
            pdfDocument.setFormFlattening(true);

            pdfDocument.close();
            pdfReader.close();

        } catch (IOException | DocumentException e) {
            throw new NotFoundException("Can't create contract file");
        } finally {
            try {
                if (pdfDocument != null) {
                    pdfDocument.close();
                }
            } catch (DocumentException | IOException e) {
                log.error(e.getMessage());
            }

            pdfReader.close();
        }

        order.setContractName(contractFileName);
    }

    private void signContract(PdfContentByte content, Order order) throws DocumentException {
        log.info("Sign contract with id: [{}]", order.getId());
        String customerSignature = order.getCustomer().getUser().getSignature();
        String providerSignature = order.getProvider().getUser().getSignature();
        content.addImage(getQRImage(customerSignature, CUSTOMER_SIGNATURE_X_POSITION));
        content.addImage(getQRImage(providerSignature, PROVIDER_SIGNATURE_X_POSITION));
    }

    private Image getQRImage(String userSignature, Float absoluteX) throws BadElementException {
        Image qrUserSignature = new BarcodeQRCode(userSignature, QR_SIZE, QR_SIZE, null).getImage();
        qrUserSignature.setAbsolutePosition(absoluteX, ABSOLUTE_Y_POSITION);
        return qrUserSignature;
    }

    private void setContractFields(Order order, AcroFields fields) throws IOException, DocumentException {
        fields.setField("provider.name", order.getProvider().getName());
        fields.setField("customer.firstName", order.getCustomer().getFirstName());
        fields.setField("customer.lastName", order.getCustomer().getLastName());
        fields.setField("timeRequirement", order.getTimeRequirement());
        fields.setField("startDate", order.getStartDate().toString());
        fields.setField("endDate", order.getEndDate().toString());
        fields.setField("description", order.getDescription());
        fields.setField("price", order.getPrice());
        fields.setField("extraDetails", order.getExtraDetails());
        fields.setField("services", order.getServices().stream().map(
                Service::getServiceName).collect(Collectors.joining("\n")));
    }

    private String getContractFileName(Order order) {
        return (order.getCustomer().getUser().getId() + "_"
                + order.getProvider().getUser().getId() + "_" + order.getId() + ".pdf");
    }

    private void createContractFolder() {
        try {
            Files.createDirectories(getAbsolutePathToContractFolder());
        } catch (IOException e) {
            throw new NotFoundException("Contract folder not found");
        }
    }

    private Path getAbsolutePathToContractFolder() {
        return Paths.get(PATH + SEPARATOR + contractsFolder).toAbsolutePath().normalize();
    }

    private File getContractFile(String contractFileName) {
        return getAbsolutePathToContractFolder().resolve(contractFileName).toFile();
    }
}
