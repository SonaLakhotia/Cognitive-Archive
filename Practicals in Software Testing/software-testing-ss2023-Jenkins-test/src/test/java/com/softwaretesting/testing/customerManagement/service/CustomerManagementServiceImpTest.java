package com.softwaretesting.testing.customerManagement.service;

import com.softwaretesting.testing.dao.CustomerRepository;
import com.softwaretesting.testing.exception.BadRequestException;
import com.softwaretesting.testing.model.Customer;
import com.softwaretesting.testing.validator.CustomerValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.softwaretesting.testing.exception.CustomerNotFoundException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerManagementServiceImpTest{

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerValidator customerValidator;

    @InjectMocks
    private CustomerManagementServiceImp customerManagementService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void listTest() {
        Customer c1 = new Customer(1L, "Test1", "T1", "12345678");
        Customer c2 = new Customer(2L, "Test2", "T2", "1237895");
        when(customerRepository.findAll()).thenReturn(
                Stream.of(c1, c2).collect(Collectors.toSet())
        );
        Collection<Customer> customers = customerManagementService.list();
        Set<Customer> expectedCustomers = Stream.of(c1, c2).collect(Collectors.toSet());

        assertEquals(expectedCustomers, customers);
    }

    @Test
    void findByUserNameTest() {
        String userName = "test_user";
        Optional<String> object = Optional.of("value");
        Customer customer = new Customer(1L, "test_user","Test User","12345678");

        when(customerRepository.findByUserName(userName)).thenReturn(Optional.of(customer));

        customerValidator.validate404(object,"User-Name", userName);

        Customer result = customerManagementService.findByUserName(userName);
        Assertions.assertEquals(customer, result);
    }


    @Test
    void findByIdTest() {
        Long id = 122L;
        Optional<String> object = Optional.of("value");
        Customer customer = new Customer(1L, "test_user","Test User","12345678");

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        customerValidator.validate404(object,"id", String.valueOf(id));

        Customer result = customerManagementService.findById(id);
        Assertions.assertEquals(customer, result);
    }

    @Test
    void selectCustomerByPhoneNumberTest() {
        String phoneNum = "123455";
        Optional<String> object = Optional.of("value");
        Customer customer = new Customer(1L, "test_user","Test User","12345678");

        when(customerRepository.selectCustomerByPhoneNumber(phoneNum)).thenReturn(Optional.of(customer));

        customerValidator.validate404(object,"phoneNumber", String.valueOf(phoneNum));

        Customer result = customerManagementService.selectCustomerByPhoneNumber(phoneNum);
        Assertions.assertEquals(customer, result);

    }

    @Test
    void deleteCustomerTest() {
        Long customerId = 1L;
        doReturn(true).when(customerRepository).existsById(customerId);
        customerManagementService.delete(customerId);
        verify(customerRepository, times(1)).deleteById(customerId);
    }

    @Test
    void customerNotFoundTest(){
        Long customerId = 1L;
        doReturn(false).when(customerRepository).existsById(customerId);
        assertThrows(CustomerNotFoundException.class, () -> customerManagementService.delete(customerId));
    }

    @Test
    void addCustomerSuccessTest() {
        Customer customer = new Customer(1l, "Test", "TT", "1234579");
        when(customerRepository.selectCustomerByPhoneNumber(customer.getPhoneNumber())).thenReturn(Optional.empty());
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer result = customerManagementService.addCustomer(customer);
        assertEquals(customer, result);
    }

    @Test
    void customerFailedTest(){
        String ph = "123457";
        Customer customer = new Customer(1l, "Test", "TT", ph);
        when(customerRepository.selectCustomerByPhoneNumber(customer.getPhoneNumber())).thenReturn(Optional.of(customer));
        BadRequestException exception = assertThrows(BadRequestException.class,() ->{
            customerManagementService.addCustomer(customer);
        });
        assertEquals("Phone Number "+ph+" taken", exception.getMessage());

    }

    @Test
    void saveAllTest() {
        List<Customer> customers = Arrays.asList(
                new Customer(12L, "TT", "TT", "1234"),
                new Customer(1L, "TST", "TST", "12345")
        );
        when(customerRepository.saveAll(customers)).thenReturn(customers);
        Collection<Customer> savedCustomers = customerManagementService.saveAll(customers);
        verify(customerRepository, times(1)).saveAll(customers);
        assertEquals(customers.size(), savedCustomers.size());
        for (Customer customer : customers) {
            assertEquals(customer, savedCustomers.stream().filter(c -> c.getId().equals(customer.getId())).findFirst().get());
        }
    }
}