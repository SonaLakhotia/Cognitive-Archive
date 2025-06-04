package com.softwaretesting.testing.customerManagement.service;

import com.softwaretesting.testing.dao.CustomerRepository;
import com.softwaretesting.testing.exception.BadRequestException;
import com.softwaretesting.testing.model.Customer;
import com.softwaretesting.testing.validator.CustomerValidator;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.softwaretesting.testing.exception.CustomerNotFoundException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CustomerManagementServiceImp.class})
@ExtendWith(SpringExtension.class)
class CustomerManagementServiceImpTest {

    @Autowired
    private CustomerManagementServiceImp customerManagementServiceImp;

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

    /**
     * Method under test: {@link CustomerManagementServiceImp#list()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testList() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.util.Objects.requireNonNull(Objects.java:208)
        //       at java.util.stream.StreamSupport.stream(StreamSupport.java:68)
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.list(CustomerManagementServiceImp.java:30)
        //   In order to prevent list()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   list().
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.findAll()).thenReturn((Iterable<Customer>) mock(Iterable.class));
        customerManagementServiceImp.list();
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#list()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testList2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.softwaretesting.testing.exception.CustomerNotFoundException: Msg
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.list(CustomerManagementServiceImp.java:30)
        //   In order to prevent list()
        //   from throwing CustomerNotFoundException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   list().
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.findAll()).thenReturn((Iterable<Customer>) mock(Iterable.class));
        new CustomerNotFoundException("Msg");
        customerManagementServiceImp.list();
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#list()}
     */
    @Test
    void testList3() {
        when(customerRepository.findAll()).thenThrow(new CustomerNotFoundException("Msg"));
        assertThrows(CustomerNotFoundException.class, () -> customerManagementServiceImp.list());
        verify(customerRepository).findAll();
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#list()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testList4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.util.Objects.requireNonNull(Objects.java:208)
        //       at java.util.stream.StreamSupport.stream(StreamSupport.java:68)
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.list(CustomerManagementServiceImp.java:30)
        //   In order to prevent list()
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   list().
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.findAll()).thenReturn((Iterable<Customer>) mock(Iterable.class));
        customerManagementServiceImp.list();
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#list()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testList5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.softwaretesting.testing.exception.CustomerNotFoundException: Msg
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.list(CustomerManagementServiceImp.java:30)
        //   In order to prevent list()
        //   from throwing CustomerNotFoundException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   list().
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.findAll()).thenReturn((Iterable<Customer>) mock(Iterable.class));
        new CustomerNotFoundException("Msg");
        customerManagementServiceImp.list();
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#list()}
     */
    @Test
    void testList6() {
        when(customerRepository.findAll()).thenThrow(new CustomerNotFoundException("Msg"));
        assertThrows(CustomerNotFoundException.class, () -> customerManagementServiceImp.list());
        verify(customerRepository).findAll();
    }

    @Test
    void findByUserNameTest() {
        String userName = "test_user";
        Optional<String> object = Optional.of("value");
        Customer customer = new Customer(1L, "test_user", "Test User", "12345678");

        when(customerRepository.findByUserName(userName)).thenReturn(Optional.of(customer));

        customerValidator.validate404(object, "User-Name", userName);

        Customer result = customerManagementService.findByUserName(userName);
        Assertions.assertEquals(customer, result);
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#findByUserName(String)}
     */
    @Test
    void testFindByUserName() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findByUserName((String) any())).thenReturn(ofResult);
        doNothing().when(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
        assertSame(customer, customerManagementServiceImp.findByUserName("janedoe"));
        verify(customerRepository).findByUserName((String) any());
        verify(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#findByUserName(String)}
     */
    @Test
    void testFindByUserName2() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findByUserName((String) any())).thenReturn(ofResult);
        doThrow(new CustomerNotFoundException("User-Name")).when(customerValidator)
                .validate404((Optional<Object>) any(), (String) any(), (String) any());
        assertThrows(CustomerNotFoundException.class, () -> customerManagementServiceImp.findByUserName("janedoe"));
        verify(customerRepository).findByUserName((String) any());
        verify(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#findByUserName(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindByUserName3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.findByUserName(CustomerManagementServiceImp.java:41)
        //   In order to prevent findByUserName(String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   findByUserName(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.findByUserName((String) any())).thenReturn(Optional.empty());
        doNothing().when(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
        customerManagementServiceImp.findByUserName("janedoe");
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#findByUserName(String)}
     */
    @Test
    void testFindByUserName4() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findByUserName((String) any())).thenReturn(ofResult);
        doNothing().when(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
        assertSame(customer, customerManagementServiceImp.findByUserName("janedoe"));
        verify(customerRepository).findByUserName((String) any());
        verify(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#findByUserName(String)}
     */
    @Test
    void testFindByUserName5() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findByUserName((String) any())).thenReturn(ofResult);
        doThrow(new CustomerNotFoundException("User-Name")).when(customerValidator)
                .validate404((Optional<Object>) any(), (String) any(), (String) any());
        assertThrows(CustomerNotFoundException.class, () -> customerManagementServiceImp.findByUserName("janedoe"));
        verify(customerRepository).findByUserName((String) any());
        verify(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#findByUserName(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindByUserName6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.findByUserName(CustomerManagementServiceImp.java:41)
        //   In order to prevent findByUserName(String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   findByUserName(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.findByUserName((String) any())).thenReturn(Optional.empty());
        doNothing().when(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
        customerManagementServiceImp.findByUserName("janedoe");
    }


    @Test
    void findByIdTest() {
        Long id = 122L;
        Optional<String> object = Optional.of("value");
        Customer customer = new Customer(1L, "test_user", "Test User", "12345678");

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        customerValidator.validate404(object, "id", String.valueOf(id));

        Customer result = customerManagementService.findById(id);
        Assertions.assertEquals(customer, result);
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#findById(Long)}
     */
    @Test
    void testFindById() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Long) any())).thenReturn(ofResult);
        doNothing().when(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
        assertSame(customer, customerManagementServiceImp.findById(123L));
        verify(customerRepository).findById((Long) any());
        verify(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#findById(Long)}
     */
    @Test
    void testFindById2() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Long) any())).thenReturn(ofResult);
        doThrow(new CustomerNotFoundException("id")).when(customerValidator)
                .validate404((Optional<Object>) any(), (String) any(), (String) any());
        assertThrows(CustomerNotFoundException.class, () -> customerManagementServiceImp.findById(123L));
        verify(customerRepository).findById((Long) any());
        verify(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#findById(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindById3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.findById(CustomerManagementServiceImp.java:51)
        //   In order to prevent findById(Long)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   findById(Long).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.findById((Long) any())).thenReturn(Optional.empty());
        doNothing().when(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
        customerManagementServiceImp.findById(123L);
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#findById(Long)}
     */
    @Test
    void testFindById4() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Long) any())).thenReturn(ofResult);
        doNothing().when(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
        assertSame(customer, customerManagementServiceImp.findById(123L));
        verify(customerRepository).findById((Long) any());
        verify(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#findById(Long)}
     */
    @Test
    void testFindById5() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById((Long) any())).thenReturn(ofResult);
        doThrow(new CustomerNotFoundException("id")).when(customerValidator)
                .validate404((Optional<Object>) any(), (String) any(), (String) any());
        assertThrows(CustomerNotFoundException.class, () -> customerManagementServiceImp.findById(123L));
        verify(customerRepository).findById((Long) any());
        verify(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#findById(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindById6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.findById(CustomerManagementServiceImp.java:51)
        //   In order to prevent findById(Long)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   findById(Long).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.findById((Long) any())).thenReturn(Optional.empty());
        doNothing().when(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
        customerManagementServiceImp.findById(123L);
    }

    @Test
    void selectCustomerByPhoneNumberTest() {
        String phoneNum = "123455";
        Optional<String> object = Optional.of("value");
        Customer customer = new Customer(1L, "test_user", "Test User", "12345678");

        when(customerRepository.selectCustomerByPhoneNumber(phoneNum)).thenReturn(Optional.of(customer));

        customerValidator.validate404(object, "phoneNumber", String.valueOf(phoneNum));

        Customer result = customerManagementService.selectCustomerByPhoneNumber(phoneNum);
        Assertions.assertEquals(customer, result);

    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#selectCustomerByPhoneNumber(String)}
     */
    @Test
    void testSelectCustomerByPhoneNumber() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.selectCustomerByPhoneNumber((String) any())).thenReturn(ofResult);
        doNothing().when(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
        assertSame(customer, customerManagementServiceImp.selectCustomerByPhoneNumber("4105551212"));
        verify(customerRepository).selectCustomerByPhoneNumber((String) any());
        verify(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#selectCustomerByPhoneNumber(String)}
     */
    @Test
    void testSelectCustomerByPhoneNumber2() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.selectCustomerByPhoneNumber((String) any())).thenReturn(ofResult);
        doThrow(new CustomerNotFoundException("phone number")).when(customerValidator)
                .validate404((Optional<Object>) any(), (String) any(), (String) any());
        assertThrows(CustomerNotFoundException.class,
                () -> customerManagementServiceImp.selectCustomerByPhoneNumber("4105551212"));
        verify(customerRepository).selectCustomerByPhoneNumber((String) any());
        verify(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#selectCustomerByPhoneNumber(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSelectCustomerByPhoneNumber3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.selectCustomerByPhoneNumber(CustomerManagementServiceImp.java:61)
        //   In order to prevent selectCustomerByPhoneNumber(String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   selectCustomerByPhoneNumber(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.selectCustomerByPhoneNumber((String) any())).thenReturn(Optional.empty());
        doNothing().when(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
        customerManagementServiceImp.selectCustomerByPhoneNumber("4105551212");
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#selectCustomerByPhoneNumber(String)}
     */
    @Test
    void testSelectCustomerByPhoneNumber4() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.selectCustomerByPhoneNumber((String) any())).thenReturn(ofResult);
        doNothing().when(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
        assertSame(customer, customerManagementServiceImp.selectCustomerByPhoneNumber("4105551212"));
        verify(customerRepository).selectCustomerByPhoneNumber((String) any());
        verify(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#selectCustomerByPhoneNumber(String)}
     */
    @Test
    void testSelectCustomerByPhoneNumber5() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.selectCustomerByPhoneNumber((String) any())).thenReturn(ofResult);
        doThrow(new CustomerNotFoundException("phone number")).when(customerValidator)
                .validate404((Optional<Object>) any(), (String) any(), (String) any());
        assertThrows(CustomerNotFoundException.class,
                () -> customerManagementServiceImp.selectCustomerByPhoneNumber("4105551212"));
        verify(customerRepository).selectCustomerByPhoneNumber((String) any());
        verify(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#selectCustomerByPhoneNumber(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSelectCustomerByPhoneNumber6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.selectCustomerByPhoneNumber(CustomerManagementServiceImp.java:61)
        //   In order to prevent selectCustomerByPhoneNumber(String)
        //   from throwing NoSuchElementException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   selectCustomerByPhoneNumber(String).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.selectCustomerByPhoneNumber((String) any())).thenReturn(Optional.empty());
        doNothing().when(customerValidator).validate404((Optional<Object>) any(), (String) any(), (String) any());
        customerManagementServiceImp.selectCustomerByPhoneNumber("4105551212");
    }

    @Test
    void deleteCustomerTest() {
        Long customerId = 1L;
        doReturn(true).when(customerRepository).existsById(customerId);
        customerManagementService.delete(customerId);
        verify(customerRepository, times(1)).deleteById(customerId);
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#delete(Long)}
     */
    @Test
    void testDelete() {
        doNothing().when(customerRepository).deleteById((Long) any());
        when(customerRepository.existsById((Long) any())).thenReturn(true);
        customerManagementServiceImp.delete(123L);
        verify(customerRepository).existsById((Long) any());
        verify(customerRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#delete(Long)}
     */
    @Test
    void testDelete2() {
        doThrow(new CustomerNotFoundException("Msg")).when(customerRepository).deleteById((Long) any());
        when(customerRepository.existsById((Long) any())).thenReturn(true);
        assertThrows(CustomerNotFoundException.class, () -> customerManagementServiceImp.delete(123L));
        verify(customerRepository).existsById((Long) any());
        verify(customerRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#delete(Long)}
     */
    @Test
    void testDelete3() {
        doNothing().when(customerRepository).deleteById((Long) any());
        when(customerRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(CustomerNotFoundException.class, () -> customerManagementServiceImp.delete(123L));
        verify(customerRepository).existsById((Long) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#delete(Long)}
     */
    @Test
    void testDelete4() {
        doNothing().when(customerRepository).deleteById((Long) any());
        when(customerRepository.existsById((Long) any())).thenReturn(true);
        customerManagementServiceImp.delete(123L);
        verify(customerRepository).existsById((Long) any());
        verify(customerRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#delete(Long)}
     */
    @Test
    void testDelete5() {
        doThrow(new CustomerNotFoundException("Msg")).when(customerRepository).deleteById((Long) any());
        when(customerRepository.existsById((Long) any())).thenReturn(true);
        assertThrows(CustomerNotFoundException.class, () -> customerManagementServiceImp.delete(123L));
        verify(customerRepository).existsById((Long) any());
        verify(customerRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#delete(Long)}
     */
    @Test
    void testDelete6() {
        doNothing().when(customerRepository).deleteById((Long) any());
        when(customerRepository.existsById((Long) any())).thenReturn(false);
        assertThrows(CustomerNotFoundException.class, () -> customerManagementServiceImp.delete(123L));
        verify(customerRepository).existsById((Long) any());
    }

    @Test
    void customerNotFoundTest() {
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

    /**
     * Method under test: {@link CustomerManagementServiceImp#addCustomer(Customer)}
     */
    @Test
    void testAddCustomer() {
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
        assertThrows(BadRequestException.class, () -> customerManagementServiceImp.addCustomer(customer2));
        verify(customerRepository).selectCustomerByPhoneNumber((String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#addCustomer(Customer)}
     */
    @Test
    void testAddCustomer2() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        when(customerRepository.save((Customer) any())).thenReturn(customer);
        when(customerRepository.selectCustomerByPhoneNumber((String) any())).thenReturn(Optional.empty());

        Customer customer1 = new Customer();
        customer1.setId(123L);
        customer1.setName("Name");
        customer1.setPhoneNumber("4105551212");
        customer1.setUserName("janedoe");
        assertSame(customer, customerManagementServiceImp.addCustomer(customer1));
        verify(customerRepository).save((Customer) any());
        verify(customerRepository).selectCustomerByPhoneNumber((String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#addCustomer(Customer)}
     */
    @Test
    void testAddCustomer3() {
        when(customerRepository.save((Customer) any())).thenThrow(new BadRequestException("Msg"));
        when(customerRepository.selectCustomerByPhoneNumber((String) any())).thenThrow(new BadRequestException("Msg"));

        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        assertThrows(BadRequestException.class, () -> customerManagementServiceImp.addCustomer(customer));
        verify(customerRepository).selectCustomerByPhoneNumber((String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#addCustomer(Customer)}
     */
    @Test
    void testAddCustomer4() {
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
        assertThrows(BadRequestException.class, () -> customerManagementServiceImp.addCustomer(customer2));
        verify(customerRepository).selectCustomerByPhoneNumber((String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#addCustomer(Customer)}
     */
    @Test
    void testAddCustomer5() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        when(customerRepository.save((Customer) any())).thenReturn(customer);
        when(customerRepository.selectCustomerByPhoneNumber((String) any())).thenReturn(Optional.empty());

        Customer customer1 = new Customer();
        customer1.setId(123L);
        customer1.setName("Name");
        customer1.setPhoneNumber("4105551212");
        customer1.setUserName("janedoe");
        assertSame(customer, customerManagementServiceImp.addCustomer(customer1));
        verify(customerRepository).save((Customer) any());
        verify(customerRepository).selectCustomerByPhoneNumber((String) any());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#addCustomer(Customer)}
     */
    @Test
    void testAddCustomer6() {
        when(customerRepository.save((Customer) any())).thenThrow(new BadRequestException("Msg"));
        when(customerRepository.selectCustomerByPhoneNumber((String) any())).thenThrow(new BadRequestException("Msg"));

        Customer customer = new Customer();
        customer.setId(123L);
        customer.setName("Name");
        customer.setPhoneNumber("4105551212");
        customer.setUserName("janedoe");
        assertThrows(BadRequestException.class, () -> customerManagementServiceImp.addCustomer(customer));
        verify(customerRepository).selectCustomerByPhoneNumber((String) any());
    }

    @Test
    void customerFailedTest() {
        String ph = "123457";
        Customer customer = new Customer(1l, "Test", "TT", ph);
        when(customerRepository.selectCustomerByPhoneNumber(customer.getPhoneNumber())).thenReturn(Optional.of(customer));
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            customerManagementService.addCustomer(customer);
        });
        assertEquals("Phone Number " + ph + " taken", exception.getMessage());

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

    /**
     * Method under test: {@link CustomerManagementServiceImp#saveAll(List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveAll() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.util.Objects.requireNonNull(Objects.java:208)
        //       at java.util.stream.StreamSupport.stream(StreamSupport.java:68)
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.saveAll(CustomerManagementServiceImp.java:92)
        //   In order to prevent saveAll(List)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveAll(List).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.saveAll((Iterable<Customer>) any())).thenReturn((Iterable<Customer>) mock(Iterable.class));
        customerManagementServiceImp.saveAll(new ArrayList<>());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#saveAll(List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveAll2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.softwaretesting.testing.exception.CustomerNotFoundException: Msg
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.saveAll(CustomerManagementServiceImp.java:92)
        //   In order to prevent saveAll(List)
        //   from throwing CustomerNotFoundException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveAll(List).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.saveAll((Iterable<Customer>) any())).thenReturn((Iterable<Customer>) mock(Iterable.class));
        new CustomerNotFoundException("Msg");
        customerManagementServiceImp.saveAll(new ArrayList<>());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#saveAll(List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveAll3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.util.Objects.requireNonNull(Objects.java:208)
        //       at java.util.stream.StreamSupport.stream(StreamSupport.java:68)
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.saveAll(CustomerManagementServiceImp.java:92)
        //   In order to prevent saveAll(List)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveAll(List).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.saveAll((Iterable<Customer>) any())).thenReturn((Iterable<Customer>) mock(Iterable.class));
        customerManagementServiceImp.saveAll(new ArrayList<>());
    }

    /**
     * Method under test: {@link CustomerManagementServiceImp#saveAll(List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveAll4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.softwaretesting.testing.exception.CustomerNotFoundException: Msg
        //       at com.softwaretesting.testing.customerManagement.service.CustomerManagementServiceImp.saveAll(CustomerManagementServiceImp.java:92)
        //   In order to prevent saveAll(List)
        //   from throwing CustomerNotFoundException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   saveAll(List).
        //   See https://diff.blue/R013 to resolve this issue.

        when(customerRepository.saveAll((Iterable<Customer>) any())).thenReturn((Iterable<Customer>) mock(Iterable.class));
        new CustomerNotFoundException("Msg");
        customerManagementServiceImp.saveAll(new ArrayList<>());
    }

    @Test
    void deleteCustomerNotFoundTest() {
        Long customerId = 1L;
        doReturn(false).when(customerRepository).existsById(customerId);

        assertThrows(CustomerNotFoundException.class, () -> customerManagementService.delete(customerId));
    }

    @Test
    void listEmptyTest() {
        when(customerRepository.findAll()).thenReturn(Collections.emptySet());

        Collection<Customer> customers = customerManagementService.list();

        assertTrue(customers.isEmpty());
    }

    @Test
    void saveAllEmptyTest() {
        List<Customer> customers = Collections.emptyList();

        when(customerRepository.saveAll(customers)).thenReturn(Collections.emptyList());

        Collection<Customer> savedCustomers = customerManagementService.saveAll(customers);

        assertTrue(savedCustomers.isEmpty());
        verify(customerRepository, times(1)).saveAll(customers);
    }

    @Test
    void findValidUserNameTest() {
        String userName = "test_user";
        Customer customer = new Customer(1L, "test_user", "Test User", "12345678");

        when(customerRepository.findByUserName(userName)).thenReturn(Optional.of(customer));

        Customer result = customerManagementService.findByUserName(userName);
        Assertions.assertEquals(customer, result);
    }

    @Test
    void validator404() {
        String userName = "test_user";
        Optional<String> object = Optional.of("value");
        Customer customer = new Customer(1L, "test_user", "Test User", "12345678");

        when(customerRepository.findByUserName(userName)).thenReturn(Optional.of(customer));

        customerValidator.validate404(object, "User-Name", userName);

        Customer result = customerManagementService.findByUserName(userName);
        Assertions.assertEquals(customer, result);
    }

}