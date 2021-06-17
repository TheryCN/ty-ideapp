package com.github.therycn.tyideapp.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;

/**
 * Application User.
 */
@Table(name = "APP_USER")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "USR_ID"))
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractAuditableEntity<Long> implements UserDetails {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = 4182222775401128848L;

    @Column(name = "USR_USERNAME", unique = true)
    private String username;

    @Column(name = "USR_EMAIL")
    private String email;

    @Column(name = "USR_PASSWORD")
    private String password;

    @Builder
    public User(Long id, String username, String email, String password) {
        setId(id);
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.security.core.userdetails.UserDetails#getAuthorities(
     * )
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.core.userdetails.UserDetails#
     * isAccountNonExpired ()
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.core.userdetails.UserDetails#
     * isAccountNonLocked( )
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.core.userdetails.UserDetails#
     * isCredentialsNonExpired()
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
