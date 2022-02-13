package com.globallogic.vehicle.registry.entities;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Version
    protected Integer version;
    @CreationTimestamp
    protected LocalDateTime creationDate;

    private String manufacturer;

    private String oem;

    @ManyToOne
    private Vehicle vehicle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Part part = (Part) o;
        return id == null && Objects.equals(id, part.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
