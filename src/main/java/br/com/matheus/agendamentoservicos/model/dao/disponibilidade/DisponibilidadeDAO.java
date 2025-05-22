package br.com.matheus.agendamentoservicos.model.dao.disponibilidade;

import java.time.LocalTime;

import br.com.matheus.agendamentoservicos.model.entity.Disponibilidade;

public interface DisponibilidadeDAO {

	boolean create(Disponibilidade disponibilidade);
	
	Disponibilidade findById(long id);

	boolean verificarHorarios(long prestadorId, LocalTime horarioInicio, LocalTime horarioFim);
}
