package com.softserve.demo.util.validators;

import com.softserve.demo.dto.OfferDTO;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class OfferDatesChecker implements ConstraintValidator<OfferDatesCheck, OfferDTO> {

    @Override
    public void initialize(OfferDatesCheck constraintAnnotation) {
        log.debug("Checking offer dates...");
    }

    @Override
    public boolean isValid(OfferDTO offerDTO, ConstraintValidatorContext constraintValidatorContext) {
        return offerDTO.getStartDate().isBefore(offerDTO.getEndDate())
                || offerDTO.getStartDate().isEqual(offerDTO.getEndDate());
    }
}
