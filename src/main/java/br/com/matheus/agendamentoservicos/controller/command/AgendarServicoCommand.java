package br.com.matheus.agendamentoservicos.controller.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import br.com.matheus.agendamentoservicos.model.dao.agendamento.AgendamentoDAOFactory;
import br.com.matheus.agendamentoservicos.model.dao.disponibilidade.DisponibilidadeDAOFactory;
import br.com.matheus.agendamentoservicos.model.dao.servico.ServicoDAOFactory;
import br.com.matheus.agendamentoservicos.model.entity.Agendamento;
import br.com.matheus.agendamentoservicos.model.entity.Cliente;
import br.com.matheus.agendamentoservicos.model.enums.StatusServico;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AgendarServicoCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var session = request.getSession();
		var clienteId = ((Cliente) session.getAttribute("cliente")).getId();
		var prestadorIdStr = request.getParameter("prestadorId");
		var servicoIdStr = request.getParameter("servicoId");
		var dataStr = request.getParameter("data");
		var horaStr = request.getParameter("horario");
		
		if(parametrosInvalidos(prestadorIdStr, servicoIdStr, dataStr, horaStr)) {
			return "erro.jsp";
		}

		var prestadorId = Long.parseLong(prestadorIdStr);
		var servicoId = Long.parseLong(servicoIdStr);
		var data = LocalDate.parse(dataStr);
		var hora = LocalTime.parse(horaStr);
		
		var agendamento = new Agendamento(clienteId, prestadorId, servicoId, data, hora, StatusServico.PENDENTE);

		var daoServico = ServicoDAOFactory.create();
		var daoDisponibilidade = DisponibilidadeDAOFactory.create();
		var daoAgendamento = AgendamentoDAOFactory.create(daoServico, daoDisponibilidade);
		
		if(daoAgendamento.create(agendamento)) {
			request.setAttribute("sucesso", "Agendamento feito com sucesso!");
		} else {
			request.setAttribute("erro", "Erro ao realizar agendamento. Tente novamente mais tarde.");
		}
		
		return new GetServicosPageCommand().execute(request, response);
	}

	private boolean parametrosInvalidos(String prestadorIdStr, String servicoIdStr, String dataStr, String horaStr) {
		return prestadorIdStr == null || prestadorIdStr.isBlank() ||
				servicoIdStr == null || servicoIdStr.isBlank() ||
				dataStr == null || dataStr.isBlank() ||
				horaStr == null || horaStr.isBlank();
	}

}
