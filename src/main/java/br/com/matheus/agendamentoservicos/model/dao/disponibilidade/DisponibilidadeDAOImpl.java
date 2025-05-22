package br.com.matheus.agendamentoservicos.model.dao.disponibilidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import javax.naming.NamingException;

import br.com.matheus.agendamentoservicos.model.entity.Disponibilidade;
import br.com.matheus.agendamentoservicos.util.ConnectionFactory;

public class DisponibilidadeDAOImpl implements DisponibilidadeDAO {
	
	private static final String INSERT_DISPONIBILIDADE_SQL = "INSERT INTO "
			+ "disponibilidade(prestador_servico_id, dia_semana, hora_inicio, hora_fim) "
			+ "VALUES(?, ?, ?, ?)";
	private static final String FIND_BY_ID_SQL = "SELECT id, prestador_servico_id, dia_semana, hora_inicio, hora_fim "
			+ "FROM disponibilidade WHERE id = ?";


	@Override
	public boolean create(Disponibilidade disponibilidade) {

		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(INSERT_DISPONIBILIDADE_SQL)) {
			
			preparedStatement.setLong(1, disponibilidade.getPrestadorServicoId());
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
				disponibilidade.setPrestadorServicoId(result.getLong(2));
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
	public boolean verificarHorarios(long prestadorId, LocalTime horarioInicio, LocalTime horarioFim) {
		// TODO Auto-generated method stub
		return false;
	}

}
