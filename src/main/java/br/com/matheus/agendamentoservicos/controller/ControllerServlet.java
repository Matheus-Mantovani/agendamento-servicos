package br.com.matheus.agendamentoservicos.controller;

import java.io.IOException;

import br.com.matheus.agendamentoservicos.controller.command.CadastroClienteCommand;
import br.com.matheus.agendamentoservicos.controller.command.CadastroPrestadorCommand;
import br.com.matheus.agendamentoservicos.controller.command.CadastroServicoCommand;
import br.com.matheus.agendamentoservicos.controller.command.Command;
import br.com.matheus.agendamentoservicos.controller.command.ErrorCommand;
import br.com.matheus.agendamentoservicos.controller.command.GetCadastroServicoPageCommand;
import br.com.matheus.agendamentoservicos.controller.command.GetContatoPageCommand;
import br.com.matheus.agendamentoservicos.controller.command.GetCadastroClientePageCommand;
import br.com.matheus.agendamentoservicos.controller.command.GetCadastroPrestadorPageCommand;
import br.com.matheus.agendamentoservicos.controller.command.GetEscolherCadastroPageCommand;
import br.com.matheus.agendamentoservicos.controller.command.GetEscolherLoginPageCommand;
import br.com.matheus.agendamentoservicos.controller.command.GetLoginClientePageCommand;
import br.com.matheus.agendamentoservicos.controller.command.GetLoginPrestadorPageCommand;
import br.com.matheus.agendamentoservicos.controller.command.GetServicosPageCommand;
import br.com.matheus.agendamentoservicos.controller.command.HomeCommand;
import br.com.matheus.agendamentoservicos.controller.command.LoginClienteCommand;
import br.com.matheus.agendamentoservicos.controller.command.LoginPrestadorCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/controller.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 2, maxRequestSize = 1024 * 1024 * 2)
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//List<String> tiposPermitidos = Arrays.asList("image/png", "image/jpeg");
    
    public ControllerServlet() {
        super();
    }
    
    public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	var action = req.getParameter("action");
		Command command = null;

		switch (action) {
			// Páginas
			case "index":
				command = new HomeCommand();
				break;
			case "escolher-login-page":
				command = new GetEscolherLoginPageCommand();
				break;
			case "login-cliente-page":
				command = new GetLoginClientePageCommand();
				break;
			case "login-prestador-page":
				command = new GetLoginPrestadorPageCommand();
				break;
			case "escolher-cadastro-page":
				command = new GetEscolherCadastroPageCommand();
				break;
			case "cadastro-cliente-page":
				command = new GetCadastroClientePageCommand();
				break;
			case "cadastro-prestador-page":
				command = new GetCadastroPrestadorPageCommand();
				break;
			case "cadastro-servico-page":
				command = new GetCadastroServicoPageCommand();
				break;
			case "servicos-page":
				command = new GetServicosPageCommand();
				break;
			case "contato-page":
				command = new GetContatoPageCommand();
				break;

			// Ações
			case "login-cliente":
				command = new LoginClienteCommand();
				break;
			case "login-prestador":
				command = new LoginPrestadorCommand();
				break;
			case "cadastro-cliente":
				command = new CadastroClienteCommand();
				break;
			case "cadastro-prestador":
				command = new CadastroPrestadorCommand();
				break;
			case "cadastro-servico":
				command = new CadastroServicoCommand();
				break;

			default:
				command = new ErrorCommand();
				break;
		}

		
		var view = command.execute(req, res);
		var dispatcher = req.getRequestDispatcher(view);
		dispatcher.forward(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}
}
