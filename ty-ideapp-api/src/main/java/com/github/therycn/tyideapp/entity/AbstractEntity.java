package com.github.therycn.tyideapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract Entity.
 * 
 * @author TheryLeopard
 *
 * @param <T>
 */
@Getter
@Setter
public abstract class AbstractEntity<T> implements Serializable {

    /** Serial version. */
    private static final long serialVersionUID = 1699519676518669090L;

    private Date createdOn = new Date();

    private Date updatedOn = new Date();

    public abstract T getId();

    @PreUpdate
    public void preUpdate() {
        updatedOn = new Date();
    }

}
