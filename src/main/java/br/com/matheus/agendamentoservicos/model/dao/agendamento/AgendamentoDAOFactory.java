package br.com.matheus.agendamentoservicos.model.dao.agendamento;

import br.com.matheus.agendamentoservicos.model.dao.disponibilidade.DisponibilidadeDAO;
import br.com.matheus.agendamentoservicos.model.dao.servico.ServicoDAO;

public class AgendamentoDAOFactory {
	
	public static AgendamentoDAO create(ServicoDAO servicoDAO, DisponibilidadeDAO disponibilidadeDAO) {
		return new AgendamentoDAOImpl(servicoDAO, disponibilidadeDAO);
	}
}
