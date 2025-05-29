package br.com.matheus.agendamentoservicos.controller.command;

import java.io.IOException;

import br.com.matheus.agendamentoservicos.model.dao.agendamento.AgendamentoDAOFactory;
import br.com.matheus.agendamentoservicos.model.enums.StatusServico;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RecusarAgendamentoCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var idStr = request.getParameter("id");
		
		if(idStr == null || idStr.isBlank()) {
			return "erro.jsp";
		}
		
		var id = Long.parseLong(idStr);
		var dao = AgendamentoDAOFactory.create();
		var agendamento = dao.findById(id);
		dao.updateStatus(agendamento, StatusServico.REJEITADO);
		
		return new GetAgendamentosPrestadorPageCommand().execute(request, response);
	}

}
