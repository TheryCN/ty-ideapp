package com.github.therycn.tyideapp.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Workspace extends AbstractEntity<Long> {

	/** Serial version. */
	private static final long serialVersionUID = 3064526606390219282L;

	@Id
	@Column(name = "WKS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "WKS_NAME", unique = true)
	private String name;

	@OneToMany(mappedBy = "workspace", cascade = CascadeType.REMOVE)
	private Collection<Idea> ideas;

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
