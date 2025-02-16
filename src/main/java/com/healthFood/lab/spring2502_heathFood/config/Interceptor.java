
package com.healthFood.lab.spring2502_heathFood.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("LoginMemberEmail");  // ✅ 세션에서 로그인 ID 확인

        if (userId == null) {
            String requestURI = request.getRequestURI();
            String queryString = request.getQueryString();

            if (queryString != null) {
                requestURI += "?" + queryString;
            }

            System.out.println("🚨 비로그인 사용자 접근 차단: " + requestURI);

            // 로그인 전 원래 URL 저장
            session.setAttribute("redirectURL", requestURI);

            // 로그인 페이지로 이동
            response.sendRedirect("/usr/user/login");
            return false;
        }

        System.out.println("✅ 로그인 인증 통과: " + request.getRequestURI() + " (유저 ID: " + userId + ")");
        return true;
    }
}

