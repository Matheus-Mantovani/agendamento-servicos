<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>HireUp - Serviços</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./includes/header.jsp" %>
	
	<main>
	    <h1>Bem-vindo à HireUp</h1>
	    <p>Plataforma de agendamento de serviços com praticidade e eficiência.</p>
	    
	    <ul>
	    	<c:forEach var="servico" items="${listaServicos}">
	    		<li>${servico}</li>
	    	</c:forEach>
	    </ul>
	    
	    <p>
			<c:if test="${pagina > 0}">
			<a href="controller.do?action=servicos-page&pagina=${pagina - 1}">Anterior</a>
			</c:if>
			<c:if test="${pagina < totalPaginas - 1}">
			<a href="controller.do?action=servicos-page&pagina=${pagina + 1}">Próximo</a>
			</c:if>
		</p>
	    
	</main>
	
	<%@ include file="./includes/footer.jsp" %>

</body>
</html>
