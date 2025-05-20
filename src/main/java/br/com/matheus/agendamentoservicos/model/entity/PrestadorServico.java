package br.com.matheus.agendamentoservicos.model.entity;

public class PrestadorServico {
	private long id;
	private long prestadorId;
	private long clienteId;
	
	public PrestadorServico() {}
	
	public PrestadorServico(long id, long prestadorId, long clienteId) {
		this.id = id;
		this.prestadorId = prestadorId;
		this.clienteId = clienteId;
	}
}
