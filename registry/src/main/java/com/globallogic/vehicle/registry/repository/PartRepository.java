package com.globallogic.vehicle.registry.repository;

import com.globallogic.vehicle.registry.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Integer> {

}
