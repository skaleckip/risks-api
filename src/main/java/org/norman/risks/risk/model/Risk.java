package org.norman.risks.risk.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.norman.risks.metadata.model.RiskArea;

import java.util.UUID;

@Entity
@Table(name = "risk")
public class Risk {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    private RiskArea area;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RiskArea getArea() {
        return this.area;
    }

    public void setArea(RiskArea area) {
        this.area = area;
    }
}