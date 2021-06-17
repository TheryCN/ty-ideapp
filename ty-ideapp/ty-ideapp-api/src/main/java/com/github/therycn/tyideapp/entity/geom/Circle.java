package com.github.therycn.tyideapp.entity.geom;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Circle.
 */
@Entity
@DiscriminatorValue("CIRCLE")
@Getter
@Setter
public class Circle extends Geometry {

    /**
     * Constructor.
     */
    public Circle() {
        super();
        setType(GeometryType.CIRCLE);
    }

    @Column(name = "GEO_RADIUS")
    private Double radius;

}
