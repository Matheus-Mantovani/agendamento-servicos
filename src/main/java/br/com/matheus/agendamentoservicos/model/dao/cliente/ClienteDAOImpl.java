package br.com.matheus.agendamentoservicos.model.dao.cliente;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import br.com.matheus.agendamentoservicos.model.entity.Cliente;
import br.com.matheus.agendamentoservicos.util.ConnectionFactory;

public class ClienteDAOImpl implements ClienteDAO {
	
	private static final String INSERT_CLIENTE_SQL = "INSERT INTO cliente(nome, email, telefone, senha) VALUES(?, ?, ?, ?)";
	private static final String FIND_BY_EMAIL_SQL = "SELECT id, nome, email, telefone, senha FROM cliente WHERE email = ?";

	@Override
	public boolean create(Cliente cliente) {
		
		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(INSERT_CLIENTE_SQL)) {

			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setString(2, cliente.getEmail());
			preparedStatement.setString(3, cliente.getTelefone());
			preparedStatement.setString(4, cliente.getSenha());
			
			return preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Cliente findByEmail(String email) {
		Cliente cliente = null;
		
		try(var connection = ConnectionFactory.getConnection();
				var preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_SQL)) {
			
			preparedStatement.setString(1, email);
			
			ResultSet result = preparedStatement.executeQuery();
			
			if(result.next()) {
				cliente = new Cliente();
				cliente.setId(result.getLong(1));
				cliente.setNome(result.getString(2));
				cliente.setEmail(result.getString(3));
				cliente.setTelefone(result.getString(4));
				cliente.setSenha(result.getString(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return cliente;
	}

}
