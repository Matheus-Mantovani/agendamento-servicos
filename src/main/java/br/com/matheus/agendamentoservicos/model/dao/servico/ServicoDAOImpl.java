package br.com.matheus.agendamentoservicos.model.dao.servico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import br.com.matheus.agendamentoservicos.model.entity.Cliente;
import br.com.matheus.agendamentoservicos.model.entity.Servico;
import br.com.matheus.agendamentoservicos.model.enums.StatusServico;
import br.com.matheus.agendamentoservicos.util.ConnectionFactory;

public class ServicoDAOImpl implements ServicoDAO {

	private static final String INSERT_SERVICO_SQL = "INSERT INTO servico(prestador_id, nome, descricao, preco, duracao_minutos) VALUES(?, ?, ?, ?, ?)";
	private static final String FIND_BY_ID_SQL = "SELECT id, prestador_id, nome, descricao, preco, duracao_minutos FROM servico WHERE id = ?";
	private static final String FIND_BY_PRESTADOR_ID_SQL = "SELECT id, prestador_id, nome, descricao, preco, duracao_minutos FROM servico WHERE prestador_id = ?";
	
	
	@Override
	public boolean create(Servico servico) {
		
		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(INSERT_SERVICO_SQL)) {

			preparedStatement.setLong(1, servico.getPrestadorId());
			preparedStatement.setString(2, servico.getNome());
			preparedStatement.setString(3, servico.getDescricao());
			preparedStatement.setBigDecimal(4, servico.getPreco());
			preparedStatement.setBigDecimal(5, servico.getDuracaoMinutos());
			
			return preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Servico findById(long id) {
		Servico servico = null;
		
		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
			
			preparedStatement.setLong(1, id);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if(result.next()) {
				servico = new Servico();
				servico.setId(result.getLong(1));
				servico.setNome(result.getString(2));
				servico.setDescricao(result.getString(3));
				servico.setPreco(result.getBigDecimal(4));
				servico.setDuracaoMinutos(result.getBigDecimal(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return servico;
	}
	
	@Override
	public List<Servico> findByPrestadorId(long id) {
		List<Servico> servicos = new ArrayList<>();
		Servico servico = null;
		
		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(FIND_BY_PRESTADOR_ID_SQL)) {
			
			preparedStatement.setLong(1, id);
			
			ResultSet result = preparedStatement.executeQuery();
			
			while(result.next()) {
				servico = new Servico();
				servico.setId(result.getLong(1));
				servico.setNome(result.getString(2));
				servico.setDescricao(result.getString(3));
				servico.setPreco(result.getBigDecimal(4));
				servico.setDuracaoMinutos(result.getBigDecimal(5));
				servicos.add(servico);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return servicos;
	}
}
