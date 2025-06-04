package com.softwaretesting.testing.customerManagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwaretesting.testing.customerManagement.service.CustomerManagementService;
import com.softwaretesting.testing.model.Customer;
import com.softwaretesting.testing.dto.inbound.CustomerInDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerManagementControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerManagementService customerManagementService;

    @Test
    public void testListCustomers() throws Exception {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setUserName("user1");
        customer1.setName("Micky Mouse");
        customer1.setPhoneNumber("1234567890");
        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setUserName("user2");
        customer2.setName("Mini Mouse");
        customer2.setPhoneNumber("9876543210");
        List<Customer> customers = Arrays.asList(customer1, customer2);

        Mockito.when(customerManagementService.list()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("user1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Micky Mouse"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].phoneNumber").value("1234567890"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].userName").value("user2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Mini Mouse"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].phoneNumber").value("9876543210"));
    }

    @Test
    public void testListCustomers_EmptyList() throws Exception {
        Mockito.when(customerManagementService.list()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    public void testGetCustomerById() throws Exception {

        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setUserName("user1");
        customer.setName("Micky Mouse");
        customer.setPhoneNumber("1234567890");


        Mockito.when(customerManagementService.findById(customerId)).thenReturn(customer);


        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/{cid}", customerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customerId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("user1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Micky Mouse"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void testAddCustomer() throws Exception {

        CustomerInDTO customerInDTO = new CustomerInDTO();
        customerInDTO.setUserName("user1");
        customerInDTO.setName("Micky Mouse");
        customerInDTO.setPhoneNumber("1234567890");


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customerInDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("user1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Micky Mouse"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));
    }

    private static String asJsonString(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void testDeleteCustomer() throws Exception {

        Long customerId = 2L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/{cid}", customerId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(customerManagementService, Mockito.times(1)).delete(customerId);
    }

    @Test
    public void testDeleteCustomer_CustomerNotFound() throws Exception {
        Long customerId = 2L;
        Mockito.doThrow(new IllegalStateException("Customer not found"))
                .when(customerManagementService).delete(customerId);

        try {
            mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/{cid}", customerId))
                    .andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e) {
            Assert.assertEquals(IllegalStateException.class, e.getCause().getClass());
            Assert.assertEquals("Customer not found", e.getCause().getMessage());
        }
    }

    @Test
    public void testDelete() throws Exception {
        Long customerId = 2L;

        Mockito.doNothing().when(customerManagementService).delete(customerId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/{cid}", customerId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(customerManagementService, Mockito.times(1)).delete(customerId);
    }

}
