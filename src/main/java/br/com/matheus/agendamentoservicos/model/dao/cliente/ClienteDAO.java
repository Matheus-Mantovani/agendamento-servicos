package br.com.matheus.agendamentoservicos.model.dao.cliente;

import br.com.matheus.agendamentoservicos.model.entity.Cliente;

public interface ClienteDAO {
	
	boolean create(Cliente cliente);
	
	Cliente findByEmail(String email);
}
