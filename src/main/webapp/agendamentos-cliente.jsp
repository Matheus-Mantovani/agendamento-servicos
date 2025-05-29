<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Meus Agendamentos</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./includes/header.jsp"%>

	<main class="container mt-5 mb-5">
		<h2>Meus Serviços Agendados</h2>

		<c:if test="${empty listaAgendamentos}">
			<div class="alert alert-info mt-3">Você ainda não agendou
				nenhum serviço.</div>
		</c:if>

		<c:forEach var="agendamento" items="${listaAgendamentos}">
			<div class="card mt-3 shadow-sm">
				<div
					class="card-body d-flex flex-column flex-md-row justify-content-between align-items-md-center">
					<div>
						<h5 class="card-title">${agendamento.nomeServico}</h5>
						<p class="card-text mb-1">
							<strong>Prestador:</strong> ${agendamento.nomePrestador}
						</p>
						<p class="card-text mb-1">
							<strong>Data:</strong> ${agendamento.agendamento.data}
						</p>
						<p class="card-text mb-1">
							<strong>Horário:</strong> ${agendamento.agendamento.horaInicio}
						</p>
					</div>
					<div class="mt-3 mt-md-0">
						<c:choose>
							<c:when
								test="${agendamento.agendamento.status.name == 'pendente'}">
								<span class="badge bg-warning text-dark">Pendente</span>
							</c:when>
							<c:when test="${agendamento.agendamento.status.name == 'aceito'}">
								<span class="badge bg-primary">Aceito</span>
							</c:when>
							<c:when
								test="${agendamento.agendamento.status.name == 'rejeitado'}">
								<span class="badge bg-danger">Recusado</span>
							</c:when>
							<c:when
								test="${agendamento.agendamento.status.name == 'concluído'}">
								<span class="badge bg-success">Concluído</span>
							</c:when>
							<c:otherwise>
								<span class="badge bg-secondary">Status desconhecido</span>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</c:forEach>

	</main>

	<%@ include file="./includes/footer.jsp"%>

</body>
</html>
