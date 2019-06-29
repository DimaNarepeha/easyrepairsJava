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
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ContractMaker {

    private static final int QR_SIZE = 75;
    private static final float ABSOLUTE_Y_POSITION = 30f;
    private static final float CUSTOMER_SIGNATURE_X_POSITION = 440f;
    private static final float PROVIDER_SIGNATURE_X_POSITION = 150f;

    @Value("${contract.template}")
    private String pathToContractTemplate;
    @Value("${contract.folder}")
    private String contractsFolder;
    @Value("${contract.path}")
    private String pathToContracts;

    public void createContract(Order order) throws IOException, DocumentException {
        if (!createFolderIfNoExist(contractsFolder)) {
            throw new NotFoundException("Contract folder not found");
        }

        String contractName = getContractName(order);
        FileInputStream fileInputStream = new FileInputStream(new ClassPathResource(pathToContractTemplate).getFile());
        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathToContracts + contractName));
        PdfReader pdfReader = new PdfReader(fileInputStream);
        PdfStamper pdfDocument = new PdfStamper(pdfReader, fileOutputStream);
        setFields(order, pdfDocument.getAcroFields());
        PdfContentByte content = pdfDocument.getOverContent(pdfReader.getNumberOfPages());
        signContract(content, order);
        pdfDocument.setFormFlattening(true);

        pdfDocument.close();
        pdfReader.close();
        fileInputStream.close();
        fileOutputStream.close();
        order.setContractName(contractName);
    }

    private void signContract(PdfContentByte content, Order order) throws DocumentException {
        String customerSignature = order.getCustomer().getUser().getUsername();
        String providerSignature = order.getProvider().getUser().getUsername();
        content.addImage(getQRImage(customerSignature, CUSTOMER_SIGNATURE_X_POSITION));
        content.addImage(getQRImage(providerSignature, PROVIDER_SIGNATURE_X_POSITION));
    }

    private Image getQRImage(String userSignature, Float absoluteX) throws BadElementException {
        Image qrUserSignature = new BarcodeQRCode(userSignature, QR_SIZE, QR_SIZE, null).getImage();
        qrUserSignature.setAbsolutePosition(absoluteX, ABSOLUTE_Y_POSITION);
        return qrUserSignature;
    }

    private void setFields(Order order, AcroFields fields) throws IOException, DocumentException {
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

    private String getContractName(Order order) {
        return (order.getCustomer().getUser().getId() + "_"
                + order.getProvider().getUser().getId() + "_" + order.getId() + ".pdf");
    }

    private boolean createFolderIfNoExist(String folderName) {
        File newDirectory = new File(folderName);
        return (newDirectory.mkdir() || newDirectory.isDirectory());
    }
}
