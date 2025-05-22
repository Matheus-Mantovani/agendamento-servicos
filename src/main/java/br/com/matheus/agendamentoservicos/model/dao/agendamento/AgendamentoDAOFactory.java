package br.com.matheus.agendamentoservicos.model.dao.agendamento;

public class AgendamentoDAOFactory {
	
	public static AgendamentoDAO create() {
		return new AgendamentoDAOImpl();
	}
}
