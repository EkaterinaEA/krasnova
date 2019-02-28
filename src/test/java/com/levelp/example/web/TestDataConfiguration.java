package com.levelp.example.web;

import com.levelp.example.ApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.levelp.example")
@ComponentScan(
        basePackages = "com.levelp.example",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = {ApplicationConfiguration.class})
)
public class TestDataConfiguration {

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory){
        return new JpaTransactionManager(factory);
    }

    @Bean (name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(){
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

}
