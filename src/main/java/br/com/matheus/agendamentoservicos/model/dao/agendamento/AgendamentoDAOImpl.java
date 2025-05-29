package br.com.matheus.agendamentoservicos.model.dao.agendamento;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import br.com.matheus.agendamentoservicos.model.dao.disponibilidade.DisponibilidadeDAO;
import br.com.matheus.agendamentoservicos.model.dao.servico.ServicoDAO;
import br.com.matheus.agendamentoservicos.model.entity.Agendamento;
import br.com.matheus.agendamentoservicos.model.entity.Servico;
import br.com.matheus.agendamentoservicos.model.enums.StatusServico;
import br.com.matheus.agendamentoservicos.util.ConnectionFactory;

public class AgendamentoDAOImpl implements AgendamentoDAO {
	private static final String INSERT_AGENDAMENTO_SQL = "INSERT INTO agendamento(cliente_id, prestador_id, servico_id, data, hora_inicio, status, observacoes) VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STATUS_SQL = "UPDATE agendamento SET status = ? WHERE id = ?";
	private static final String FIND_BY_ID_SQL = "SELECT id, cliente_id, prestador_id, servico_id, data, hora_inicio, status, observacoes FROM agendamento WHERE id = ?";
	private static final String BUSCAR_AGENDAMENTOS_DATA_SQL = "SELECT hora_inicio FROM agendamento WHERE data = ? AND status NOT LIKE ?";
	private static final String BUSCAR_AGENDAMENTOS_PRESTADOR_SQL = "SELECT id, cliente_id, prestador_id, servico_id, data, hora_inicio, status, observacoes FROM agendamento WHERE prestador_id = ?";
	private static final String BUSCAR_AGENDAMENTOS_CLIENTE_SQL = "SELECT id, cliente_id, prestador_id, servico_id, data, hora_inicio, status, observacoes FROM agendamento WHERE cliente_id = ?";
	
	public AgendamentoDAOImpl() {}

	@Override
	public boolean create(Agendamento agendamento) {
		
		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(INSERT_AGENDAMENTO_SQL)) {
			
			preparedStatement.setLong(1, agendamento.getClienteId());
			preparedStatement.setLong(2, agendamento.getPrestadorId());
			preparedStatement.setLong(3, agendamento.getServicoId());
			preparedStatement.setDate(4, java.sql.Date.valueOf(agendamento.getData()));
			preparedStatement.setTime(5, java.sql.Time.valueOf(agendamento.getHoraInicio()));
			preparedStatement.setString(6, agendamento.getStatus().toString());
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
		
		try(var conn = ConnectionFactory.getConnection();
				var stmt = conn.prepareStatement(UPDATE_STATUS_SQL)) {
			System.out.println(status.getName());
			stmt.setString(1, status.getName());
			stmt.setLong(2, agendamento.getId());
			
			return stmt.executeUpdate() > 0;
			
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		
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

	@Override
	public List<LocalTime> buscarHorariosAgendados(LocalDate data) {
		List<LocalTime> agendamentosMarcados = new ArrayList<>();
		
		try(var conn = ConnectionFactory.getConnection();
				var stmt = conn.prepareStatement(BUSCAR_AGENDAMENTOS_DATA_SQL)) {
			
			stmt.setDate(1, Date.valueOf(data));
			stmt.setString(2, StatusServico.REJEITADO.toString());
			
			try(var rs = stmt.executeQuery()) {
				while(rs.next()) {
					agendamentosMarcados.add(rs.getTime(1).toLocalTime());
				}
			}
			
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		
		
		return agendamentosMarcados;
	}

	@Override
	public List<Agendamento> buscarAgendamentosPrestador(long id) {
		List<Agendamento> agendamentos = new ArrayList<>();
		
		try(var conn = ConnectionFactory.getConnection();
				var stmt = conn.prepareStatement(BUSCAR_AGENDAMENTOS_PRESTADOR_SQL)) {
			
			stmt.setLong(1, id);
			
			try(var rs = stmt.executeQuery()) {
				while(rs.next()) {
					Agendamento a = new Agendamento();
					a.setId(rs.getLong(1));
					a.setClienteId(rs.getLong(2));
					a.setPrestadorId(rs.getLong(3));
					a.setServicoId(rs.getLong(4));
					a.setData(rs.getDate(5).toLocalDate());
					a.setHoraInicio(rs.getTime(6).toLocalTime());
					a.setStatus(StatusServico.parseStatus(rs.getString(7)));
					a.setObservacoes(rs.getString(8));
					agendamentos.add(a);
				}
			}
			
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		
		return agendamentos;
	}

	@Override
	public List<Agendamento> buscarAgendamentosCliente(long id) {
		List<Agendamento> agendamentos = new ArrayList<>();
		
		try(var conn = ConnectionFactory.getConnection();
				var stmt = conn.prepareStatement(BUSCAR_AGENDAMENTOS_CLIENTE_SQL)) {
			
			stmt.setLong(1, id);
			
			try(var rs = stmt.executeQuery()) {
				while(rs.next()) {
					Agendamento a = new Agendamento();
					a.setId(rs.getLong(1));
					a.setClienteId(rs.getLong(2));
					a.setPrestadorId(rs.getLong(3));
					a.setServicoId(rs.getLong(4));
					a.setData(rs.getDate(5).toLocalDate());
					a.setHoraInicio(rs.getTime(6).toLocalTime());
					a.setStatus(StatusServico.parseStatus(rs.getString(7)));
					a.setObservacoes(rs.getString(8));
					agendamentos.add(a);
				}
			}
			
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		
		return agendamentos;
	}
}
