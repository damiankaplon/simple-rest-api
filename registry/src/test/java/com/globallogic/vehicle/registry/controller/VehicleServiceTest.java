package com.globallogic.vehicle.registry.controller;

import com.globallogic.vehicle.registry.entities.Vehicle;
import com.globallogic.vehicle.registry.exceptions.RegistryResourceNotFound;
import com.globallogic.vehicle.registry.repository.VehicleRepository;
import com.globallogic.vehicle.registry.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    private Vehicle stubVehicle;
    private VehicleSO stubVehicleSo;
    @Mock
    VehicleRepository vehicleRepository;
    @InjectMocks
    VehicleService vehicleService;

    static ModelMapper modelMapper;

    @BeforeAll
    public static void setUpModelMapper() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    }

    @BeforeEach
    public void setUp() {
        stubVehicleSo = new VehicleSO();
        stubVehicleSo.setProductionYear(2019);
        stubVehicleSo.setVin("12345678910");
        stubVehicleSo.setModel("Vulcan");
        stubVehicleSo.setBrand("AM");

        stubVehicle = new Vehicle();
        stubVehicle.setCreationDate(LocalDateTime.now());
        stubVehicle.setVersion(1);
        stubVehicle.setProductionYear(2019);
        stubVehicle.setVin("12345678910");
        stubVehicle.setModel("Vulcan");
        stubVehicle.setBrand("AM");
        vehicleService = new VehicleService(vehicleRepository, modelMapper);
    }


    @Test
    public void testGetByVin() {
        doReturn(stubVehicle).when(vehicleRepository).findByVin("12345678910");

        //WHEN
        VehicleSO vehicleSO = vehicleService.get("12345678910");

        //THEN
        assertThat(vehicleSO).isEqualTo(stubVehicleSo);
    }

    @Test
    public void testThrowsExceptionWhenNoSuchVinInDb() {
        assertThatThrownBy(() -> vehicleService.get("NOT EVEN A VIN LOL"))
                .isInstanceOf(RegistryResourceNotFound.class)
                .hasMessageContaining("Vehicle with given VIN does not exist.");
    }

    @Test
    public void testCreate() {
        doReturn(stubVehicle).when(vehicleRepository).save(ArgumentMatchers.any(Vehicle.class));
        //WHEN & THEN
        assertThat(vehicleService.create(stubVehicleSo)).isEqualTo(stubVehicleSo);
    }
}
