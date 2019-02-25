package com.github.therycn.tyideapp;

import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

/**
 * Repository Rest Configurer Adapter, used to add entities ids.
 * 
 * @author TheryLeopard
 *
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