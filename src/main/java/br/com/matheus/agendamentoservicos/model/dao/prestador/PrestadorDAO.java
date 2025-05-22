package br.com.matheus.agendamentoservicos.model.dao.prestador;

import br.com.matheus.agendamentoservicos.model.entity.Prestador;

public interface PrestadorDAO {

	boolean create(Prestador prestador);
	
	Prestador findByEmail(String email);
}
