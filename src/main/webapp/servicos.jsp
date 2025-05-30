<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>HireUp - Serviços</title>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
</head>
<body>

	<%@ include file="./includes/header.jsp"%>

	<main>
		<div class="container mt-3">
			<c:if test="${not empty sucesso}">
				<div class="alert alert-success">${sucesso}</div>
				<c:remove var="sucesso" scope="session" />
			</c:if>

			<c:if test="${not empty erro}">
				<div class="alert alert-danger">${erro}</div>
				<c:remove var="erro" scope="session" />
			</c:if>
		</div>

		<div class="container mt-4">
			<form method="get" action="controller.do"
				class="row g-3 align-items-end">
				<input type="hidden" name="action" value="servicos-page">

				<div class="col-md-5">
					<label for="nomeFiltro" class="form-label">Nome do serviço</label>
					<input type="text" class="form-control" id="nomeFiltro"
						name="nomeFiltro" value="${param.nome}">
				</div>

				<div class="col-md-5">
					<label for="cidadeFiltro" class="form-label">Cidade</label> <input
						type="text" class="form-control" id="cidadeFiltro"
						name="cidadeFiltro" value="${param.cidade}" disabled>
				</div>

				<div class="col-md-2">
					<button type="submit" class="btn btn-primary w-100">Filtrar</button>
				</div>
			</form>
		</div>

		<div class="container mt-4">
			<div class="row row-cols-1 row-cols-md-3 g-4">
				<c:forEach var="servico" items="${listaServicos}">
					<div class="col">
						<div class="card h-100">
							<div class="image-container">
								<img src="${servico.imagemUrl}" alt="Imagem do serviço" />
							</div>
							<div class="card-body d-flex flex-column">
								<h5 class="card-title">${servico.nome}</h5>
								<p class="card-text">${servico.descricao}</p>

								<a
									href="controller.do?action=agendar-servico-page&id=${servico.id}"
									class="btn btn-success mt-auto">Agendar</a>
							</div>
							<div class="card-footer">
								<small class="text-muted"> Duração:
									${servico.duracaoMinutos} min • R$ ${servico.preco} </small>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

		<div class="container mt-4 mb-5">
			<div class="d-flex justify-content-center gap-3">
				<c:if test="${pagina > 0}">
					<a
						href="controller.do?action=servicos-page&pagina=${pagina - 1}&nomeServico=${param.nomeServico}&cidadeServico=${param.cidadeServico}"
						class="btn btn-primary"> &laquo; Anterior </a>
				</c:if>

				<c:if test="${pagina < totalPaginas}">
					<a
						href="controller.do?action=servicos-page&pagina=${pagina + 1}&nomeServico=${param.nomeServico}&cidadeServico=${param.cidadeServico}"
						class="btn btn-primary"> Próximo &raquo; </a>
				</c:if>
			</div>
		</div>
	</main>

	<%@ include file="./includes/footer.jsp"%>

</body>
</html>
