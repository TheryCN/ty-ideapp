package com.github.therycn.tyideapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Workspace entity.
 */
@Table(name = "WORKSPACE")
@Entity
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "WKS_ID"))
public class Workspace extends AbstractAuditableEntity<Long> {

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
