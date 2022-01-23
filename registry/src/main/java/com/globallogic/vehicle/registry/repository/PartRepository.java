package com.globallogic.vehicle.registry.repository;

import com.globallogic.vehicle.registry.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {

    List<Part> findAllByVehicle_vin(String vin);
}
