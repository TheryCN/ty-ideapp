package com.github.therycn.tyideapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract Entity. https://jira.spring.io/browse/DATAREST-1204
 * 
 * @author TheryLeopard
 *
 * @param <T>
 */
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity<T> implements Serializable {

	/** Serial version. */
	private static final long serialVersionUID = 1699519676518669090L;

	@Column(updatable = false)
	@CreatedDate
	private Date createdOn;

	@LastModifiedDate
	private Date updatedOn;

	@Column(updatable = false)
	@CreatedBy
	private String createdBy;

	@LastModifiedBy
	private String modifiedBy;

	public abstract T getId();

}
