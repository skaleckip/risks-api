package org.norman.customer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.norman.system.entity.SystemVersion;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customer")
    private List<SystemVersion> systemVersions;
}
