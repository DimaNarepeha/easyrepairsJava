package com.softserve.demo.service.impl;

import com.softserve.demo.model.Customer;
import com.softserve.demo.repository.CustomerRepository;
import com.softserve.demo.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
@RunWith(MockitoJUnitRunner.class)
public class СustomerServiceImpl {

    @Spy
    @InjectMocks
    CustomerServiceImpl customerService;
    @Mock
    CustomerRepository customerRepository;
    @Mock
    Customer customer;

    @Test
    public void givenCountMethodMocked_WhenCountInvoked_ThenMockValueReturned() {
        Mockito.when(customerRepository.existsById(1)).thenReturn(false);
        Assert.assertEquals(customerService.deleteCustomer(1),null);

    }

    @Test
    public void givenCountMethodMocked_WhenCountInvoked_ThenMockValueReturned1() {
        Mockito.when(customerRepository.existsById(1)).thenReturn(false);
        Assert.assertEquals(customerService.updateCustomer(1,customer),null);

    }








}
