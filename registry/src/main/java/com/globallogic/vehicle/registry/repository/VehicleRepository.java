package com.globallogic.vehicle.registry.repository;

import com.globallogic.vehicle.registry.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    Vehicle findByVin(String vin);
    void deleteByVin(String vin);
}
