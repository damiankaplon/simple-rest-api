package com.globallogic.vehicle.registry.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
	@ToString.Exclude
	private List<Part> parts;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Vehicle vehicle = (Vehicle) o;
		return vin != null && Objects.equals(vin, vehicle.vin);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
