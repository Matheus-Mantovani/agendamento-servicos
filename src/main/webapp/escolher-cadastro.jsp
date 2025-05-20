<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>HireUp - Escolher Tipo de Cadastro</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./includes/header.jsp"%>
	
	<main class="login-main">
	    <div class="login-form">
	        <h2 class="form-title">Escolha o Tipo de Cadastro</h2>
	        <div style="display: flex; flex-direction: column; gap: 1rem;">
	            <a href="controller.do?action=cadastro-cliente-page" class="btn-login">Sou Cliente</a>
	            <a href="controller.do?action=cadastro-prestador-page" class="btn-login">Sou Prestador de Serviço</a>
	        </div>
	    </div>
	</main>
	
	<%@ include file="./includes/footer.jsp"%>

</body>
</html>
