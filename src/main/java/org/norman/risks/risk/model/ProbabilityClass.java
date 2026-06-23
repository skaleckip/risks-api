package org.norman.risks.risk.model;

import jakarta.persistence.*;

import java.util.UUID;

@SuppressWarnings("unused")
@Entity
@Table(name = "probability_class")
public class ProbabilityClass {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "system_version_id")
    private UUID systemVersionId;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getSystemVersionId() {
        return systemVersionId;
    }

    public void setSystemVersionId(UUID systemVersionId) {
        this.systemVersionId = systemVersionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
