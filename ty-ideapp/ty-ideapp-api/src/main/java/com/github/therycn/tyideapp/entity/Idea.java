package com.github.therycn.tyideapp.entity;

import java.util.Collection;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.github.therycn.tyideapp.entity.geom.Geometry;

import lombok.Getter;
import lombok.Setter;

/**
 * Idea entity.
 * 
 * @author TheryLeopard
 *
 */
@Table(name = "IDEA")
@Entity
@Getter
@Setter
@AttributeOverrides({
		@AttributeOverride(name = "createdOn", column = @Column(name = "IDA_CREATED_ON", updatable = false)),
		@AttributeOverride(name = "updatedOn", column = @Column(name = "IDA_UPDATED_ON")),
		@AttributeOverride(name = "createdBy", column = @Column(name = "IDA_CREATED_BY", updatable = false)),
		@AttributeOverride(name = "modifiedBy", column = @Column(name = "IDA_MODIFIED_BY")) })
public class Idea extends AbstractEntity<Long> {

	/** Serial version. */
	private static final long serialVersionUID = -850686969756458480L;

	@Id
	@Column(name = "IDA_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "IDA_NAME", unique = true)
	private String name;

	@Column(name = "IDA_SUBTITLE")
	private String subTitle;

	@Column(name = "IDA_DESCRIPTION")
	private String description;

	/** Rating stars 0 as minimum, 5 as maximum. */
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
	private Collection<Geometry> localizations;
}
