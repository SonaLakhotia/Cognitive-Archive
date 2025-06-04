package com.softwaretesting.testing.customerRegistration.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwaretesting.testing.customerRegistration.service.CustomerRegistrationService;
import com.softwaretesting.testing.exception.BadRequestException;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerRegistrationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerRegistrationService customerRegistrationService;

    @Test
    public void testRegisterNewCustomer() throws Exception {
        CustomerInDTO inputDTO = new CustomerInDTO();
        inputDTO.setName("John Doe");
        inputDTO.setUserName("user1");
        inputDTO.setPhoneNumber("1234567890");

        // Perform a POST request to the endpoint with the input DTO as JSON
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer-registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputDTO))) // Convert the inputDTO to JSON string
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("user1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value("1234567890"));

    }

    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testRegisterNewCustomer_DuplicatePhoneNumber() throws Exception {
        CustomerInDTO inputDTO = new CustomerInDTO();
        inputDTO.setName("John Doe");
        inputDTO.setUserName("user1");
        inputDTO.setPhoneNumber("+490999");

        Mockito.doThrow(new BadRequestException("Phone Number +490999 taken"))
                .when(customerRegistrationService)
                .registerNewCustomer(Mockito.any(Customer.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer-registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(inputDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testRegisterNewCustomer_AlreadyRegistered() throws Exception {
        CustomerInDTO inputDTO = new CustomerInDTO();
        inputDTO.setName("John Doe");
        inputDTO.setUserName("user1");
        inputDTO.setPhoneNumber("+490999");

        Mockito.doThrow(new IllegalStateException("Customer already registered"))
                .when(customerRegistrationService)
                .registerNewCustomer(Mockito.any(Customer.class));

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer-registration")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(inputDTO)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Bad Request"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Customer already registered"))
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
        } catch (Exception e) {
            Assert.assertEquals(IllegalStateException.class, e.getCause().getClass());
            Assert.assertEquals("Customer already registered", e.getCause().getMessage());
        }
    }

}