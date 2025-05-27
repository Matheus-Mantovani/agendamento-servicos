package br.com.matheus.agendamentoservicos.model.dao.servico;

import java.util.List;

import br.com.matheus.agendamentoservicos.model.entity.Servico;

public interface ServicoDAO {
	
	public Servico create(Servico servico);
	
	public Servico findById(long id);
	
	public List<Servico> findByPrestadorId(long id);
	
	public List<Servico> listarServicos(int pagina, int servicosPorPagina);

	public List<Servico> listarServicos(int pagina, int servicosPorPagina, String filtroNome, String filtroCidade);
	
	public int getTotalPaginas(int servicosPorPagina);

	public int getTotalPaginas(int servicosPorPagina, String filtroNome, String filtroCidade);
}
