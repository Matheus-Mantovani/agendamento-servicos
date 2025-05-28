package br.com.matheus.agendamentoservicos.controller.command;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AgendarServicoCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "agendar-servico.jsp"; //TODO TODO TODO TODO TODO TODO 
	}

}
