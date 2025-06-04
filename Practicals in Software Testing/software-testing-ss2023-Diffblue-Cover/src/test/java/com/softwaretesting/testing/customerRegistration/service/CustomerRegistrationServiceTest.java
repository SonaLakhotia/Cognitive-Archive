package com.softwaretesting.testing.customerRegistration.service;

import com.softwaretesting.testing.dao.CustomerRepository;
import com.softwaretesting.testing.exception.BadRequestException;
import com.softwaretesting.testing.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CustomerRegistrationService.class})
@ExtendWith(SpringExtension.class)
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

    /**
     * Method under test: {@link CustomerRegistrationService#registerNewCustomer(Customer)}
     */
    @Test
    void testRegisterNewCustomer() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");

        Customer customer1 = new Customer();
        customer1.setId(123L);
        customer1.setName("Name");
        customer1.setPhoneNumber("4105551212");
        customer1.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer1);
        when(customerRepository.save((Customer) any())).thenReturn(customer);
        when(customerRepository.selectCustomerByPhoneNumber((String) any())).thenReturn(ofResult);

        Customer customer2 = new Customer();
        customer2.setId(123L);
        customer2.setName("Name");
        customer2.setPhoneNumber("4105551212");
        customer2.setUserName("janedoe");
        assertThrows(IllegalStateException.class, () -> customerRegistrationService.registerNewCustomer(customer2));
        verify(customerRepository).selectCustomerByPhoneNumber((String) any());
    }

    /**
     * Method under test: {@link CustomerRegistrationService#registerNewCustomer(Customer)}
     */
    @Test
    void testRegisterNewCustomer2() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        Customer customer1 = mock(Customer.class);
        when(customer1.getName()).thenReturn("foo");
        doNothing().when(customer1).setId((Long) any());
        doNothing().when(customer1).setName((String) any());
        doNothing().when(customer1).setPhoneNumber((String) any());
        doNothing().when(customer1).setUserName((String) any());
        customer1.setId(123L);
        customer1.setName("Name");
        customer1.setPhoneNumber("4105551212");
        customer1.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer1);
        when(customerRepository.save((Customer) any())).thenReturn(customer);
        when(customerRepository.selectCustomerByPhoneNumber((String) any())).thenReturn(ofResult);

        Customer customer2 = new Customer();
        customer2.setId(123L);
        customer2.setName("Name");
        customer2.setPhoneNumber("4105551212");
        customer2.setUserName("janedoe");
        assertThrows(BadRequestException.class, () -> customerRegistrationService.registerNewCustomer(customer2));
        verify(customerRepository).selectCustomerByPhoneNumber((String) any());
        verify(customer1).getName();
        verify(customer1).setId((Long) any());
        verify(customer1).setName((String) any());
        verify(customer1).setPhoneNumber((String) any());
        verify(customer1).setUserName((String) any());
    }

    /**
     * Method under test: {@link CustomerRegistrationService#registerNewCustomer(Customer)}
     */
    @Test
    void testRegisterNewCustomer3() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        when(customerRepository.save((Customer) any())).thenReturn(customer);
        when(customerRepository.selectCustomerByPhoneNumber((String) any())).thenReturn(Optional.empty());
        Customer customer1 = mock(Customer.class);
        when(customer1.getName()).thenReturn("Name");
        doNothing().when(customer1).setId((Long) any());
        doNothing().when(customer1).setName((String) any());
        doNothing().when(customer1).setPhoneNumber((String) any());
        doNothing().when(customer1).setUserName((String) any());
        customer1.setId(123L);
        customer1.setName("Name");
        customer1.setPhoneNumber("4105551212");
        customer1.setUserName("janedoe");

        Customer customer2 = new Customer();
        customer2.setId(123L);
        customer2.setName("Name");
        customer2.setPhoneNumber("4105551212");
        customer2.setUserName("janedoe");
        assertSame(customer, customerRegistrationService.registerNewCustomer(customer2));
        verify(customerRepository).save((Customer) any());
        verify(customerRepository).selectCustomerByPhoneNumber((String) any());
        verify(customer1).setId((Long) any());
        verify(customer1).setName((String) any());
        verify(customer1).setPhoneNumber((String) any());
        verify(customer1).setUserName((String) any());
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
    void registerWithSamePhoneNumber() {
        String phoneNumber = "+4912345";
        Customer newCustomer = new Customer(1L, "Test1", "Test1", phoneNumber);
        Customer existingCustomer = new Customer(12L, "Test", "Test", phoneNumber);
        when(customerRepository.selectCustomerByPhoneNumber(existingCustomer.getPhoneNumber())).thenReturn(Optional.of(existingCustomer));
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            customerRegistrationService.registerNewCustomer(newCustomer);
        });
        assertEquals("Phone Number " + phoneNumber + " taken", exception.getMessage());
    }

}