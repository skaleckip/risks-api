package org.norman.risks.risk.model;

import jakarta.persistence.*;

import java.util.UUID;

@SuppressWarnings("unused")
@Entity
@Table(name = "risk")
public class Risk {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "system_version_id")
    private UUID systemVersionId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "owner_username")
    private String ownerUsername;

    @ManyToOne
    @JoinColumn(name = "probability_class_id")
    private ProbabilityClass probabilityClass;

    @ManyToOne
    @JoinColumn(name = "impact_class_id")
    private ImpactClass impactClass;

    @Column(name = "level_id")
    private UUID levelId;

    @Column(name = "level_evaluation_id")
    private UUID levelEvaluationId;

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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public ProbabilityClass getProbabilityClass() {
        return probabilityClass;
    }

    public void setProbabilityClass(ProbabilityClass probabilityClass) {
        this.probabilityClass = probabilityClass;
    }

    public ImpactClass getImpactClass() {
        return impactClass;
    }

    public void setImpactClass(ImpactClass impactClass) {
        this.impactClass = impactClass;
    }

    public UUID getLevelId() {
        return levelId;
    }

    public void setLevelId(UUID levelId) {
        this.levelId = levelId;
    }

    public UUID getLevelEvaluationId() {
        return levelEvaluationId;
    }

    public void setLevelEvaluationId(UUID levelEvaluationId) {
        this.levelEvaluationId = levelEvaluationId;
    }
}