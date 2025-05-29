package br.com.matheus.agendamentoservicos.model.enums;

public enum StatusServico{
	PENDENTE("pendente", 1),
	ACEITO("aceito", 2),
	REJEITADO("rejeitado", 3),
	CONCLUIDO("concluído", 4);

	private String name;
	private int prioridade;
	
	StatusServico(String name, int prioridade) {
		this.name = name;
		this.prioridade = prioridade;
	}

	public String getName() {
		return name;
	}
	
	public int getPrioridade() {
		return prioridade;
	}
	
	public static StatusServico parseStatus(String string) {
		switch (string.toLowerCase()) {
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
