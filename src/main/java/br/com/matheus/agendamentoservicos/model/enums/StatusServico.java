package br.com.matheus.agendamentoservicos.model.enums;

public enum StatusServico {
	PENDENTE("pendente"),
	ACEITO("aceito"),
	REJEITADO("rejeitado"),
	CONCLUIDO("concluído");

	private String name;
	
	StatusServico(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public static StatusServico parseStatus(String string) {
		switch (string) {
			case "pendente": {
				return PENDENTE;
			}
			case "aceito": {
				return ACEITO;
			}
			case "rejeitado": {
				return REJEITADO;
			}
			case "concluído": {
				return CONCLUIDO;
			}
		default:
			throw new IllegalArgumentException("Unexpected value: " + string);
		}
	}
}
