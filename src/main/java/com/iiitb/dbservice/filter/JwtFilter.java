package com.iiitb.dbservice.filter;

import com.iiitb.dbservice.service.UserService;
import com.iiitb.dbservice.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
//@Order(10)
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserService userService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        System.out.println("Path in should not filter :PATH :"+path);
        return path.matches("/auth") || path.matches("/addUser");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        System.out.println("authorization:"+ authorization);
        String token = null;
        String username= null;
        System.out.println("filtering a request");
        if(null!=authorization && authorization.startsWith("Bearer ")){
            token = authorization.substring(7);
            username = jwtUtility.getUsernameFromToken(token);
        }
        System.out.println("context AUTHENTICATION = "+ SecurityContextHolder.getContext().getAuthentication());
        if( null != username ){
            UserDetails userDetails = userService.loadUserByUsername(username);
            System.out.println("Validating request ");
            if(jwtUtility.validateToken(token,userDetails)){
                System.out.println("Jwt validated token");
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            filterChain.doFilter(request,response);
        }
        System.out.println("filtered");
    }
}
