package org.norman.system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.norman.customer.entity.Customer;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "system_version")
public class SystemVersion {
    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "system_class")
    @Enumerated(EnumType.STRING)
    private SystemClass systemClass;

    @Column(name = "valid_from")
    private Date validFrom;
}
