package com.hackathon.Restaurant.Auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.hackathon.Restaurant.Repository.UserRepository;

import jakarta.servlet.http.HttpServlet;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	CustomUserDetailService userDetailService;
	
	@Autowired
	JwtAuthenticationFilter jwtFilter;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//		http.authorizeHttpRequests().anyRequest().authenticated().and().httpBasic();
//		http.csrf().disable().authorizeHttpRequests().requestMatchers("/*").permitAll().anyRequest()
//		.authenticated().and().sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).httpBasic();
//		
//		http.authorizeHttpRequests().anyRequest().permitAll().and().httpBasic();
//		//http.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//		return http.build();
//		 http.authorizeHttpRequests(auth -> auth
//	            .anyRequest().permitAll()   // ✅ allow everyone
//	        )
//	        .sessionManagement(session -> 
//	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//	        );

	    // remove JWT filter for now
	    // http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		 http
         .cors() // 👈 enable CORS with the bean below
         .and()
         .csrf().disable()
         .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
         .sessionManagement(session ->
             session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         );

     return http.build();

	   
		
	}
	 @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        CorsConfiguration config = new CorsConfiguration();
	        config.setAllowedOrigins(List.of("http://localhost:3000")); // your React app
	        config.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
	        config.setAllowedHeaders(List.of("*")); // or be specific
	        config.setAllowCredentials(true);

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", config);
	        return source;
	    }
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}


}
