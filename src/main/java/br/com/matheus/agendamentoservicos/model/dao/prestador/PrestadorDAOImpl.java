package br.com.matheus.agendamentoservicos.model.dao.prestador;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import br.com.matheus.agendamentoservicos.model.entity.Prestador;
import br.com.matheus.agendamentoservicos.util.ConnectionFactory;

public class PrestadorDAOImpl implements PrestadorDAO {

	private static final String INSERT_PRESTADOR_SQL = "INSERT INTO "
			+ "prestador(nome, email, telefone, cpf, especialidade, senha) "
			+ "VALUES(?, ?, ?, ?, ?, ?)";
	private static final String FIND_BY_EMAIL_SQL = "SELECT id, nome, email, telefone, cpf, especialidade, senha FROM prestador WHERE email = ?";

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
}
