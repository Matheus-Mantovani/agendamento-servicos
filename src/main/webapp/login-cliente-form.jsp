<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>HireUp</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./includes/header.jsp"%>

	<main class="login-main">
		<form action="controller.do?action=login-cliente" method="post" class="login-form">
			<h2 class="form-title">Login - Cliente</h2>

			<div class="form-group">
				<label for="email">E-mail:</label> <input type="email" id="email"
					name="email" required>
			</div>

			<div class="form-group">
				<label for="senha">Senha:</label> <input type="password" id="senha"
					name="senha" required>
			</div>

			<button type="submit" class="btn-login">Entrar</button>
		</form>
	</main>

	<%@ include file="./includes/footer.jsp"%>

</body>
</html>
