package com.softserve.demo.service.impl;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class СustomerServiceImpl {
    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerDTO customer;

    @Test(expected = NotFoundException.class)
    public void givenFindMethodMockedWhenDeleteInvokedThenNotFoundExceptionIsThrown() {
        Mockito.when(customerRepository.findById(1)).thenThrow(NotFoundException.class);
       customerService.deleteCustomer(1);
    }

    @Test(expected = NotFoundException.class)
    public void givenFindMethodMockedWhenUpdateInvokedThenNotFoundExceptionIsThrown() {
        Mockito.when(customerRepository.findById(1)).thenThrow(NotFoundException.class);
       customerService.updateCustomer(1,customer);

    }



}
