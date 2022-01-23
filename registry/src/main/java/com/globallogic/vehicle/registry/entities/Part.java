package com.globallogic.vehicle.registry.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Version
    protected Integer version;
    @CreationTimestamp
    protected LocalDateTime creationDate;

    private String manufacturer;

    @ManyToOne
    private Vehicle vehicle;

}
