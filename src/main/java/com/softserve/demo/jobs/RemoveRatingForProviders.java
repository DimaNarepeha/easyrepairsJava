package com.softserve.demo.jobs;

import com.softserve.demo.model.Feedback;
import com.softserve.demo.repository.FeedbackRepository;
import com.softserve.demo.repository.ProviderRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveRatingForProviders {

    private final ProviderRepository providerRepository;
    private final FeedbackRepository feedbackRepository;


    public RemoveRatingForProviders(ProviderRepository providerRepository, FeedbackRepository feedbackRepository) {
        this.providerRepository = providerRepository;
        this.feedbackRepository = feedbackRepository;
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void removeRating() {
        providerRepository.findAll().stream().forEach(provider -> {
            Double sum = 0.;
            Integer count;
            for (Feedback f : feedbackRepository.findActiveFeedbackByUserId(provider.getUser().getId())
            ) {
                sum += f.getRating();
            }
            if (feedbackRepository.findActiveFeedbackByUserId(provider.getUser().getId()).size() == 0) {
                provider.setRaiting(2);
                providerRepository.save(provider);
            } else {
                count = feedbackRepository.findActiveFeedbackByUserId(provider.getUser().getId()).size();
                provider.setRaiting(sum / count);
                providerRepository.save(provider);
            }
        });
    }
}
