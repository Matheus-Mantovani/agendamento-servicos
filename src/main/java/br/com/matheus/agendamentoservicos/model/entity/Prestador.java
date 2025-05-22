package br.com.matheus.agendamentoservicos.model.entity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Prestador {
	private long id;
	private String nome;
	private String email;
	private String telefone;
	private String cpf;
	private String especialidade;
	private String senha;
	
	public Prestador() {}

	public Prestador(String nome, String email, String telefone, String cpf, String especialidade, String senha) {
		this(null, nome, email, telefone, cpf, especialidade, senha, false);
	}

	public Prestador(Long id, String nome, String email, String telefone, String cpf, String especialidade,
			String senha, boolean fromDB) {
		if(id != null) {
			this.id = id;
		}
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.cpf = cpf;
		this.especialidade = especialidade;
		
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public static boolean authenticate(Prestador prestador, String email, String password) {
		if (prestador != null) {
			return hashSHA256(password).equals(prestador.getSenha()) && email.equals(prestador.getEmail());
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
		return Objects.hash(cpf, email, especialidade, id, nome, senha, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestador other = (Prestador) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
				&& Objects.equals(especialidade, other.especialidade) && id == other.id
				&& Objects.equals(nome, other.nome) && Objects.equals(senha, other.senha)
				&& Objects.equals(telefone, other.telefone);
	}

	
}
