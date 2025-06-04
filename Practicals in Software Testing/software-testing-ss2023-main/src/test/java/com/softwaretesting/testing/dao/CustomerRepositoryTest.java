package com.softwaretesting.testing.dao;
import com.softwaretesting.testing.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.NoSuchElementException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repo;

    @Test
    void testFindByUserNameFailed() {
        Customer c = new Customer(122L, "Tommy", "Tom", "1234567890");
        repo.save(c);
        Optional<Customer> customer = repo.findByUserName("Zombie");
        String expectedMsg = "No value present";
        NoSuchElementException exception= assertThrows(NoSuchElementException.class, ()->
                customer.get());
        assertEquals(expectedMsg, exception.getMessage());
    }

    @Test
    void testFindByUserNameSuccess() {
        repo.save(new Customer(122L, "Tommy", "Tom", "1234567890"));
        Optional<Customer> customer = repo.findByUserName("Tommy");
        assertEquals("Tommy",customer.get().getUserName());
    }

    @Test
    void testSelectCustomerByPhoneNumberFailed() {
        repo.save(new Customer(122L, "Tommy", "Tom", "1234567890"));
        Optional<Customer> c = repo.selectCustomerByPhoneNumber("123");
        String expectedMsg = "No value present";
        NoSuchElementException exception= assertThrows(NoSuchElementException.class, ()->c.get());
        assertEquals(expectedMsg, exception.getMessage());
    }


    @Test
    void testSelectCustomerByPhoneNumberSuccess() {
        repo.save(new Customer(122L, "Tommy", "Tom", "1234567890"));
        Optional<Customer> c = repo.selectCustomerByPhoneNumber("1234567890");
        assertEquals("1234567890",c.get().getPhoneNumber());
    }
}