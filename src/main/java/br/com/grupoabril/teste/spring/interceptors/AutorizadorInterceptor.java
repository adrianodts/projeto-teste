package br.com.grupoabril.teste.spring.interceptors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
		String uri = request.getRequestURI();		

		if (request.getSession().getAttribute("usuarioLogado") != null) {
			return true;
		}

		if (uri.equals("/clientes/listarAssinaturas")) { 
		    redirect(request, response, "/login/loginPage");
		    return false; 
		}
		return true;
    }
    
    private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException {
		try {
			response.sendRedirect(request.getContextPath() + path);
		} catch (java.io.IOException e) {
			throw new ServletException(e);
		}
    }

}