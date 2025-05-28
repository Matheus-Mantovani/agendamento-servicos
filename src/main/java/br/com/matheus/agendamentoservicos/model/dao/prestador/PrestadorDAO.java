package br.com.matheus.agendamentoservicos.model.dao.prestador;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import br.com.matheus.agendamentoservicos.model.entity.Prestador;

public interface PrestadorDAO {

	boolean create(Prestador prestador);
	
	Prestador findByEmail(String email);
	
	Prestador findByCpf(String cpf);

	Prestador findById(long id);
	
	boolean horarioDisponivel(long prestadorId, List<Integer> diasSemana, LocalTime horaInicio, BigDecimal duracaoMinutos);
}
