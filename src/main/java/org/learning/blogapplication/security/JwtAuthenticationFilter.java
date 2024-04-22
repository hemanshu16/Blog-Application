package org.learning.blogapplication.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.learning.blogapplication.entities.models.User;
import org.learning.blogapplication.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@NoArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Lazy
    private JwtTokenHelper jwtTokenHelper;

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. get token from request header
        String requestToken =  request.getHeader("Authorization");
        System.out.println(requestToken);
        String username = null;
        String token = null;
        if(requestToken != null && requestToken.startsWith("Bearer"))
        {
            token = requestToken.substring(7);
            try {
                username = jwtTokenHelper.getUserNameFromToken(token);
            }
            catch(IllegalArgumentException ex)
            {
                System.out.println("Unable to get JWT token");
            }
            catch(ExpiredJwtException ex)
            {
                System.out.println("Jwt token is expired");
            }
            catch(MalformedJwtException ex)
            {
                System.out.println("Jwt token is invalid");
            }
        }
        else{
            System.out.println("JWT token is not start with bearer");
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            System.out.println("Inside one step");
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(this.jwtTokenHelper.validateToken(token,userDetails))
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            else{
                System.out.println("jwt token is not valid");
            }
        }
        else{
            System.out.println("username is null or security context is not null");
        }
        filterChain.doFilter(request,response);
    }
}
