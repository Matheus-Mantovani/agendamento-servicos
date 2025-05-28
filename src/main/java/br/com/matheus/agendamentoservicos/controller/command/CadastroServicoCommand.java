package br.com.matheus.agendamentoservicos.controller.command;

import java.io.IOException;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import br.com.matheus.agendamentoservicos.model.dao.disponibilidade.DisponibilidadeDAOFactory;
import br.com.matheus.agendamentoservicos.model.dao.prestador.PrestadorDAOFactory;
import br.com.matheus.agendamentoservicos.model.dao.servico.ServicoDAOFactory;
import br.com.matheus.agendamentoservicos.model.entity.Disponibilidade;
import br.com.matheus.agendamentoservicos.model.entity.Prestador;
import br.com.matheus.agendamentoservicos.model.entity.Servico;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class CadastroServicoCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> tiposPermitidos = Arrays.asList("image/png", "image/jpeg");
		var session = request.getSession(false);

		Prestador prestador = (Prestador) session.getAttribute("prestador");
		
		if(prestador == null) {
			return "acesso-restrito.jsp";
		}
		
		var prestadorIdString = prestador.getId();
		var nome = request.getParameter("nome");
		var descricao = request.getParameter("descricao");
		var precoString = request.getParameter("preco");
		var duracaoString = request.getParameter("duracao");
		var diasDisponiveisString = request.getParameterValues("diasDisponiveis");
		var horarioInicioString = request.getParameter("horarioInicio");
		var horarioFimString = request.getParameter("horarioFim");
		var imagemPart = request.getPart("imagemServico");
		
		var prestadorId = Long.valueOf(prestadorIdString);
		var preco = BigDecimal.valueOf(Double.valueOf(precoString));
		var duracao = BigDecimal.valueOf(Double.valueOf(duracaoString));
		var diasDisponiveisInt = diasStringToInt(diasDisponiveisString);
		var horarioInicio = LocalTime.parse(horarioInicioString);
		var horarioFim = LocalTime.parse(horarioFimString);
		var nomeImagem = gerarNome(imagemPart);
		
		String contentType = imagemPart.getContentType();
		File diretorio = new File("F:/ARQDSW2/agendamentoservicos/src/main/webapp/uploads/servico/");
		if(!diretorio.exists()) diretorio.mkdirs();
		String caminhoCompleto = new File(diretorio, nomeImagem).getAbsolutePath();
		imagemPart.write(caminhoCompleto);
		String imagemUrl = "uploads/servico/" + nomeImagem;
		
		if(!tiposPermitidos.contains(contentType)) {
			request.setAttribute("erro", "A imagem está no formato (" + contentType.toString() + ") mas deve estar em .png ou .jpeg");
		} else {
			var daoPrestador = PrestadorDAOFactory.create();
			var daoServico = ServicoDAOFactory.create();
			var daoDisponibilidade = DisponibilidadeDAOFactory.create();
			
			if(daoPrestador.horarioDisponivel(1, diasDisponiveisInt, horarioInicio, duracao)) {
				var servico = new Servico(prestadorId, nome, descricao, imagemUrl, preco, duracao, diasDisponiveisInt);
				servico = daoServico.create(servico); //pegar o id gerado pelo banco
				var disponibilidade = new Disponibilidade(servico.getId(), -1, horarioInicio, horarioFim);
				for(int dia : diasDisponiveisInt) {
					disponibilidade.setDiaSemana(dia);
					daoDisponibilidade.create(disponibilidade);
				}
				
				request.setAttribute("sucesso", true);
			} else {
				request.setAttribute("erro", "O horário selecionado já está sendo usado por outro serviço!");
			}
		}
		return "cadastro-servico-form.jsp";
	}
	
	private String gerarNome(Part imagem) {
		String original = imagem.getSubmittedFileName();
		String extensao = original.substring(original.lastIndexOf("."));
		
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		
		String nomeImagem = "img_" + timestamp + extensao;
		
		return nomeImagem;
	}

	private List<Integer> diasStringToInt(String[] diasString) {
	    if (diasString == null || diasString.length == 0) {
	        return Collections.emptyList();
	    }

	    Map<String, Integer> diasMap = Map.of(
	        "segunda", 2,
	        "terca", 3,
	        "quarta", 4,
	        "quinta", 5,
	        "sexta", 6,
	        "sabado", 7,
	        "domingo", 1
	    );

	    List<Integer> diasInt = new ArrayList<>();
	    
	    for (String dia : diasString) {
	        Integer diaNumero = diasMap.get(dia.toLowerCase());
	        if (diaNumero != null) {
	            diasInt.add(diaNumero);
	        }
	    }
	    
	    return diasInt;
	}

}
