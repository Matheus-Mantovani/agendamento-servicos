package br.com.matheus.agendamentoservicos.model.entity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Cliente {
	private long id;
	private String nome;
	private String email;
	private String telefone;
	private String senha;
	
	public Cliente() {}

	public Cliente(long id, String nome, String email, String telefone, String senha, boolean fromDB) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;

		if (fromDB) {
			this.senha = senha;
        } else {
            this.senha = hashSHA256(senha);
        }
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public static boolean authenticate(Cliente cliente, String email, String password) {
		if (cliente != null) {
			return hashSHA256(password).equals(cliente.getSenha()) && email.equals(cliente.getEmail());
		}
		return false;
	}
	
	private static String hashSHA256(String password) {
		try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao criptografar");
        }
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nome, senha, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(nome, other.nome) && Objects.equals(senha, other.senha)
				&& Objects.equals(telefone, other.telefone);
	}
}
