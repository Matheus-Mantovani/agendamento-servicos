<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%
	var erro = request.getAttribute("erro");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>HireUp - Login de Prestador</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./includes/header.jsp"%>

	<main class="login-main">
		<form action="controller.do?action=login-prestador" method="post" class="login-form">
			<h2 class="form-title">Login - Prestador</h2>
			<%
			if(erro != null) {
			%>
				<div class="msg-box msg-error"><%= erro %></div>
			<%
			} 
			%>
			
			<c:if test="${not empty sessionScope.erro}">
				<div class="msg-box msg-error">${sessionScope.erro}</div>
				<c:remove var="erro" scope="session"/>
			</c:if>

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
