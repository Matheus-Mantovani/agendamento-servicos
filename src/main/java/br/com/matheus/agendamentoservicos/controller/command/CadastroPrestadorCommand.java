package br.com.matheus.agendamentoservicos.controller.command;

import java.io.IOException;

import br.com.matheus.agendamentoservicos.model.dao.cliente.ClienteDAOFactory;
import br.com.matheus.agendamentoservicos.model.dao.prestador.PrestadorDAOFactory;
import br.com.matheus.agendamentoservicos.model.entity.Prestador;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CadastroPrestadorCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var nome = request.getParameter("nome");
		var email = request.getParameter("email");
		var telefone = request.getParameter("telefone");
		var especialidade = request.getParameter("especialidade");
		var cpf = request.getParameter("cpf");
		var senha = request.getParameter("senha");
		
		var dao = PrestadorDAOFactory.create();
		
		if(dao.findByEmail(email) != null) {
			request.setAttribute("erro", "E-mail já está em uso!");
		} else if(dao.findByCpf(cpf) != null) {
			request.setAttribute("erro", "CPF já está em uso!");
		} else {
			var prestador = new Prestador(nome, email, telefone, cpf, especialidade, senha);
			var sucesso = dao.create(prestador);
			
			request.setAttribute("sucesso", sucesso);
		}
		
		return "cadastro-prestador-form.jsp";
	}

}
