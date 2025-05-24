package br.com.matheus.agendamentoservicos.controller.command;

import java.io.IOException;

import br.com.matheus.agendamentoservicos.model.dao.cliente.ClienteDAOFactory;
import br.com.matheus.agendamentoservicos.model.entity.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginClienteCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var email = request.getParameter("email");
		var senha = request.getParameter("senha");
		
		var clienteDao = ClienteDAOFactory.create();
		var cliente = clienteDao.findByEmail(email);
		var autorizado = Cliente.authenticate(cliente, email, senha);
		
		String view;
		
		if (autorizado) {
			var session = request.getSession(true);
			session.setAttribute("cliente", cliente);
			view = "controller.do?action=servicos-page";
		} else {
			request.setAttribute("erro", "E-mail ou senha inválido.");
			view = "controller.do?action=login-cliente-page";
		}
		
		return view;
	}

}
