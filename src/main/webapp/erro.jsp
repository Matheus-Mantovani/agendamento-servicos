<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>HireUp - Erro 404</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./includes/header.jsp" %>

	<main>
	    <div class="error-page-container">
	        <div class="error-code">404</div>
	        <h1 class="error-message-title">Página Não Encontrada</h1>
	        <p class="error-message-text">
	            Oops! Parece que você se perdeu. A página que você está tentando acessar não existe,
	            foi movida ou o link que você seguiu está quebrado.
	        </p>
	        <p class="error-message-text">
	            Que tal tentar voltar para a nossa página inicial?
	        </p>
	        <a href="controller.do?action=index" class="error-link-home">Voltar à Página Inicial</a>
	    </div>
	</main>

	<%@ include file="./includes/footer.jsp" %>

</body>
</html>