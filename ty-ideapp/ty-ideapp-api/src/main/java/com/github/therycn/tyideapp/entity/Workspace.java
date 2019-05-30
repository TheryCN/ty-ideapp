package com.github.therycn.tyideapp.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Workspace entity.
 * 
 * @author TheryLeopard
 *
 */
@Table(name = "WORKSPACE")
@Entity
@Getter
@Setter
@AttributeOverrides({
		@AttributeOverride(name = "createdOn", column = @Column(name = "WKS_CREATED_ON", updatable = false)),
		@AttributeOverride(name = "updatedOn", column = @Column(name = "WKS_UPDATED_ON")),
		@AttributeOverride(name = "createdBy", column = @Column(name = "WKS_CREATED_BY", updatable = false)),
		@AttributeOverride(name = "modifiedBy", column = @Column(name = "WKS_MODIFIED_BY")) })
public class Workspace extends AbstractEntity<Long> {

	/** Serial version. */
	private static final long serialVersionUID = 3064526606390219282L;

	@Id
	@Column(name = "WKS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "WKS_NAME")
	private String name;

	@OneToMany(mappedBy = "workspace", cascade = CascadeType.REMOVE)
	private Collection<Idea> ideas;

	@ManyToOne
	@JoinColumn(name = "WKS_USR_ID", foreignKey = @ForeignKey(name = "FK_WKS_USR"))
	private User user;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.therycn.tyideapp.entity.AbstractEntity#getCreatedOn()
	 */
	@Column(name = "WKS_CREATED_ON")
	@Override
	public Date getCreatedOn() {
		return super.getCreatedOn();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.therycn.tyideapp.entity.AbstractEntity#getUpdatedOn()
	 */
	@Column(name = "WKS_UPDATED_ON")
	@Override
	public Date getUpdatedOn() {
		return super.getUpdatedOn();
	}

}
