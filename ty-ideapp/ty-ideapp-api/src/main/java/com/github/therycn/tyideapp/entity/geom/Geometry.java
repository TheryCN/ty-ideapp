package com.github.therycn.tyideapp.entity.geom;

import com.github.therycn.tyideapp.entity.AbstractAuditableEntity;
import com.github.therycn.tyideapp.entity.Idea;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Geometry.
 */
@Table(name = "GEOMETRY")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "GEO_TYPE")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "GEO_ID"))
public abstract class Geometry extends AbstractAuditableEntity<Long> {

    @Column(name = "GEO_TYPE", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private GeometryType type;

    @Column(name = "GEO_COORDINATES")
    private Double[] coordinates;

    @ManyToOne
    @JoinColumn(name = "GEO_IDA_ID", foreignKey = @ForeignKey(name = "FK_GEO_IDA"))
    private Idea idea;

}
