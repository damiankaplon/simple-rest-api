package com.globallogic.vehicle.registry.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.vehicle.registry.service.VehicleService;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistryApplicationTests {

    protected ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    protected MockMvc mockMvc;

    @Mock
    VehicleService vehicleService;

    @InjectMocks
    VehicleController vehicleController;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
    }

    @Test
    public void testCreateClub_positive_ok() throws Exception {
        VehicleSO stubVehicleSo = new VehicleSO();
        stubVehicleSo.setProductionYear(1999);
        stubVehicleSo.setBrand("VW");
        stubVehicleSo.setModel("Polo");
        stubVehicleSo.setVin("12345678910");
        doReturn(stubVehicleSo).when(vehicleService).get("12345678910");

        mockMvc
                .perform(get("/vehicles/12345678910").header("Content-Type", "application/json"))
                .andDo(print()) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)) //
                .andExpect(jsonPath("$.productionYear", Matchers.is(1999))) //
                .andExpect(jsonPath("$.brand", Matchers.is("VW"))) //
                .andExpect(jsonPath("$.model", Matchers.is("Polo"))) //
                .andExpect(jsonPath("$.vin", Matchers.is("12345678910")));
    }

}