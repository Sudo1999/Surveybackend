package survey.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import survey.backend.exception.JwtTokenMissingException;
import survey.backend.service.implement.UserAuthService;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // JwtAuthenticationFilter ressemble à un contrôleur quand il appelle la fonction loadUserByUsername() du service
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserAuthService userAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Get HTTP Header
        String header = request.getHeader("Authorization");
        // Is it a HTTP_TOKEN ?
        //if (header == null || !header.startsWith("HTTP_TOKEN")) {
        if (header == null || !header.startsWith("Bearer")) {
            throw new JwtTokenMissingException("No JWT token found in the request headers");
        }
        //String token = header.substring("HTTP_TOKEN".length() + 1);
        String token = header.substring("Bearer".length() + 1);
        // Optional - verification
        jwtUtil.validateToken(token);
        String userName = jwtUtil.getUserName(token);
        UserDetails userDetails = userAuthService.loadUserByUsername(userName);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
