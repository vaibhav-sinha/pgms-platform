package com.pgms.manager.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by user-1 on 18/7/15.
 */
@Component
public class AjaxAwareAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    public AjaxAwareAuthenticationEntryPoint() {
        super("/login");
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (isAjax(request)) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Please re-authenticate yourself");
        } else {
            super.commence(request, response, authException);
        }
    }

    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}
