package org.buddy.backend.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class FirebaseFilter extends OncePerRequestFilter {
    private static final int TOKEN_START = 7;

    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            sendUnauthorizedResponse(response, "Missing or invalid Authorization header");
            return;
        }

        String token = header.substring(TOKEN_START);
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdTokenAsync(token).get();
            // SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info(decodedToken.getEmail());
            filterChain.doFilter(request, response);
        } catch (InterruptedException | ExecutionException e) {
            SecurityContextHolder.clearContext();
            sendUnauthorizedResponse(response, "Invalid token");
        }
    }
}