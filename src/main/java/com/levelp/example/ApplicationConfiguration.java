package com.levelp.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "src.main.web.example")
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

    @Bean // некий управляемый компонент
    public EntityManager createEntityManager(EntityManagerFactory factory){
        return factory.createEntityManager();
    }

    @Bean
    public EntityManagerFactory createEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
    }

    @Bean
    public ViewResolver createViewResolver(){

        InternalResourceViewResolver resolver = new InternalResourceViewResolver();

        // путь, где найти ресурс:
        resolver.setPrefix("/pages/");
        resolver.setSuffix(".jsp");

        resolver.setViewClass(JstlView.class);

        return resolver;

    }

}
