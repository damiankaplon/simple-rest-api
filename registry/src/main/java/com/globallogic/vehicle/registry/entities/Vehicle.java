package com.globallogic.vehicle.registry.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Vehicle {

	@Version
	protected Integer version;
	@CreationTimestamp
	protected LocalDateTime creationDate;

	@Id
	private String vin;
	private String brand;
	private String model;
	private Integer productionYear;

	@OneToMany(mappedBy = "vehicle")
	private List<Part> parts;
}
