<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<%
String[] siglas = { "D", "S", "T", "Q", "Q", "S", "S" };
request.setAttribute("diasSemanaSiglas", siglas);
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>HireUp - Agendar Serviço</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./includes/header.jsp"%>

	<main class="container mt-5 mb-5">
		<div class="row">
			<div class="col-md-6">
				<img src="${servico.imagemUrl}" alt="Imagem do serviço"
					class="img-fluid rounded shadow">
			</div>
			<div class="col-md-6">
				<h2>${servico.nome}</h2>
				<p>${servico.descricao}</p>
				<p>
					<strong>Preço:</strong> R$ ${servico.preco}
				</p>
				<p>
					<strong>Duração:</strong> ${servico.duracaoMinutos} minutos
				</p>
				<p>
					<strong>Dias disponíveis:</strong>
					<c:forEach var="dia" items="${diasSemana}" varStatus="status">
						<span class="badge me-1 ${diasDisponiveis[status.index] ? 'bg-primary' : 'bg-secondary text-light opacity-50'}">
						 	${dia} 
						 </span>
					</c:forEach>
				</p>
			</div>
		</div>

		<hr class="my-4">

		<h4>Escolha uma data</h4>
		<form method="get" action="controller.do" class="row g-3 mb-4">
			<input type="hidden" name="action" value="agendar-servico-page">
			<input type="hidden" name="id" value="${servico.id}">
			<div class="col-auto">
				<input type="date" name="data" class="form-control"
					min="${dataHoje}" value="${dataSelecionada}" required>
			</div>
			<div class="col-auto">
				<button type="submit" class="btn btn-primary">Ver horários</button>
			</div>
		</form>

		<c:if test="${not empty dataSelecionada}">
			<h4>Horários disponíveis para ${dataSelecionada}</h4>
			<div class="row row-cols-2 row-cols-md-4 g-3 mt-2">
				<c:forEach var="horario" items="${horariosDisponiveis}">
					<div class="col">
						<form method="post" action="controller.do">
							<input type="hidden" name="action" value="agendar-servico">
							<input type="hidden" name="servicoId" value="${servico.id}">
							<input type="hidden" name="prestadorId" value="${prestador.id}">
							<input type="hidden" name="data" value="${dataSelecionada}">
							<input type="hidden" name="horario" value="${horario}">
							<button type="submit" class="btn btn-outline-success w-100">${horario}</button>
						</form>
					</div>
				</c:forEach>
			</div>

			<c:if test="${empty horariosDisponiveis}">
				<div class="alert alert-warning mt-4">Nenhum horário
					disponível no momento.</div>
			</c:if>
		</c:if>
	</main>

	<%@ include file="./includes/footer.jsp"%>

</body>
</html>
