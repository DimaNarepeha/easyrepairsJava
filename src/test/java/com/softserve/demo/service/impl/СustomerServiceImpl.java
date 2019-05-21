package com.softserve.demo.service.impl;

import com.softserve.demo.dto.CustomerDTO;
import com.softserve.demo.exceptions.NotFoundException;
import com.softserve.demo.repository.CustomerRepository;
import org.junit.Assert;
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
    public void givenCountMethodMocked_WhenCountInvoked_ThenMockValueReturned() {
        Mockito.when(customerRepository.existsById(1)).thenReturn(false);
       customerService.deleteCustomer(1);

    }

    @Test(expected = NotFoundException.class)
    public void givenCountMethodMocked_WhenCountInvoked_ThenMockValueReturned1() {
        Mockito.when(customerRepository.existsById(1)).thenReturn(false);
        Assert.assertEquals(customerService.updateCustomer(1,customer),null);

    }

}
