package com.softwaretesting.testing.customerRegistration.service;

import com.softwaretesting.testing.dao.CustomerRepository;
import com.softwaretesting.testing.exception.BadRequestException;
import com.softwaretesting.testing.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerRegistrationServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerRegistrationService customerRegistrationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        customerRegistrationService = new CustomerRegistrationService(customerRepository);
    }

    @Test
    void registerNewCustomerWithPhoneNumber() {
        Customer customer = new Customer(123L, "Mickey Mouse", "MM", "+4912345678900");
        when(customerRepository.selectCustomerByPhoneNumber(customer.getPhoneNumber())).thenReturn(Optional.empty());
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer newCustomer = customerRegistrationService.registerNewCustomer(customer);

        assertNotNull(newCustomer);
        assertEquals(customer.getName(), newCustomer.getName());
        assertEquals(customer.getPhoneNumber(), newCustomer.getPhoneNumber());
        verify(customerRepository, times(1)).selectCustomerByPhoneNumber(customer.getPhoneNumber());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void registerExistingCustomer() {
        Customer customer = new Customer(123L, "Mickey Mouse", "MM", "+4912345678900");
        Customer newCustomer = new Customer(123L, "Mickey Mouse", "MM", "+4912345678900");
        when(customerRepository.selectCustomerByPhoneNumber(customer.getPhoneNumber())).thenReturn(Optional.of(customer));
        when(customerRepository.selectCustomerByPhoneNumber(customer.getName())).thenReturn(Optional.of(customer));
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            customerRegistrationService.registerNewCustomer(newCustomer);
        });
        assertEquals("You are already registered", exception.getMessage());
    }

    @Test
    void registerWithSamePhoneNumber(){
        String phoneNumber = "+4912345";
        Customer newCustomer = new Customer(1L, "Test1","Test1", phoneNumber);
        Customer existingCustomer = new Customer(12L, "Test", "Test", phoneNumber);
        when(customerRepository.selectCustomerByPhoneNumber(existingCustomer.getPhoneNumber())).thenReturn(Optional.of(existingCustomer));
        BadRequestException exception = assertThrows(BadRequestException.class, ()->{
            customerRegistrationService.registerNewCustomer(newCustomer);
        });
        assertEquals("Phone Number "+phoneNumber+" taken", exception.getMessage());
    }

}