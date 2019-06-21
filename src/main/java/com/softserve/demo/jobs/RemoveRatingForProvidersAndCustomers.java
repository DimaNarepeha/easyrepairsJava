package com.softserve.demo.jobs;

import com.softserve.demo.model.Feedback;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.repository.FeedbackRepository;
import com.softserve.demo.repository.ProviderRepository;
import com.softserve.demo.util.Constant;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RemoveRatingForProvidersAndCustomers {

    private final ProviderRepository providerRepository;
    private final FeedbackRepository feedbackRepository;
    private final CustomerRepository customerRepository;

    public RemoveRatingForProvidersAndCustomers(ProviderRepository providerRepository, FeedbackRepository feedbackRepository, CustomerRepository customerRepository) {
        this.providerRepository = providerRepository;
        this.feedbackRepository = feedbackRepository;
        this.customerRepository = customerRepository;
    }

    private Double recalculateRating(Integer userId) {
        Double sum = 0.;
        Integer count;
        for (Feedback f : feedbackRepository.findAllActiveFeedbacksByUserId(userId)
        ) {
            sum += f.getRating();
        }
        count = feedbackRepository.findAllActiveFeedbacksByUserId(userId).size();
        return sum / count;

    }

    @Scheduled(cron = "0 0 0 * * *")
    public void removeRatingForProviders() {
        providerRepository.findAll().stream().forEach(provider -> {
            if (feedbackRepository.findAllActiveFeedbacksByUserId(provider.getUser().getId()).isEmpty()) {
                provider.setRaiting(Constant.DEFAULT_RATING);
                providerRepository.save(provider);
            } else {
                provider.setRaiting(recalculateRating(provider.getUser().getId()));
                providerRepository.save(provider);
            }
        });
    }

    @Scheduled(cron = "0 40 16 * * *")
    public void removeRatingForCustomers() {
        customerRepository.findAll().stream().forEach(customer -> {
            if (feedbackRepository.findAllActiveFeedbacksByUserId(customer.getUser().getId()).isEmpty()) {
                customer.setRating(Constant.DEFAULT_RATING);
                customerRepository.save(customer);
            } else {
                customer.setRating(recalculateRating(customer.getUser().getId()));
                customerRepository.save(customer);
            }
        });
    }
}
