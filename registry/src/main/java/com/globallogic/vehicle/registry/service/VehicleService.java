package com.globallogic.vehicle.registry.service;

import com.globallogic.vehicle.registry.controller.VehicleSO;
import com.globallogic.vehicle.registry.entities.Part;
import com.globallogic.vehicle.registry.entities.Vehicle;
import com.globallogic.vehicle.registry.exceptions.RegistryResourceNotFound;
import com.globallogic.vehicle.registry.repository.RegistryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RegistryService {

    @Autowired
    private RegistryRepository registryRepository;

    @Autowired
    protected ModelMapper modelMapper;

    public VehicleSO get(String vin) {
        Vehicle found = registryRepository.findByVin(vin);

        if (found == null) {
            throw new RegistryResourceNotFound("Vehicle with given VIN does not exist.");
        }

        return modelMapper.map(found, VehicleSO.class);
    }

    public VehicleSO create(VehicleSO so) {
        Vehicle vehicle = modelMapper.map(so, Vehicle.class);

        return modelMapper.map(registryRepository.save(vehicle), VehicleSO.class);
    }

    public List<VehicleSO> getAll() {
        List<Vehicle> vehicles = registryRepository.findAll();
        return vehicles
                .stream()
                .map(v -> modelMapper.map(v, VehicleSO.class))
                .collect(Collectors.toList());
    }

    public Vehicle updateByVin(VehicleSO so) {
        Vehicle vehicleFromDb = registryRepository.findByVin(so.getVin());

        Vehicle requestVehicle = modelMapper.map(so, Vehicle.class);
        requestVehicle.setId(vehicleFromDb.getId());
        requestVehicle.setCreationDate(vehicleFromDb.getCreationDate());
        requestVehicle.setVersion(vehicleFromDb.getVersion());

        return registryRepository.save(requestVehicle);
    }

    @Transactional
    public void deleteByVin(String vin) {
        registryRepository.deleteByVin(vin);
    }
}