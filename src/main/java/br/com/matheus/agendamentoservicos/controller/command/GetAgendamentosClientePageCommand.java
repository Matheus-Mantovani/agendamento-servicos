package br.com.matheus.agendamentoservicos.controller.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.matheus.agendamentoservicos.model.dao.agendamento.AgendamentoDAOFactory;
import br.com.matheus.agendamentoservicos.model.dao.cliente.ClienteDAOFactory;
import br.com.matheus.agendamentoservicos.model.dao.prestador.PrestadorDAOFactory;
import br.com.matheus.agendamentoservicos.model.dao.servico.ServicoDAOFactory;
import br.com.matheus.agendamentoservicos.model.dto.AgendamentoDetalhado;
import br.com.matheus.agendamentoservicos.model.entity.Cliente;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetAgendamentosClientePageCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		var session = request.getSession();
		var id = ((Cliente) session.getAttribute("cliente")).getId();
		
		var daoAgendamento = AgendamentoDAOFactory.create();
		var daoCliente = ClienteDAOFactory.create();
		var daoPrestador = PrestadorDAOFactory.create();
		var daoServico = ServicoDAOFactory.create();
		
		var agendamentos = daoAgendamento.buscarAgendamentosCliente(id);
		List<AgendamentoDetalhado> agendamentosDetalhados = new ArrayList<>();
		
		agendamentos = agendamentos.stream()
				.sorted(Comparator.comparingInt(a -> a.getStatus().getPrioridade()))
				.collect(Collectors.toList());
		
		for(var a : agendamentos) {
			AgendamentoDetalhado ad = new AgendamentoDetalhado();
			ad.setAgendamento(a);
			ad.setNomeCliente(daoCliente.findById(a.getClienteId()).getNome());
			ad.setNomePrestador(daoPrestador.findById(a.getPrestadorId()).getNome());
			ad.setNomeServico(daoServico.findById(a.getServicoId()).getNome());
			agendamentosDetalhados.add(ad);
		}
		
		request.setAttribute("listaAgendamentos", agendamentosDetalhados);
		
		return "agendamentos-cliente.jsp";
	}

}
