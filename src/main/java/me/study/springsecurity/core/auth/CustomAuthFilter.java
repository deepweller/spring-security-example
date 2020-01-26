package me.study.springsecurity.core.auth;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class CustomAuthFilter extends GenericFilterBean {
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            Cookie[] cookies = ((HttpServletRequest) request).getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("controller-cookie".equals(cookie.getName())) {
                        if ("login-success".equals(cookie.getValue())) {
                            System.out.println("ok");
                            //todo add auth
//                        SecurityContextHolder.getContext().setAuthentication();
                        }
                    }
                }
            }
        }

        chain.doFilter(request, response);
    }

}