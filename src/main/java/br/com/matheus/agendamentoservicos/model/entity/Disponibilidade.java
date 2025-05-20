package br.com.matheus.agendamentoservicos.model.entity;

import java.time.LocalTime;

public class Disponibilidade {
	private long id;
	private long prestadorServicoId;
	private int diaSemana;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	
	public Disponibilidade() {}
	
	public Disponibilidade(long id, long prestadorServicoId, int diaSemana, LocalTime horaInicio, LocalTime horaFim) {
		this.id = id;
		this.prestadorServicoId = prestadorServicoId;
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}
}
