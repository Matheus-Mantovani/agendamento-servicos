package br.com.matheus.agendamentoservicos.controller.command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import br.com.matheus.agendamentoservicos.model.dao.agendamento.AgendamentoDAOFactory;
import br.com.matheus.agendamentoservicos.model.dao.disponibilidade.DisponibilidadeDAOFactory;
import br.com.matheus.agendamentoservicos.model.dao.prestador.PrestadorDAOFactory;
import br.com.matheus.agendamentoservicos.model.dao.servico.ServicoDAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetAgendarServicoPageCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var servicoIdStr = request.getParameter("id");
		var dataSelecionadaStr = request.getParameter("data");
		
		if(servicoIdStr == null || servicoIdStr.isBlank()) return "erro.jsp";
		
		var servicoId = Long.parseLong(servicoIdStr);
		var daoServico = ServicoDAOFactory.create();
		var servico = daoServico.findById(servicoId);
		
		if(dataSelecionadaStr != null) {
			LocalDate dataSelecionada = LocalDate.parse(dataSelecionadaStr);
			int diaSemana = diaDaSemanaInt(dataSelecionada);
			
			var daoPrestador = PrestadorDAOFactory.create();
			var daoDisponibilidade = DisponibilidadeDAOFactory.create();
			var daoAgendamento = AgendamentoDAOFactory.create(daoServico, daoDisponibilidade);
			
			var prestador = daoPrestador.findById(servico.getPrestadorId());
			var horariosAgendados = daoAgendamento.buscarHorariosAgendados(dataSelecionada);
			var horariosDisponiveis = daoDisponibilidade.gerarHorariosDisponiveis(servicoId, diaSemana);
			
			horariosDisponiveis.removeAll(horariosAgendados);
			
			request.setAttribute("prestador", prestador);
			request.setAttribute("horariosDisponiveis", horariosDisponiveis);
			request.setAttribute("dataSelecionada", dataSelecionada);
		}
		
		List<String> diasSemana = Arrays.asList("D", "S", "T", "Q", "Q", "S", "S");
		List<Boolean> diasDisponiveis = new ArrayList<>();
		for (int i = 1; i <= 7; i++) {
		    diasDisponiveis.add(servico.getDiasDisponiveis().contains(i));
		}
		
		request.setAttribute("servico", servico);
		request.setAttribute("dataHoje", LocalDate.now());
		request.setAttribute("diasSemana", diasSemana);
		request.setAttribute("diasDisponiveis", diasDisponiveis);
		
		return "agendar-servico.jsp";
	}

	private int diaDaSemanaInt(LocalDate data) {
	    int dia = data.getDayOfWeek().getValue();
	    
	    return switch (dia) {
	        case 1 -> 2; 
	        case 2 -> 3; 
	        case 3 -> 4;
	        case 4 -> 5;
	        case 5 -> 6; 
	        case 6 -> 7; 
	        case 7 -> 1;
	        default -> throw new IllegalArgumentException("Dia inválido");
	    };
	}

}
