package br.com.matheus.agendamentoservicos.model.dao.agendamento;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import br.com.matheus.agendamentoservicos.model.entity.Agendamento;
import br.com.matheus.agendamentoservicos.model.entity.Servico;
import br.com.matheus.agendamentoservicos.model.enums.StatusServico;
import br.com.matheus.agendamentoservicos.util.ConnectionFactory;

public class AgendamentoDAOImpl implements AgendamentoDAO {

	private static final String INSERT_AGENDAMENTO_SQL = "INSERT INTO agendamento(cliente_id, prestador_id, servico_id, data, hora_inicio, status, observacoes) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STATUS_SQL = "UPDATE agendamento SET status = ? WHERE id = ?";
	private static final String FIND_BY_ID_SQL = "SELECT id, cliente_id, prestador_id, servico_id, data, hora_inicio, status, observacoes FROM agendamento WHERE id = ?";

	@Override
	public boolean create(Agendamento agendamento) {
		
		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(INSERT_AGENDAMENTO_SQL)) {

			preparedStatement.setLong(1, agendamento.getClienteId());
			preparedStatement.setLong(2, agendamento.getPrestadorId());
			preparedStatement.setLong(3, agendamento.getServicoId());
			preparedStatement.setDate(4, java.sql.Date.valueOf(agendamento.getData()));
			preparedStatement.setTime(5, java.sql.Time.valueOf(agendamento.getHoraInicio()));
			preparedStatement.setString(6, agendamento.getStatus().name());
			preparedStatement.setString(7, agendamento.getObservacoes());
			
			return preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean updateStatus(Agendamento agendamento, StatusServico status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Agendamento findById(long id) {
		Agendamento agendamento = null;
		
		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
			
			preparedStatement.setLong(1, id);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if(result.next()) {
				agendamento = new Agendamento();
				agendamento.setId(result.getLong(1));
				agendamento.setClienteId(result.getLong(2));
				agendamento.setPrestadorId(result.getLong(3));
				agendamento.setServicoId(result.getLong(4));
				agendamento.setData(result.getDate(5).toLocalDate());
				agendamento.setHoraInicio(result.getTime(6).toLocalTime());
				agendamento.setStatus(StatusServico.parseStatus(result.getString(7)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return agendamento;
	}
}
