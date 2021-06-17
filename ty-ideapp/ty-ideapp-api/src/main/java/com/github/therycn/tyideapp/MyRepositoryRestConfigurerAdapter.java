package com.github.therycn.tyideapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import javax.persistence.EntityManager;
import java.util.stream.Collectors;

/**
 * Repository Rest Configurer Adapter, used to add entities ids.
 */
@Configuration
public class MyRepositoryRestConfigurerAdapter implements RepositoryRestConfigurer {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(e -> e.getJavaType())
                .collect(Collectors.toList()).toArray(new Class[0]));
    }

}