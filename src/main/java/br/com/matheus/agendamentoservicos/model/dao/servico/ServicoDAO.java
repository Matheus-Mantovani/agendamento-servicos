package br.com.matheus.agendamentoservicos.model.dao.servico;

import java.util.List;

import br.com.matheus.agendamentoservicos.model.entity.Servico;

public interface ServicoDAO {
	
	public boolean create(Servico servico);
	
	public Servico findById(long id);
	
	public List<Servico> findByPrestadorId(long id);
}
