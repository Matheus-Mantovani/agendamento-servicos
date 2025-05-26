<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>HireUp - Acesso Negado</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <%@ include file="./includes/header.jsp" %>

    <main>
        <div class="error-page-container">
            <div class="error-code">403</div>
            <h1 class="error-message-title">Acesso Restrito</h1>
            <p class="error-message-text">
                Você não tem permissão para acessar esta página ou recurso.
                Isso pode acontecer se você não estiver logado ou não tiver o nível de acesso necessário.
            </p>
            <p class="error-message-text">
                Faça login com uma conta autorizada ou volte para a página inicial.
            </p>
            <a href="controller.do?action=index" class="error-link-home">Voltar à Página Inicial</a>
        </div>
    </main>

    <%@ include file="./includes/footer.jsp" %>

</body>
</html>
