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
	
	private static final String INSERT_SERVICO_SQL = "INSERT INTO servico(prestador_id, nome, descricao, imagem_url, preco, duracao_minutos) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String FIND_BY_ID_SQL = "SELECT id, prestador_id, nome, descricao, imagem_url, preco, duracao_minutos FROM servico WHERE id = ?";
	private static final String FIND_BY_PRESTADOR_ID_SQL = "SELECT id, prestador_id, nome, descricao, imagem_url, preco, duracao_minutos FROM servico WHERE prestador_id = ?";
	private static final String LISTAR_SERVICOS_SQL = "SELECT id, prestador_id, nome, descricao, imagem_url, preco, duracao_minutos FROM servico ORDER BY id ASC LIMIT ? OFFSET ?";
	private static final String LISTAR_SERVICOS_FILTRADOS_SQL = "SELECT id, prestador_id, nome, descricao, imagem_url, preco, duracao_minutos FROM servico ORDER BY id ASC LIMIT ? OFFSET ?";
	private static final String COUNT_TOTAL_SERVICOS_SQL = "SELECT COUNT(id) AS totalServicos FROM servico";
	private static final String COUNT_TOTAL_SERVICOS_FILTRADOS_SQL = "SELECT COUNT(id) AS totalServicos FROM servico";
	
	@Override
	public Servico create(Servico servico) {

	    try (var connection = ConnectionFactory.getConnection();
	         var preparedStatement = connection.prepareStatement(INSERT_SERVICO_SQL, java.sql.Statement.RETURN_GENERATED_KEYS)) {

	        preparedStatement.setLong(1, servico.getPrestadorId());
	        preparedStatement.setString(2, servico.getNome());
	        preparedStatement.setString(3, servico.getDescricao());
	        preparedStatement.setString(4, servico.getImagemUrl());
	        preparedStatement.setBigDecimal(5, servico.getPreco());
	        preparedStatement.setBigDecimal(6, servico.getDuracaoMinutos());

	        int affectedRows = preparedStatement.executeUpdate();

	        if (affectedRows > 0) {
	            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    servico.setId(generatedKeys.getLong(1));
	                }
	            }
	            return servico;
	        }

	    } catch (SQLException | NamingException e) {
	        e.printStackTrace();
	    }

	    return null;
	}


	@Override
	public Servico findById(long id) {
	    Servico servico = null;

	    try (var connection = ConnectionFactory.getConnection();
	    		var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {

	        preparedStatement.setLong(1, id);

	        ResultSet result = preparedStatement.executeQuery();

	        if (result.next()) {
	            servico = new Servico();
	            servico.setId(result.getLong(1));
	            servico.setPrestadorId(result.getLong(2));
	            servico.setNome(result.getString(3));
	            servico.setDescricao(result.getString(4));
	            servico.setImagemUrl(result.getString(5));
	            servico.setPreco(result.getBigDecimal(6));
	            servico.setDuracaoMinutos(result.getBigDecimal(7));
	        }

	    } catch (SQLException | NamingException e) {
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
	            servico.setPrestadorId(result.getLong(2));
	            servico.setNome(result.getString(3));
	            servico.setDescricao(result.getString(4));
	            servico.setImagemUrl(result.getString(5));
	            servico.setPreco(result.getBigDecimal(6));
	            servico.setDuracaoMinutos(result.getBigDecimal(7));
				servicos.add(servico);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return servicos;
	}


	@Override
	public List<Servico> listarServicos(int pagina, int servicosPorPagina) {
		List<Servico> servicos = new ArrayList<>();
		Servico servico;
		
		try(var conn = ConnectionFactory.getConnection();
				var stmt = conn.prepareStatement(LISTAR_SERVICOS_SQL)) {
		
			stmt.setInt(1, servicosPorPagina);
			stmt.setInt(2, servicosPorPagina * pagina);
			var rs = stmt.executeQuery();
			
			while(rs.next()) {
				servico = new Servico();
				servico.setId(rs.getLong(1));
				servico.setPrestadorId(rs.getLong(2));
				servico.setNome(rs.getString(3));
				servico.setDescricao(rs.getString(4));
				servico.setImagemUrl(rs.getString(5));
				servico.setPreco(rs.getBigDecimal(6));
				servico.setDuracaoMinutos(rs.getBigDecimal(7));
				servicos.add(servico);
			}
			
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		
		return servicos;
	}
	
	@Override
	public List<Servico> listarServicos(int pagina, int servicosPorPagina, String filtroNome, String filtroCidade) {
		StringBuilder LISTAR_SERVICOS_SQL = new StringBuilder("SELECT id, prestador_id, nome, descricao, imagem_url, preco, duracao_minutos FROM servico WHERE 1=1");
		List<Object> params = new ArrayList<>();
		List<Servico> servicos = new ArrayList<>();
		Servico servico;
		
		if(filtroNome != null && !filtroNome.isBlank()) {
			LISTAR_SERVICOS_SQL.append(" AND LOWER(nome) LIKE ?");
			params.add("%" + filtroNome.toLowerCase() + "%");
		}
		
		if(filtroCidade != null && !filtroCidade.isBlank()) {
			LISTAR_SERVICOS_SQL.append(" AND LOWER(cidade) LIKE ?");
			params.add("%" + filtroCidade.toLowerCase() + "%");
		}
		
		LISTAR_SERVICOS_SQL.append(" ORDER BY id ASC LIMIT ? OFFSET ?");
		params.add(servicosPorPagina);
		params.add(servicosPorPagina * pagina);
		
		try(var conn = ConnectionFactory.getConnection();
				var stmt = conn.prepareStatement(LISTAR_SERVICOS_SQL.toString())) {
			
			for(int i = 0; i < params.size(); i++) {
				stmt.setObject(i+1, params.get(i));
			}
			
			try(var rs = stmt.executeQuery()) {
				while(rs.next()) {
					servico = new Servico();
					servico.setId(rs.getLong(1));
					servico.setPrestadorId(rs.getLong(2));
					servico.setNome(rs.getString(3));
					servico.setDescricao(rs.getString(4));
					servico.setImagemUrl(rs.getString(5));
					servico.setPreco(rs.getBigDecimal(6));
					servico.setDuracaoMinutos(rs.getBigDecimal(7));
					servicos.add(servico);
				}
			}
			
			
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		
		return servicos;
	}

	@Override
	public int getTotalPaginas(int servicosPorPagina) {
		int totalPaginas = 0;
		
		try(var conn = ConnectionFactory.getConnection();
				var stmt = conn.prepareStatement(COUNT_TOTAL_SERVICOS_SQL)) {
			
			var rs = stmt.executeQuery();
			rs.next();
			totalPaginas = rs.getInt(1);
			totalPaginas /= servicosPorPagina;
			
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		
		return totalPaginas;
	}

	@Override
	public int getTotalPaginas(int servicosPorPagina, String filtroNome, String filtroCidade) {
		StringBuilder COUNT_TOTAL_SERVICOS_SQL = new StringBuilder("SELECT COUNT(id) AS totalServicos FROM servico WHERE 1=1");
		List<Object> params = new ArrayList<>();
		int totalPaginas = 0;
		
		if(filtroNome != null && !filtroNome.isBlank()) {
			COUNT_TOTAL_SERVICOS_SQL.append(" AND LOWER(nome) LIKE ?");
			params.add("%" + filtroNome.toLowerCase() + "%");
		}
		
		if(filtroCidade != null && !filtroCidade.isBlank()) {
			COUNT_TOTAL_SERVICOS_SQL.append(" AND LOWER(cidade) LIKE ?");
			params.add("%" + filtroCidade.toLowerCase() + "%");
		}
		
		try(var conn = ConnectionFactory.getConnection();
				var stmt = conn.prepareStatement(COUNT_TOTAL_SERVICOS_SQL.toString())) {
			
			for(int i = 0; i < params.size(); i++) {
				stmt.setObject(i+1, params.get(i));
			}
			
			try(var rs = stmt.executeQuery()) {
				if(rs.next()) {
					totalPaginas = rs.getInt(1);
					totalPaginas /= servicosPorPagina;
				}
			}
			
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		
		return totalPaginas;
	}
}
