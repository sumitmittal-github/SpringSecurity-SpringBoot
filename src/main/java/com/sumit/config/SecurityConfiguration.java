package com.sumit.config;


import com.sumit.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    // Authentication
    public UserDetailsService userDetailsService(){
        /*
        UserDetails admin = User.withUsername("lokesh").password(encoder.encode("admin")).roles("ADMIN").build();
        UserDetails user = User.withUsername("sumit").password(encoder.encode("user")).roles("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
        */
        return new UserService();
    }

    @Bean
    // Authorization
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        /*
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/user/populateDB").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/product/welcome").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/product/**").authenticated()
                .and()
                .formLogin()
                .and().build();
         */
        return httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/user/populateDB").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/product/welcome").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/product/**").authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }


}