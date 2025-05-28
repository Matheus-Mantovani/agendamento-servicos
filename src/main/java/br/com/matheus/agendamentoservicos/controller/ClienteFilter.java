package br.com.matheus.agendamentoservicos.controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebFilter(urlPatterns = {"/controller.do"})
public class ClienteFilter extends HttpFilter implements Filter {
	private final Set<String> acoesProtegidas = Set.of("agendar-servico");
	
	private static final long serialVersionUID = 1L;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        String action = req.getParameter("action");

        boolean precisaAutenticacao = acoesProtegidas.contains(action);
        boolean logadoCliente = session != null && session.getAttribute("cliente") != null;
        boolean logadoPrestador = session != null && session.getAttribute("prestador") != null;

        //caso esteja logado como prestador, é enviado para a pagina de acesso restrito
        if (precisaAutenticacao && logadoPrestador) {
        	session = req.getSession();
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/acesso-restrito.jsp");
            return;
        }
        
        //caso nao esteja logado é enviado para o login de cliente
        if (precisaAutenticacao && !logadoCliente) {
        	session = req.getSession();
        	session.setAttribute("erro", "Você precisa estar logado como cliente para realizar essa ação.");
            ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login-cliente-form.jsp");
            return;
        }

        chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {}
	public void destroy() {}
}
