package br.com.matheus.agendamentoservicos.model.dao.cliente;

public class ClienteDAOFactory {

	public static ClienteDAO create() {
		return new ClienteDAOImpl();
	}
}
