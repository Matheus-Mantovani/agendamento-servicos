package br.com.matheus.agendamentoservicos.model.dto;

import java.util.Objects;

import br.com.matheus.agendamentoservicos.model.entity.Agendamento;

public class AgendamentoDetalhado {
	private Agendamento agendamento;
	private String nomeCliente;
	private String nomePrestador;
	private String nomeServico;
	
	public Agendamento getAgendamento() {
		return agendamento;
	}
	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomePrestador() {
		return nomePrestador;
	}
	public void setNomePrestador(String nomePrestador) {
		this.nomePrestador = nomePrestador;
	}
	public String getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
	@Override
	public int hashCode() {
		return Objects.hash(agendamento, nomeCliente, nomePrestador, nomeServico);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AgendamentoDetalhado other = (AgendamentoDetalhado) obj;
		return Objects.equals(agendamento, other.agendamento) && Objects.equals(nomeCliente, other.nomeCliente)
				&& Objects.equals(nomePrestador, other.nomePrestador) && Objects.equals(nomeServico, other.nomeServico);
	}
}
