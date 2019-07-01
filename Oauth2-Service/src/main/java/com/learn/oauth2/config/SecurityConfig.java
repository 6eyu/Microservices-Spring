package com.learn.oauth2.config;

import com.learn.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;


@Configuration
//@Order(SecurityProperties.IGNORED_ORDER)
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService")
    private UserService userDetailsService;

    @Autowired
    private Oauth2AuthenticationProvider authProvider;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authProvider)
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/static/**").permitAll();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

//        httpSecurity.addFilterAt(new EizUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().frameOptions().disable();
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login*", "/crossSiteMessage",
                        "/signin*", "/oauth/token", "/oauth/authorize", "/oauth/confirm_access", "/oauth/check_token").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .exceptionHandling().authenticationEntryPoint(new EizAuthenticationUrlEntryPoint("/eiz-account/signin"));
    }



    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
