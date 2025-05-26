package br.com.matheus.agendamentoservicos.model.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Servico {
	private long id;
	private long prestadorId;
	private String nome;
	private String descricao;
	private String imagemUrl;
	private BigDecimal preco;
	private BigDecimal duracaoMinutos;
	private List<Integer> diasDisponiveis;
	
	public Servico() {}
	
	public Servico(long prestadorId, String nome, String descricao, String imagemUrl, BigDecimal preco, BigDecimal duracaoMinutos, List<Integer> diasDisponiveis) {
		this(null, prestadorId, nome, descricao, imagemUrl, preco, duracaoMinutos, diasDisponiveis);
	}

	public Servico(Long id, long prestadorId, String nome, String descricao, String imagemUrl, BigDecimal preco, BigDecimal duracaoMinutos, List<Integer> diasDisponiveis) {
		super();
		if(id != null) {
			this.id = id;
		}
		this.prestadorId = prestadorId;
		this.nome = nome;
		this.descricao = descricao;
		this.imagemUrl = imagemUrl;
		this.preco = preco;
		this.duracaoMinutos = duracaoMinutos;
		this.diasDisponiveis = diasDisponiveis;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPrestadorId() {
		return prestadorId;
	}

	public void setPrestadorId(long prestadorId) {
		this.prestadorId = prestadorId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getDuracaoMinutos() {
		return duracaoMinutos;
	}

	public void setDuracaoMinutos(BigDecimal duracaoMinutos) {
		this.duracaoMinutos = duracaoMinutos;
	}

	public List<Integer> getDiasDisponiveis() {
		return diasDisponiveis;
	}

	public void setDiasDisponiveis(List<Integer> diasDisponiveis) {
		this.diasDisponiveis = diasDisponiveis;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, diasDisponiveis, duracaoMinutos, id, imagemUrl, nome, preco, prestadorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servico other = (Servico) obj;
		return Objects.equals(descricao, other.descricao) && Objects.equals(diasDisponiveis, other.diasDisponiveis)
				&& Objects.equals(duracaoMinutos, other.duracaoMinutos) && id == other.id
				&& Objects.equals(imagemUrl, other.imagemUrl) && Objects.equals(nome, other.nome)
				&& Objects.equals(preco, other.preco) && prestadorId == other.prestadorId;
	}
}
