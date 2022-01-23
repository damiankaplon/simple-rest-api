package com.globallogic.vehicle.registry.service;

import com.globallogic.vehicle.registry.controller.PartSO;
import com.globallogic.vehicle.registry.entities.Part;
import com.globallogic.vehicle.registry.entities.Vehicle;
import com.globallogic.vehicle.registry.exceptions.NoSuchPartException;
import com.globallogic.vehicle.registry.exceptions.NoSuchVinException;
import com.globallogic.vehicle.registry.repository.PartRepository;
import com.globallogic.vehicle.registry.repository.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartService {
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final VehicleRepository vehicleRepository;

    public List<PartSO> getVehiclesParts(String vin) {
        List<Part> partsFromDb = partRepository.findAllByVehicle_vin(vin);
        return partsFromDb
                .stream()
                .map(part -> modelMapper.map(part, PartSO.class))
                .collect(Collectors.toList());
    }

    public PartSO getPart(Integer id) {
        Optional<Part> partToUpdate = partRepository.findById(id);
        if (partToUpdate.isEmpty()) throw new NoSuchPartException("No such part");
        return modelMapper.map(partRepository.findById(id), PartSO.class);
    }

    public PartSO addNewPart(PartSO so) {
        Part part = partRepository.save(modelMapper.map(so, Part.class));
        return modelMapper.map(part, PartSO.class);
    }

    public PartSO updatePart(Integer id, PartSO so) throws NoSuchPartException {
        Optional<Part> partToUpdate = partRepository.findById(id);
        if (partToUpdate.isEmpty()) throw new NoSuchPartException("No such part");

        Part updatedPart = modelMapper.map(so, Part.class);
        updatedPart.setVersion(partToUpdate.get().getVersion());
        updatedPart.setId(partToUpdate.get().getId());
        updatedPart.setCreationDate(partToUpdate.get().getCreationDate());

        Vehicle vehicle = vehicleRepository.findByVin(so.getVehicleVin());
        if (vehicle == null) throw new NoSuchVinException("Vehicle with that vin not found");
        updatedPart.setVehicle(vehicle);

        return modelMapper.map(partRepository.save(updatedPart), PartSO.class);
    }

    public List<PartSO> getAllParts() {
        List<Part> partsFromDb = partRepository.findAll();
        return partsFromDb
                .stream()
                .map(part -> modelMapper.map(part, PartSO.class))
                .collect(Collectors.toList());
    }

    public PartService(PartRepository partRepository, ModelMapper modelMapper, VehicleRepository vehicleRepository) {
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.vehicleRepository = vehicleRepository;
    }
}