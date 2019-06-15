package com.softserve.demo.util.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = OfferDatesChecker.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OfferDatesCheck {
    String message() default "startDate must be before endDate";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
