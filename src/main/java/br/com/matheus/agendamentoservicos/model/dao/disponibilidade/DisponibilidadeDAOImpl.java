package br.com.matheus.agendamentoservicos.model.dao.disponibilidade;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.naming.NamingException;

import br.com.matheus.agendamentoservicos.model.entity.Disponibilidade;
import br.com.matheus.agendamentoservicos.util.ConnectionFactory;

public class DisponibilidadeDAOImpl implements DisponibilidadeDAO {
	
	private static final String INSERT_DISPONIBILIDADE_SQL = "INSERT INTO "
			+ "disponibilidade(servico_id, dia_semana, hora_inicio, hora_fim) "
			+ "VALUES(?, ?, ?, ?)";
	private static final String FIND_BY_ID_SQL = "SELECT id, servico_id, dia_semana, hora_inicio, hora_fim "
			+ "FROM disponibilidade WHERE id = ?";
	private static final String VERIFICAR_HORARIO_DISPONIVEL_SQL = "SELECT COUNT(a.id) AS agendamentos_existentes " +
            "FROM agendamento a " +
            "JOIN servico s ON a.servico_id = s.id " +
            "WHERE a.prestador_id = ? " +
            "AND a.servico_id = ? " +
            "AND a.data = ? " +
            "AND ( " +
            "    (a.hora_inicio <= ? AND ADDTIME(a.hora_inicio, SEC_TO_TIME(s.duracao_minutos * 60)) > ?) " +
            "    OR " +
            "    (a.hora_inicio >= ? AND a.hora_inicio < ADDTIME(?, SEC_TO_TIME((SELECT duracao_minutos FROM servico WHERE id = ?) * 60))) " +
            ") " +
            "AND a.status NOT IN ('CANCELADO', 'REJEITADO')";


	@Override
	public boolean create(Disponibilidade disponibilidade) {

		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(INSERT_DISPONIBILIDADE_SQL)) {
			
			preparedStatement.setLong(1, disponibilidade.getServicoId());
			preparedStatement.setInt(2, disponibilidade.getDiaSemana());
			preparedStatement.setTime(3, Time.valueOf(disponibilidade.getHoraInicio()));
			preparedStatement.setTime(4, Time.valueOf(disponibilidade.getHoraFim()));
			
			return preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Disponibilidade findById(long id) {
		Disponibilidade disponibilidade = null;
		
		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
			
			preparedStatement.setLong(1, id);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if(result.next()) {
				disponibilidade = new Disponibilidade();
				disponibilidade.setId(result.getLong(1));
				disponibilidade.setServicoId(result.getLong(2));
				disponibilidade.setDiaSemana(result.getInt(3));
				disponibilidade.setHoraInicio(result.getTime(4).toLocalTime());
				disponibilidade.setHoraFim(result.getTime(5).toLocalTime());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return disponibilidade;
	}

	@Override
	public boolean verificarHorarioDisponivel(long prestadorId, long servicoId, LocalDate data, LocalTime horaInicio) {
		try (var connection = ConnectionFactory.getConnection();
		         var preparedStatement = connection.prepareStatement(VERIFICAR_HORARIO_DISPONIVEL_SQL)) {
		
			Time sqlHoraInicio = Time.valueOf(horaInicio);
		    
			preparedStatement.setLong(1, prestadorId);
			preparedStatement.setLong(2, servicoId);
			preparedStatement.setDate(3, Date.valueOf(data));
			preparedStatement.setTime(4, sqlHoraInicio);
			preparedStatement.setTime(5, sqlHoraInicio);
			preparedStatement.setTime(6, sqlHoraInicio);
			preparedStatement.setTime(7, sqlHoraInicio);
			preparedStatement.setLong(8, servicoId);
		    
		    try (ResultSet rs = preparedStatement.executeQuery()) {
		        return !rs.next() || rs.getInt("agendamentos_existentes") == 0;
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		    return false;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return false;
	}
}
