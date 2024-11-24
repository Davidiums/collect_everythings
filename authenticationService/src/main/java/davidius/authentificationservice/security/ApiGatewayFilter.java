package davidius.authentificationservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ApiGatewayFilter extends OncePerRequestFilter {
    private static final String API_GATEWAY_IP = "127.0.0.1";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String remoteAddr = request.getRemoteAddr();

        if (!API_GATEWAY_IP.equals(remoteAddr)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
