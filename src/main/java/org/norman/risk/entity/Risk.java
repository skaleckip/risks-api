package org.norman.risk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.norman.system.entity.ImpactClass;
import org.norman.system.entity.ProbabilityClass;

import java.util.UUID;

@Setter
@Getter
@SuppressWarnings("unused")
@Entity
@Table(name = "risk")
public class Risk {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
}