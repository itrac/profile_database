package br.unb.itrac.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) {
		String uri = request.getRequestURI();
		if(uri.endsWith("sign_in") || uri.contains("webjars") || uri.contains("resources") || uri.endsWith("attempt")) {
			return true;
		}
		
		if(request.getSession().getAttribute("role") != null) {
			return true;
		}
		
		try {
			response.sendRedirect("/itrac/sign_in");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}
