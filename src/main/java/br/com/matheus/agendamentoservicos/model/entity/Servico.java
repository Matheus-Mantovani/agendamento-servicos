package br.com.matheus.agendamentoservicos.model.entity;

public class Servico {
	private long id;
	private String nome;
	private String descricao;
	private double preco;
	private double duracaoMinutos;
	
	public Servico() {}
	
	public Servico(long id, String nome, String descricao, double preco, double duracaoMinutos) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.duracaoMinutos = duracaoMinutos;
	}
}
