package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public UserDetailsService userDetailsService(){
        //Create user in the memory
        //Only for illustration :>>
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withDefaultPasswordEncoder().username("rekkei").password("123456").roles("USER").build()
        );
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/", "/home").permitAll() //Allow to access these 2 addresses
                .anyRequest().authenticated() //Requests need to authenticated
                .and().formLogin() //Allow user authenticate using form login
                .defaultSuccessUrl("/hello")
                .permitAll() //Tát cả đều được truy cập vào địa chỉ này
                .and()
                .logout()
                .permitAll();
    }
}
