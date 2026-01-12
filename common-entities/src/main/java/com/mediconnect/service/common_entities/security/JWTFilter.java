package com.mediconnect.service.common_entities.security;

import com.mediconnect.service.common_entities.entity.UserEntity;
import com.mediconnect.service.common_entities.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration // ???
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserService userService;

    /*
        •	This method belongs to OncePerRequestFilter
        •	It executes before Spring Security authorization
        •	It decides whether your custom filter runs at all

        Meaning

        If it returns true:
        •	Your JWT filter is skipped
        •	No token parsing
        •	No authentication attempt
        •	No SecurityContext modification
     */

    /*
        Request
         ↓
        shouldNotFilter() → true
         ↓
        JwtAuthenticationFilter SKIPPED ✅
         ↓
        DispatcherServlet
         ↓
        Controller HIT
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return path.startsWith("/login")
                || path.startsWith("/register")
                || path.startsWith("/refreshToken")
                || path.startsWith("/actuator")
                ;
    }
    /*
    NEED OF THIS??
    - “permitAll() only disables authorization checks but does not bypass authentication filters.
    - Custom filters like JWT filters will still execute and may reject requests. shouldNotFilter() prevents the filter from running entirely,
        which is why unauthenticated endpoints like login or refresh-token must be excluded there.”
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization").split("Bearer ")[1];
        UserEntity user = jwtService.getUserFromToken(token);
        UserEntity userDetails = userService.getUser(user);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request, response);
    }
}
