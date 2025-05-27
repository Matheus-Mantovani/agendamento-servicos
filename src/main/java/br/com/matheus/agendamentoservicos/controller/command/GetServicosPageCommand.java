package br.com.matheus.agendamentoservicos.controller.command;

import br.com.matheus.agendamentoservicos.model.dao.servico.ServicoDAOFactory;
import br.com.matheus.agendamentoservicos.model.entity.Servico;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetServicosPageCommand implements Command {
	private static final int servicosPorPagina = 15;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String paginaStr = request.getParameter("pagina");
		String nomeFiltro = request.getParameter("nomeFiltro");
		String cidadeFiltro = request.getParameter("cidadeFiltro");
		
		List<Servico> listaServicos = new ArrayList<>();
		int pagina = 0;
		int totalPaginas = 0;
		
		if(paginaStr != null) {
			pagina = Integer.parseInt(paginaStr);
		}
		
		if(pagina < 0) {
			return "erro.jsp";
		}
		
		var daoServico = ServicoDAOFactory.create();
		listaServicos = daoServico.listarServicos(pagina, servicosPorPagina, nomeFiltro, cidadeFiltro);
		totalPaginas = daoServico.getTotalPaginas(servicosPorPagina, nomeFiltro, cidadeFiltro);
		
		for(var s : listaServicos) {
			if(s.getNome() == null && !s.getNome().isBlank()) {
				System.out.println("nome");
			} else {
				System.out.println(s.getNome() + " fill");
			}
		}
		
		request.setAttribute("listaServicos", listaServicos);
		request.setAttribute("pagina", pagina);
		request.setAttribute("totalPaginas", totalPaginas);
		
		return "servicos.jsp";
	}

}
