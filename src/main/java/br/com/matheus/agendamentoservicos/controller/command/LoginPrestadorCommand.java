package br.com.matheus.agendamentoservicos.controller.command;

import java.io.IOException;

import br.com.matheus.agendamentoservicos.model.dao.prestador.PrestadorDAOFactory;
import br.com.matheus.agendamentoservicos.model.entity.Prestador;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginPrestadorCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var email = request.getParameter("email");
		var senha = request.getParameter("senha");
		
		var prestadorDao = PrestadorDAOFactory.create();
		var prestador = prestadorDao.findByEmail(email);
		var autorizado = Prestador.authenticate(prestador, email, senha);
		
		String view;
		
		if (autorizado) {
			var session = request.getSession(true);
			session.removeAttribute("cliente");
			session.setAttribute("prestador", prestador);
			view = "controller.do?action=servicos-page";
		} else {
			request.setAttribute("erro", "E-mail ou senha inválido.");
			view = "controller.do?action=login-prestador-page";
		}
		
		return view;
	}

}
