package com.github.therycn.tyideapp.entity.geom;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.github.therycn.tyideapp.entity.Idea;

import lombok.Getter;
import lombok.Setter;

/**
 * Geometry.
 * 
 * @author TCHARASS
 *
 */
@Table(name = "GEOMETRY")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "GEO_TYPE")
@Getter
@Setter
public abstract class Geometry {

	@Id
	@Column(name = "GEO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "GEO_TYPE", insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private GeometryType type;

	@Column(name = "GEO_COORDINATES")
	private Double[] coordinates;

	@ManyToOne
	@JoinColumn(name = "IDA_WKS_ID", foreignKey = @ForeignKey(name = "FK_IDA_WKS"))
	private Idea idea;

}
