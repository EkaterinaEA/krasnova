package com.levelp.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // настройка прав доступа, генерация токена, настройка страницы логина (авт redirect)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // csrf - тип атаки: подделка формы, запросов, воспроизводство сторонних сайтов
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/", "/static/**", "/login", "/signin").permitAll()
                .antMatchers("/test/test").authenticated()
                .antMatchers("/api/users").hasRole("ADMIN")
                .antMatchers("/messages").permitAll();

        http.formLogin()
                .loginPage("signin")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
       //         .successForwardUrl("redirect:/")
                .permitAll();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider (PasswordEncoder encoder,
                                                             UsersService usersDetails){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setUserDetailsService(usersDetails);

        return provider;
    }

}
