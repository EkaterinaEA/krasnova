package com.levelp.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.levelp.example")
@EnableTransactionManagement
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

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

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory){
        return new JpaTransactionManager(factory);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/pages/static/");
    }
}
