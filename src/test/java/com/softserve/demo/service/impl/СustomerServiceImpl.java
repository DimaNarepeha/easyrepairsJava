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

    @Spy
    @InjectMocks
    CustomerServiceImpl customerService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    CustomerDTO customer;

    @Test(expected = NotFoundException.class)
    public void givenExistsMethodMockedWhenDeleteInvokedThenNotFoundExceptionIsThrown() {
        Mockito.when(customerRepository.existsById(1)).thenReturn(false);
       customerService.deleteCustomer(1);
    }

    @Test(expected = NotFoundException.class)
    public void givenExistsMethodMockedWhenUpdateInvokedThenNotFoundExceptionIsThrown() {
        Mockito.when(customerRepository.existsById(1)).thenReturn(false);
       customerService.updateCustomer(1,customer);

    }



}
