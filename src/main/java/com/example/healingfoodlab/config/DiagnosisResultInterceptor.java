/*

package com.example.healingfoodlab.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class DiagnosisResultInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("uIdx"); // 로그인한 사용자 ID

        if (user == null) {
            System.out.println("🚨 비로그인 사용자 접근 차단: " + request.getRequestURI());
            response.sendRedirect("/user/login"); // 로그인 페이지로 리다이렉트
            return false;
        }

        System.out.println("✅ 로그인 인증 통과: " + request.getRequestURI());
        return true;
    }
}

*/
