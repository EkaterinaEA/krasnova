package com.levelp.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = "com.levelp.example",
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = ApplicationConfiguration.class))
public class TestConfiguration extends WebMvcConfigurerAdapter {

    @Bean // некий управляемый компонент
    public EntityManager createEntityManager(EntityManagerFactory factory){
        return factory.createEntityManager();
    }

    @Bean
    public EntityManagerFactory createEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

}
