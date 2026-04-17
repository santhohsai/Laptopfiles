package com.santhosh.java.filter;

import com.santhosh.java.service.JwtService;
import com.santhosh.java.service.userInfoImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter
{
    @Autowired
    JwtService jwtservice ;

    @Autowired
    userInfoImpl userinfoimpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
          String token = null;
          String username = null;
          String header = request.getHeader("Authorization") ;
          try {
              if (null != header && header.startsWith("Bearer ")) {
                  token = header.substring(7);
                  username = jwtservice.extractUsername(token);
              }
              if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                  UserDetails userDetails = userinfoimpl.loadUserByUsername(username);
                  if (jwtservice.validateToken(token, userDetails)) {
                      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                      authenticationToken.setDetails(new WebAuthenticationDetails(request));
                      SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                  }
              }
          } catch (Exception e) {
//              throw new RuntimeException("jwterror" + e.getMessage());
              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
              response.getWriter().write("Invalid or Expired JWT Token");
              return;
          }
          filterChain.doFilter(request,response);

    }

}
