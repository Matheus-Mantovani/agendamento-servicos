package br.com.matheus.agendamentoservicos.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.matheus.agendamentoservicos.model.enums.StatusServico;

public class Agendamento {
	private long id;
	private long clienteId;
	private long prestadorId;
	private long servicoId;
	private LocalDate data;
	private LocalTime horaInicio;
	private StatusServico status;
	private String observacoes;
	
	public Agendamento() {}
	
	public Agendamento(long id, long clienteId, long prestadorId, long servicoId, LocalDate data, LocalTime horaInicio,
			StatusServico status, String observacoes) {
		this.id = id;
		this.clienteId = clienteId;
		this.prestadorId = prestadorId;
		this.servicoId = servicoId;
		this.data = data;
		this.horaInicio = horaInicio;
		this.status = status;
		this.observacoes = observacoes;
	}
}
