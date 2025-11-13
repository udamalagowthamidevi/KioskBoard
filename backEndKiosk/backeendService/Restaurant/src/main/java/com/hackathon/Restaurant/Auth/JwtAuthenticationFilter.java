package com.hackathon.Restaurant.Auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hackathon.Restaurant.DTO.User;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	JwtHelper jwtHelper;
	
	@Autowired 
	CustomUserDetailService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestHeader=request.getHeader("Authorization");
		String username=null;
		String password=null;
		String token=null;
		if(requestHeader!=null && requestHeader.contains("bearer")) {
			token=requestHeader.substring(7);	
			username=jwtHelper.getUsername(token);
			if(username!=null && SecurityContextHolder.getContext()==null) {
				User userDetails=userDetailService.loadUserByUsername(username);
				if(jwtHelper.isTokenExpired(token)) {
					UsernamePasswordAuthenticationToken usernamepasswordToken=new UsernamePasswordAuthenticationToken(token,null,userDetails.getAuthorities());
					usernamepasswordToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamepasswordToken);	
				}
				
				
			}
		}
		filterChain.doFilter(request, response);

	}

}
