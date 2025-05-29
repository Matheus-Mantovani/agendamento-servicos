<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Lista de Agendamentos</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./includes/header.jsp"%>

	<main class="container mt-5 mb-5">
		<h2>Serviços agendados</h2>

		<c:if test="${empty listaAgendamentos}">
			<div class="alert alert-info mt-3">Nenhum serviço agendado.</div>
		</c:if>

		<c:forEach var="agendamento" items="${listaAgendamentos}">
			<div class="card mt-3 shadow-sm">
				<div
					class="card-body d-flex flex-column flex-md-row justify-content-between align-items-md-center">
					<div>
						<h5 class="card-title">${agendamento.nomeServico}</h5>
						<p class="card-text mb-1">
							<strong>Cliente:</strong> ${agendamento.nomeCliente}
						</p>
						<p class="card-text mb-1">
							<strong>Data:</strong> ${agendamento.agendamento.data}
						</p>
						<p class="card-text mb-1">
							<strong>Horário:</strong> ${agendamento.agendamento.horaInicio}
						</p>
						<p class="card-text mb-1">
							<strong>Status:</strong> ${agendamento.agendamento.status.name}
						</p>
					</div>

					<div class="d-flex gap-2 mt-3 mt-md-0">

						<c:choose>
							<c:when
								test="${agendamento.agendamento.status.name == 'pendente'}">
								<form method="post" action="controller.do">
									<input type="hidden" name="action" value="aceitar-agendamento">
									<input type="hidden" name="id"
										value="${agendamento.agendamento.id}">
									<button type="submit" class="btn btn-success">Aceitar</button>
								</form>

								<form method="post" action="controller.do">
									<input type="hidden" name="action" value="recusar-agendamento">
									<input type="hidden" name="id"
										value="${agendamento.agendamento.id}">
									<button type="submit" class="btn btn-danger">Recusar</button>
								</form>
							</c:when>

							<c:when test="${agendamento.agendamento.status.name == 'aceito'}">
								<form method="post" action="controller.do">
									<input type="hidden" name="action" value="concluir-agendamento">
									<input type="hidden" name="id"
										value="${agendamento.agendamento.id}">
									<button type="submit" class="btn btn-primary">Concluir</button>
								</form>
							</c:when>

							<c:when
								test="${agendamento.agendamento.status.name == 'concluído'}">
								<span class="badge bg-success">Serviço concluído</span>
							</c:when>
							
							<c:when
								test="${agendamento.agendamento.status.name == 'rejeitado'}">
								<span class="badge bg-danger">Serviço rejeitado</span>
							</c:when>
						</c:choose>

					</div>
				</div>
			</div>
		</c:forEach>

	</main>

	<%@ include file="./includes/footer.jsp"%>

</body>
</html>
