<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<header>
    <div class="logo-area">
        <h2 class="site-title">HireUp</h2>
    </div>
    <nav class="nav-links">
        <div class="nav-left">
            <a href="controller.do?action=index">Início</a>
            <a href="controller.do?action=servicos-page&pagina=0">Serviços</a>
            <c:if test="${not empty sessionScope.prestador}">
            	<a href="controller.do?action=cadastro-servico-page">Cadastrar serviço</a>
            </c:if>
            <c:if test="${not empty sessionScope.prestador}">
            	<a href="controller.do?action=agendamentos-prestador-page">Agendamentos</a>
            </c:if>
            <c:if test="${not empty sessionScope.cliente}">
            	<a href="controller.do?action=agendamentos-cliente-page">Agendamentos</a>
            </c:if>
            <a href="controller.do?action=contato-page">Contato</a>
        </div>
        <div class="nav-right">
            <c:if test="${empty sessionScope.prestador and empty sessionScope.cliente}">
            	<a href="controller.do?action=escolher-login-page">Login</a>
            	<a href="controller.do?action=escolher-cadastro-page">Cadastro</a>
            </c:if>
            <c:if test="${not empty sessionScope.prestador or not empty sessionScope.cliente}">
            	<a href="controller.do?action=logout">Sair</a>
            </c:if>
        </div>
    </nav>
</header>
