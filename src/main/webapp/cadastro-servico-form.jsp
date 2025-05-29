<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
	var sucesso = request.getAttribute("sucesso");
	var erro = request.getAttribute("erro");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>HireUp - Cadastro de serviço</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./includes/header.jsp"%>

	<main class="login-main">
		<form action="controller.do?action=cadastro-servico" method="post" class="login-form" onsubmit="return validarFormulario()" enctype="multipart/form-data">
			<h2 class="form-title">Cadastro de Serviço</h2>
			
			<%
			if (sucesso != null) {
				if ((Boolean) sucesso) {
			%>
					<div class="msg-box msg-success">Serviço cadastrado com sucesso!</div>
			<%
				} else {
			%>
				<div class="msg-box msg-error">Erro ao realizar cadastro. Tente novamente.</div>
			<%
				}
			}
	        if(erro != null) {
			%>
				<div class="msg-box msg-error"><%= erro %></div>
			<%
			} 
			%>

			<div class="form-group">
				<label for="nome">Nome do Serviço:</label> <input type="text"
					id="nome" name="nome" required>
			</div>

			<div class="form-group">
				<label for="descricao">Descrição:</label>
				<textarea id="descricao" name="descricao" rows="4" required></textarea>
			</div>

			<div class="form-group">
				<label for="preco">Preço (R$):</label> <input type="number"
					id="preco" name="preco" step="0.01" required>
			</div>

			<div class="form-group">
				<label for="duracao">Duração (minutos):</label> <input type="number"
					id="duracao" name="duracao" required>
			</div>

			<div class="form-group">
				<label>Dias disponíveis:</label>
				<div class="dias-semana">
					<label><input type="checkbox" name="diasDisponiveis" value="segunda">Segunda</label><br>
					<label><input type="checkbox" name="diasDisponiveis" value="terca">Terça</label><br>
					<label><input type="checkbox" name="diasDisponiveis" value="quarta">Quarta</label><br>
					<label><input type="checkbox" name="diasDisponiveis" value="quinta">Quinta</label><br>
					<label><input type="checkbox" name="diasDisponiveis" value="sexta">Sexta</label><br>
					<label><input type="checkbox" name="diasDisponiveis" value="sabado">Sábado</label><br>
					<label><input type="checkbox" name="diasDisponiveis" value="domingo"> Domingo</label>	
				</div>
			</div>

			<div class="form-group">
				<label for="horarioInicio">Horário de Início:</label> <input
					type="time" id="horarioInicio" name="horarioInicio" required>
			</div>

			<div class="form-group">
				<label for="horarioFim">Horário de Fim:</label> <input type="time"
					id="horarioFim" name="horarioFim" required>
			</div>
			
			<div class="form-group">
				<label for="imagemServico">Imagem do serviço (.png ou .jpeg):</label> <input type="file"
					id="imagemServico" name="imagemServico" accept="image/png, image/jpeg" required>
			</div>

			<button type="submit" class="btn-login">Cadastrar Serviço</button>
		</form>
	</main>

	<%@ include file="./includes/footer.jsp"%>
	
	<script>
		function validarCheckboxes() {
    		const checkboxes = document.querySelectorAll('input[name="diasDisponiveis"]:checked');
    		if (checkboxes.length === 0) {
        		alert('Selecione pelo menos um dia da semana!');
        		return false;
    		}
    		return true;
		}
		
		function validarHorarios() {
		    const horaInicio = document.getElementById('horaInicio').value;
		    const horaFim = document.getElementById('horaFim').value;
		    
		    if (horaInicio && horaFim && horaFim <= horaInicio) {
		        alert('O horário de fim deve ser após o horário de início!');
		        return false;
		    }
		    return true;
		}
		
		function validarFormulario() {
		    return validarCheckboxes() && validarHorarios();
		}
	</script>

</body>
</html>
