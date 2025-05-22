package br.com.matheus.agendamentoservicos.model.dao.prestador;

public class PrestadorDAOFactory {

	public static PrestadorDAO create() {
		return new PrestadorDAOImpl();
	}
}
