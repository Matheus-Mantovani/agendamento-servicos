package br.com.matheus.agendamentoservicos.model.entity;

import java.time.LocalTime;
import java.util.Objects;

public class Disponibilidade {
	private long id;
	private long prestadorServicoId;
	private int diaSemana;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	
	public Disponibilidade() {}
	
	public Disponibilidade(Long prestadorServicoId, int diaSemana, LocalTime horaInicio, LocalTime horaFim) {
		this(null, prestadorServicoId, diaSemana, horaInicio, horaFim);
	}

	public Disponibilidade(Long id, Long prestadorServicoId, int diaSemana, LocalTime horaInicio,
			LocalTime horaFim) {
		super();
		if(id != null) {
			this.id = id;
		}
		this.prestadorServicoId = prestadorServicoId;
		this.diaSemana = diaSemana;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPrestadorServicoId() {
		return prestadorServicoId;
	}

	public void setPrestadorServicoId(Long prestadorServicoId) {
		this.prestadorServicoId = prestadorServicoId;
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}

	@Override
	public int hashCode() {
		return Objects.hash(diaSemana, horaFim, horaInicio, id, prestadorServicoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disponibilidade other = (Disponibilidade) obj;
		return diaSemana == other.diaSemana && Objects.equals(horaFim, other.horaFim)
				&& Objects.equals(horaInicio, other.horaInicio) && id == other.id
				&& prestadorServicoId == other.prestadorServicoId;
	}

	
}
