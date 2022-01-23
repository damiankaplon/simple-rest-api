package com.globallogic.vehicle.registry.controller;

import com.globallogic.vehicle.registry.service.PartService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parts")
public class PartsController {

    final PartService partService;

    public PartsController(PartService partService) {
        this.partService = partService;
    }

    @ApiOperation(value = "Returns all vehicles parts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Entities found") })
    @GetMapping("/{id}")
    public PartSO getAllVehiclesParts(@PathVariable Integer id) {
        return partService.getPart(id);
    }
}
