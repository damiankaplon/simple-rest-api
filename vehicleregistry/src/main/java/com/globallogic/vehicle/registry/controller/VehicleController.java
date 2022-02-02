package com.globallogic.vehicle.registry.controller;

import com.globallogic.vehicle.registry.entities.Vehicle;
import com.globallogic.vehicle.registry.service.PartService;
import com.globallogic.vehicle.registry.service.VehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@Slf4j
@Api("Article Management API")
public class VehicleController {

    private final VehicleService vehicleService;
    private final PartService partService;

    @ApiOperation(value = "Returns a specified entity.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entity found") })
    @GetMapping(path = "/{vin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VehicleSO get(@PathVariable(name = "vin") String vin) {
        VehicleSO vehicleSO = vehicleService.get(vin);
        log.info("Returning vehicle={}", vehicleSO);
        return vehicleSO;
    }


    @ApiOperation(value = "Creates an entity.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Vehicle entry created") })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public VehicleSO create(@RequestBody VehicleSO so) {
        return vehicleService.create(so);
    }

    @ApiOperation(value = "Returns all entities")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entities found") })
    @GetMapping
    public List<VehicleSO> getAll() {
        return vehicleService.getAll();
    }

    @PutMapping(path = "/{vin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vehicle updateVehicle(@PathVariable String vin, @RequestBody VehicleSO vehicle) {
        vehicle.setVin(vin);
        return vehicleService.updateByVin(vehicle);
    }

    @DeleteMapping(path = "/{vin}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Deleted") })
    public void deleteVehicleByVin(@PathVariable String vin) {
        vehicleService.deleteByVin(vin);
    }

    @GetMapping("/{vin}/parts")
    public List<PartSO> getVehiclesParts(@PathVariable String vin) {
        return partService.getVehiclesParts(vin);
    }

    public VehicleController(VehicleService vehicleService, PartService partService) {
        this.vehicleService = vehicleService;
        this.partService = partService;
    }
}