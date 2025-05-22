package br.com.matheus.agendamentoservicos.controller.command;

import java.io.IOException;

import br.com.matheus.agendamentoservicos.model.dao.cliente.ClienteDAOFactory;
import br.com.matheus.agendamentoservicos.model.entity.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CadastroClienteCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var nome = request.getParameter("nome");
		var email = request.getParameter("email");
		var telefone = request.getParameter("telefone");
		var senha = request.getParameter("senha");
		
		var dao = ClienteDAOFactory.create();
		
		if(dao.findByEmail(email) != null) {
			request.setAttribute("erro", "E-mail já está em uso!");
		} else {
			var cliente = new Cliente(nome, email, telefone, senha);
			var sucesso = dao.create(cliente);
			
			request.setAttribute("sucesso", sucesso);
		}
		
		return "cadastro-cliente-form.jsp";
	}
}
