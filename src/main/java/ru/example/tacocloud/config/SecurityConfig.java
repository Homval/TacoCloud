package ru.example.tacocloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        String passwordBuzz = passwordEncoder().encode("buzz-pass");
//        String passwordFrog = passwordEncoder().encode("frog-pass");
//        auth.inMemoryAuthentication()
//                .withUser("buzz")
//                .password(passwordBuzz)
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("frog")
//                .password(passwordFrog)
//                .authorities("ROLE_USER");
//
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from Users where username=?"
                )
                .authoritiesByUsernameQuery(
                        "select username, authority from UserAuthorities where username=?"
                )
                .passwordEncoder(passwordEncoder());

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
