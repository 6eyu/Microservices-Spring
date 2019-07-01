package com.learn.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests().antMatchers("/static/**").permitAll();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        httpSecurity
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/login",  "/crossSiteMessage",
                        "/signin*", "/oauth/token", "/oauth/authorize", "/oauth/confirm_access", "/oauth/check_token").permitAll()
                .anyRequest().authenticated();
    }
}
