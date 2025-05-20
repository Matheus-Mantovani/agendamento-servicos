<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>HireUp - Cadastro de serviço</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./includes/header.jsp"%>

	<main class="login-main">
		<form action="controller.do?action=cadastro-servico" method="post" class="login-form">
			<h2 class="form-title">Cadastro de Serviço</h2>

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
				<label for="horaInicio">Horário de Início:</label> <input
					type="time" id="horaInicio" name="horaInicio" required>
			</div>

			<div class="form-group">
				<label for="horaFim">Horário de Fim:</label> <input type="time"
					id="horaFim" name="horaFim" required>
			</div>

			<button type="submit" class="btn-login">Cadastrar Serviço</button>
		</form>
	</main>

	<%@ include file="./includes/footer.jsp"%>

</body>
</html>
