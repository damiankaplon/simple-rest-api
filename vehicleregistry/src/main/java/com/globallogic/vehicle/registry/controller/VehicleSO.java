package com.globallogic.vehicle.registry.controller;

import lombok.Data;

@Data
public class VehicleSO {
    private String vin;
    private Integer productionYear;
    private String brand;
    private String model;
}
