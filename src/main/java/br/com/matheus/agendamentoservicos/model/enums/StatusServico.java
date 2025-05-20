package br.com.matheus.agendamentoservicos.model.enums;

public enum StatusServico {
	PENDENTE("pendente"),
	ACEITO("aceito"),
	CONCLUIDO("concluído");

	private String name;
	
	StatusServico(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
