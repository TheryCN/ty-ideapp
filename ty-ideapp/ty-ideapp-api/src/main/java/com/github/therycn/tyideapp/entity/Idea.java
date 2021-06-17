package com.github.therycn.tyideapp.entity;

import com.github.therycn.tyideapp.entity.geom.Geometry;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Idea entity.
 */
@Table(name = "IDEA")
@Entity
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "IDA_ID"))
public class Idea extends AbstractAuditableEntity<Long> {

    @Column(name = "IDA_NAME", unique = true)
    private String name;

    @Column(name = "IDA_SUBTITLE")
    private String subTitle;

    @Column(name = "IDA_DESCRIPTION")
    private String description;

    /**
     * Rating stars 0 as minimum, 5 as maximum.
     */
    @Column(name = "IDA_RATING")
    private int rating;

    @Column(name = "IDA_ACHIEVE", nullable = false)
    private boolean achieve;

    @Column(name = "IDA_FEASIBILITY")
    @Enumerated(EnumType.STRING)
    private FeasibilityType feasibility;

    @ManyToOne
    @JoinColumn(name = "IDA_WKS_ID", foreignKey = @ForeignKey(name = "FK_IDA_WKS"))
    private Workspace workspace;

    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Geometry> localizations;
}
