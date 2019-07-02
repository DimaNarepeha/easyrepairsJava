package com.softserve.demo.jobs;

import com.softserve.demo.model.Notification;
import com.softserve.demo.model.Offer;
import com.softserve.demo.repository.OfferRepository;
import com.softserve.demo.service.EmailService;
import com.softserve.demo.service.NotificationService;
import com.softserve.demo.util.Constant;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@EnableScheduling
@Component
public class RemoveOfferJob {
    private OfferRepository offerRepository;
    private EmailService emailService;
    private NotificationService notificationService;

    public RemoveOfferJob(OfferRepository offerRepository, EmailService emailService,
                          NotificationService notificationService) {
        this.offerRepository = offerRepository;
        this.emailService = emailService;
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void removeOffers() {
        List<Offer> allOffers = offerRepository.findAll();
        allOffers.forEach(offer -> {
            if (isOfferDateExpired(offer)) {//todo CHECK!!!!
                allOffers.remove(offer);
                sendEmailToUser(offer);
                sendNotificationToUser(offer);
            }
        });
        offerRepository.saveAll(allOffers);
    }

    private void sendNotificationToUser(Offer offer) {
        Notification notification = new Notification();
        notification.setHeader(Constant.OFFER_WAS_DELETED);
        notification.setMessage(Constant.AUTOMATICALLY_DELETED);
        notificationService.notifyByUserId(offer.getCustomer().getUser().getId(), notification);
    }

    private void sendEmailToUser(Offer offer) {
        emailService.sendEmailTo(offer.getCustomer().getUser().getEmail(), Constant.OFFER_WAS_DELETED,
                Constant.AUTOMATICALLY_DELETED);
    }

    private boolean isOfferDateExpired(Offer offer) {
        return LocalDate.now().isBefore(offer.getRemoveDate());
    }



}
