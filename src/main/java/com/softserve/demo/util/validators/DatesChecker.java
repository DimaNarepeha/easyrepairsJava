package com.softserve.demo.util.validators;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class DatesChecker implements ConstraintValidator<DatesChecks, StartEndDates> {

   @Override
   public void initialize(DatesChecks constraintAnnotation) {
      log.debug("Checking dates...");
   }

   @Override
   public boolean isValid(StartEndDates startEndDates, ConstraintValidatorContext constraintValidatorContext) {
      return startEndDates.getStartDate().isBefore(startEndDates.getEndDate())
              || startEndDates.getStartDate().isEqual(startEndDates.getEndDate());
   }
}
