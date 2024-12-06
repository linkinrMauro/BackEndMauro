package com.proyecto.taller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.proyecto.taller.config.JWTAuthorizationFilter;

@SpringBootApplication
public class TallerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TallerApplication.class, args);
	}
	
	
	@Configuration 
	@EnableWebSecurity
	public class WebSecurityConfig {
		@Value("${jwt.secret.key}")
		private String secret;
		
	   @Bean
	   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception { 
	      http.cors().and().csrf().disable()
	      	.addFilterAfter(new JWTAuthorizationFilter(secret), UsernamePasswordAuthenticationFilter.class)
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and().authorizeRequests() 
	        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
			.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
			.requestMatchers(HttpMethod.GET, "/productos").permitAll()
			.requestMatchers(HttpMethod.DELETE,"/api/**").permitAll()
			.requestMatchers(HttpMethod.PUT, "/api/**").permitAll()
	        .anyRequest().authenticated();
	      return http.build();
	   }
	}

/*
   @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Deshabilitar CSRF para permitir solicitudes desde fuera de la app
            .authorizeRequests()
            .antMatchers("/api/productos/crear").permitAll()  // Permitir acceso público a este endpoint
            .anyRequest().authenticated();
    } 


 */
}
