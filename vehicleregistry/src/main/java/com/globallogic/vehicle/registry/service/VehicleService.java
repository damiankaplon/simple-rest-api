package com.globallogic.vehicle.registry.service;

import com.globallogic.vehicle.registry.controller.VehicleSO;
import com.globallogic.vehicle.registry.entities.Vehicle;
import com.globallogic.vehicle.registry.exceptions.RegistryResourceNotFound;
import com.globallogic.vehicle.registry.repository.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    protected final ModelMapper modelMapper;

    public VehicleSO get(String vin) {
        Vehicle found = vehicleRepository.findByVin(vin);

        if (found == null) {
            throw new RegistryResourceNotFound("Vehicle with given VIN does not exist.");
        }

        return modelMapper.map(found, VehicleSO.class);
    }

    public VehicleSO create(VehicleSO so) {
        Vehicle vehicle = modelMapper.map(so, Vehicle.class);

        return modelMapper.map(vehicleRepository.save(vehicle), VehicleSO.class);
    }

    public List<VehicleSO> getAll() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles
                .stream()
                .map(v -> modelMapper.map(v, VehicleSO.class))
                .collect(Collectors.toList());
    }

    public Vehicle updateByVin(VehicleSO so) {
        Vehicle vehicleFromDb = vehicleRepository.findByVin(so.getVin());

        Vehicle requestVehicle = modelMapper.map(so, Vehicle.class);
        requestVehicle.setCreationDate(vehicleFromDb.getCreationDate());
        requestVehicle.setVersion(vehicleFromDb.getVersion());

        return vehicleRepository.save(requestVehicle);
    }

    @Transactional
    public void deleteByVin(String vin) {
        vehicleRepository.deleteByVin(vin);
    }

    public VehicleService(VehicleRepository vehicleRepository, ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
    }
}