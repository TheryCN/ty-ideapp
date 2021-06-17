package com.github.therycn.tyideapp;

import com.github.therycn.tyideapp.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Spring Security Auditor Aware.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.domain.AuditorAware#getCurrentAuditor()
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated).map(Authentication::getPrincipal)
                .filter(principal -> !principal.toString().equals("anonymousUser")).map(User.class::cast)
                .map(User::getUsername);
    }

}
