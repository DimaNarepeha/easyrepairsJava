package com.softserve.demo.util.validators;

import com.softserve.demo.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class OrderDatesChecker implements ConstraintValidator<OrderDateCheck, OrderDTO> {

   @Override
   public void initialize(OrderDateCheck constraintAnnotation) {
      log.debug("Checking order dates...");
   }

   @Override
   public boolean isValid(OrderDTO orderDTO, ConstraintValidatorContext constraintValidatorContext) {
      return orderDTO.getStartDate().isBefore(orderDTO.getEndDate())
              || orderDTO.getStartDate().isEqual(orderDTO.getEndDate());
   }
}
