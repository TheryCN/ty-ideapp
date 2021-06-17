package com.github.therycn.tyideapp.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Abstract Auditable Entity.
 *
 * @param <T>
 */
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "id")
public abstract class AbstractAuditableEntity<T> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

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

}
