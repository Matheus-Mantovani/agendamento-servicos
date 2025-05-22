package br.com.matheus.agendamentoservicos.model.dao.servico;

public class ServicoDAOFactory {
	
	public static ServicoDAO create() {
		return new ServicoDAOImpl();
	}
}
