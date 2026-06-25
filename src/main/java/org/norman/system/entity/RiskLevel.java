package org.norman.system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "risk_level")
public class RiskLevel {
    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "system_version_id")
    private SystemVersion systemVersion;

    @ManyToOne
    @JoinColumn(name = "probability_class_id")
    private ProbabilityClass probabilityClass;

    @ManyToOne
    @JoinColumn(name = "impact_class_id")
    private ImpactClass impactClass;

    @Column(name = "level")
    private Short level;
}
