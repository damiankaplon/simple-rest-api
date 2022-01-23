package com.globallogic.vehicle.registry.controller;

import com.globallogic.vehicle.registry.service.PartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parts")
public class PartController {

    final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping()
    public List<PartSO> getAllParts() {
        return partService.getAllParts();
    }

    @GetMapping("/{id}")
    public PartSO getPartById(@PathVariable Integer id) {
        return partService.getPart(id);
    }

    @PostMapping
    public PartSO createPart(@RequestBody PartSO so) {
        return partService.addNewPart(so);
    }

    @ApiOperation(value = "Updates Part")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Accepted"),
            @ApiResponse(code = 400, message = "No Such Vin")})
    @PatchMapping("/{id}")
    public PartSO updatePart(@PathVariable Integer id, @RequestBody PartSO so) {
        return partService.updatePart(id, so);
    }
}
