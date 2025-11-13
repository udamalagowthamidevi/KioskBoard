package com.hackathon.Restaurant.Auth;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtHelper {
	
	private String secretKey="Gowthamisiningkeyforhackathon";
	
	public String getUsername(String token) {
		Claims claims=getClaimsfromToken(token);
		return claims.getSubject();
	}
	public Claims getClaimsfromToken(String tokrn) {
		return Jwts.parserBuilder().setSigningKey(secretKey.getBytes()).build().parseClaimsJws(tokrn).getBody();
	}
	public boolean isTokenExpired(String token) {
		Claims claims=getClaimsfromToken(token);
		Date expDate=claims.getExpiration();
		return expDate.before(new Date());
	}
	public String generateToken(String username) {
		Map<String,Object> claims=new HashMap<>();
		return Jwts.builder().setClaims(claims).setSubject(username)
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+60*60))
		.signWith(new SecretKeySpec(secretKey.getBytes(),SignatureAlgorithm.HS512.getJcaName()),SignatureAlgorithm.HS512)
		.compact();
		
	}

}
