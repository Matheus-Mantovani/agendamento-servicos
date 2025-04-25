package br.com.matheus.agendamentoservicos.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/teste")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 2, maxRequestSize = 1024 * 1024 * 2)
public class ControllerServlet extends HttpServlet {
	//List<String> tiposPermitidos = Arrays.asList("image/png", "image/jpeg");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/servicosdb");
			conn = ds.getConnection();
			
			var teste = conn.prepareStatement("INSERT INTO cliente (nome, email, telefone, cpf, senha) VALUES (?, ?, ?, ?, ?)");
			teste.setString(1, "matheus");
			teste.setString(2, "matheus@gmail");
			teste.setString(3, "12345667");
			teste.setString(4, "56845680");
			teste.setString(5, "12334");
			
			teste.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;

		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/servicosdb");
			conn = ds.getConnection();
			conn.close();
			request.getRequestDispatcher("usuarios.jsp").forward(request, response);
		} catch (Exception e) {
			throw new ServletException("Erro ao salvar usuário", e);
		}
	}
}