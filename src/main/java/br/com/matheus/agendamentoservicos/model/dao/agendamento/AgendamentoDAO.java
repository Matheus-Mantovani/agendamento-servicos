package br.com.matheus.agendamentoservicos.model.dao.agendamento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.matheus.agendamentoservicos.model.entity.Agendamento;
import br.com.matheus.agendamentoservicos.model.enums.StatusServico;

public interface AgendamentoDAO {
	
	public boolean create(Agendamento agendamento);
	
	public boolean updateStatus(Agendamento agendamento, StatusServico status);
	
	public Agendamento findById(long id);
	
	public List<LocalTime> buscarHorariosAgendados(LocalDate data);
}
