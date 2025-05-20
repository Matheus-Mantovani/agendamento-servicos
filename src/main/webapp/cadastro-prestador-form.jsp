<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>HireUp - Cadastro de Prestador</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./includes/header.jsp"%>
	
	<main class="login-main">
	    <form action="controller.do?action=cadastro-prestador" method="post" class="login-form">
	        <h2 class="form-title">Cadastro - Prestador</h2>
	
	        <input type="hidden" name="tipoUsuario" value="prestador">
	
	        <div class="form-group">
	            <label for="nome">Nome:</label>
	            <input type="text" id="nome" name="nome" required>
	        </div>
	
	        <div class="form-group">
	            <label for="email">E-mail:</label>
	            <input type="email" id="email" name="email" required>
	        </div>
	
	        <div class="form-group">
	            <label for="telefone">Telefone:</label>
	            <input type="text" id="telefone" name="telefone" required>
	        </div>
	        
	        <div class="form-group">
	            <label for="especialidade">Especialidade:</label>
	            <input type="text" id="especialidade" name="especialidade" required>
	        </div>
	
	        <div class="form-group">
	            <label for="senha">Senha:</label>
	            <input type="password" id="senha" name="senha" required>
	        </div>
	
	        <button type="submit" class="btn-login">Cadastrar</button>
	    </form>
	</main>
	
	<%@ include file="./includes/footer.jsp"%>

</body>
</html>
