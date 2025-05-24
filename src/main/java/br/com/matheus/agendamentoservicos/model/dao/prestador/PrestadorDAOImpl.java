package br.com.matheus.agendamentoservicos.model.dao.prestador;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import javax.naming.NamingException;

import br.com.matheus.agendamentoservicos.model.entity.Prestador;
import br.com.matheus.agendamentoservicos.util.ConnectionFactory;

public class PrestadorDAOImpl implements PrestadorDAO {

	private static final String INSERT_PRESTADOR_SQL = "INSERT INTO "
			+ "prestador(nome, email, telefone, cpf, especialidade, senha) "
			+ "VALUES(?, ?, ?, ?, ?, ?)";
	private static final String FIND_BY_EMAIL_SQL = "SELECT id, nome, email, telefone, cpf, especialidade, senha FROM prestador WHERE email = ?";
	private static final String VERIFICAR_CONFLITO_HORARIO = "SELECT EXISTS ("
			+ "    SELECT 1"
			+ "    FROM disponibilidade d"
			+ "    JOIN servico s ON d.servico_id = s.id"
			+ "    WHERE s.prestador_id = ?"
			+ "      AND d.dia_semana = ?"
			+ "      AND ("
			+ "          (d.hora_inicio <= ? AND ADDTIME(d.hora_inicio, SEC_TO_TIME(s.duracao_minutos * 60)) > ?)"
			+ "          OR"
			+ "          (d.hora_inicio >= ? AND d.hora_inicio < ADDTIME(?, SEC_TO_TIME(? * 60)))"
			+ "      )"
			+ ") AS possui_conflito";
	
	@Override
	public boolean create(Prestador prestador) {
		
		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(INSERT_PRESTADOR_SQL)) {

			preparedStatement.setString(1, prestador.getNome());
			preparedStatement.setString(2, prestador.getEmail());
			preparedStatement.setString(3, prestador.getTelefone());
			preparedStatement.setString(4, prestador.getCpf());
			preparedStatement.setString(5, prestador.getEspecialidade());
			preparedStatement.setString(6, prestador.getSenha());
			
			return preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Prestador findByEmail(String email) {
		Prestador prestador = null;
		
		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_SQL)) {
			
			preparedStatement.setString(1, email);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if(result.next()) {
				prestador = new Prestador();
				prestador.setId(result.getLong(1));
				prestador.setNome(result.getString(2));
				prestador.setEmail(result.getString(3));
				prestador.setTelefone(result.getString(4));
				prestador.setCpf(result.getString(5));
				prestador.setEspecialidade(result.getString(6));
				prestador.setSenha(result.getString(7));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return prestador;
	}

	@Override
	public boolean horarioDisponivel(long prestadorId, List<Integer> diasSemana, LocalTime horaInicio, BigDecimal duracaoMinutos) {

		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(VERIFICAR_CONFLITO_HORARIO)) {
			
			preparedStatement.setLong(1, prestadorId);
			preparedStatement.setTime(3, Time.valueOf(horaInicio));
		    preparedStatement.setTime(4, Time.valueOf(horaInicio));
		    preparedStatement.setTime(5, Time.valueOf(horaInicio));
		    preparedStatement.setTime(6, Time.valueOf(horaInicio));
		    preparedStatement.setBigDecimal(7, duracaoMinutos);
	
			for(int dia : diasSemana) {
				preparedStatement.setInt(2, dia);
				var rs = preparedStatement.executeQuery();
				if(rs.next() && rs.getBoolean("possui_conflito")) return false;
				rs.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
