package al.cit.supermarket.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessfulAuthenticationHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private MySessionAttributes sessionAttributes;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ADMIN")) {

            response.sendRedirect(servletContext.getContextPath() + "/admin/stores");
        } else if (roles.contains("USER")) {

            response.sendRedirect(servletContext.getContextPath() + "/");
        } else {

            SecurityContextHolder.clearContext();
            response.sendRedirect(servletContext.getContextPath() + "/login?error");
        }
    }

}