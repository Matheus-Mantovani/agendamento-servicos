package br.com.matheus.agendamentoservicos.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

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
	
	public Agendamento(Long id, long clienteId, long prestadorId, long servicoId, LocalDate data, LocalTime horaInicio,
			StatusServico status, String observacoes) {
		if(id != null) {
			this.id = id;
		}
		this.clienteId = clienteId;
		this.prestadorId = prestadorId;
		this.servicoId = servicoId;
		this.data = data;
		this.horaInicio = horaInicio;
		this.status = status;
		this.observacoes = observacoes;
	}
	
	public Agendamento(long clienteId, long prestadorId, long servicoId, LocalDate data, LocalTime horaInicio,
			StatusServico status, String observacoes) {
		this(null, clienteId, prestadorId, servicoId, data, horaInicio, status, observacoes);
	}
	
	public Agendamento(long clienteId, long prestadorId, long servicoId, LocalDate data, LocalTime horaInicio,
			StatusServico status) {
		this(null, clienteId, prestadorId, servicoId, data, horaInicio, status, null);
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClienteId() {
		return clienteId;
	}

	public void setClienteId(long clienteId) {
		this.clienteId = clienteId;
	}

	public long getPrestadorId() {
		return prestadorId;
	}

	public void setPrestadorId(long prestadorId) {
		this.prestadorId = prestadorId;
	}

	public long getServicoId() {
		return servicoId;
	}

	public void setServicoId(long servicoId) {
		this.servicoId = servicoId;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public StatusServico getStatus() {
		return status;
	}

	public void setStatus(StatusServico status) {
		this.status = status;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clienteId, data, horaInicio, id, observacoes, prestadorId, servicoId, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		return clienteId == other.clienteId && Objects.equals(data, other.data)
				&& Objects.equals(horaInicio, other.horaInicio) && id == other.id
				&& Objects.equals(observacoes, other.observacoes) && prestadorId == other.prestadorId
				&& servicoId == other.servicoId && status == other.status;
	}
	
	
}
