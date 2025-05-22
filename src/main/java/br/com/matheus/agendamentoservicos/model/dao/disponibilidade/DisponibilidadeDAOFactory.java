package br.com.matheus.agendamentoservicos.model.dao.disponibilidade;

public class DisponibilidadeDAOFactory {
	
	public static DisponibilidadeDAO create() {
		return new DisponibilidadeDAOImpl();
	}
}
