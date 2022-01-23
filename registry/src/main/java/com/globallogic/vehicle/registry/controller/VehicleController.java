package com.globallogic.vehicle.registry.controller;

import com.globallogic.vehicle.registry.entities.Part;
import com.globallogic.vehicle.registry.entities.Vehicle;
import com.globallogic.vehicle.registry.service.RegistryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@Slf4j
@Api("Article Management API")
public class RegistryController {

    @Autowired
    private RegistryService registryService;

    @ApiOperation(value = "Returns a specified entity.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entity found") })
    @GetMapping(path = "/{vin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleSO get(@PathVariable(name = "vin") String vin) {
        VehicleSO vehicleSO = registryService.get(vin);
        log.info("Returning vehicle={}", vehicleSO);
        return vehicleSO;
    }


    @ApiOperation(value = "Creates an entity.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Vehicle entry created") })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public VehicleSO create(@RequestBody VehicleSO so) {
        return registryService.create(so);
    }

    @ApiOperation(value = "Returns all entities")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entities found") })
    @GetMapping
    public List<VehicleSO> getAll() {
        return registryService.getAll();
    }

    @PutMapping(path = "/{vin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vehicle updateVehicle(@PathVariable String vin, @RequestBody VehicleSO vehicle) {
        vehicle.setVin(vin);
        return registryService.updateByVin(vehicle);
    }

    @DeleteMapping(path = "/{vin}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Returns all entities")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entities found") })
    public void deleteVehicleByVin(@PathVariable String vin) {
        registryService.deleteByVin(vin);
    }

}