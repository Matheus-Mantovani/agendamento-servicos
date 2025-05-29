package br.com.matheus.agendamentoservicos.controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

@WebFilter(urlPatterns = { "/controller.do" })
public class PrestadorFilter extends HttpFilter implements Filter {
	private final Set<String> acoesProtegidas = Set.of("cadastro-servico", "cadastro-servico-page",
			"agendamentos-prestador-page", "aceitar-agendamento", "recusar-agendamento", "concluir-agendamento");

	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		String action = req.getParameter("action");

		boolean precisaAutenticacao = acoesProtegidas.contains(action);
		boolean logadoPrestador = session != null && session.getAttribute("prestador") != null;
		boolean logadoCliente = session != null && session.getAttribute("cliente") != null;

		// caso esteja logado como cliente, é enviado para a pagina de acesso restrito
		if (precisaAutenticacao && logadoCliente) {
			session = req.getSession();
			((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/acesso-restrito.jsp");
			return;
		}

		// caso nao esteja logado é enviado para o login de prestador
		if (precisaAutenticacao && !logadoPrestador) {
			session = req.getSession();
			session.setAttribute("erro",
					"Você precisa estar logado como prestador de serviço para realizar essa ação.");
			((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login-prestador-form.jsp");
			return;
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	public void destroy() {
	}
}
