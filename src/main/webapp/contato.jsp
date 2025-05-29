<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>HireUp - Contato</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <%@ include file="./includes/header.jsp" %>

    <section class="bg-primary text-white text-center py-5">
        <div class="container">
            <h1 class="display-5">Fale Conosco</h1>
            <p class="lead">Tem dúvidas, sugestões ou precisa de ajuda? Envie uma mensagem.</p>
        </div>
    </section>

    <section class="container py-5 mb-5">
        <div class="row">
            <div class="col-md-6">
                <h3>Envie sua mensagem</h3>
                <form action="ContatoServlet" method="post">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" class="form-control" id="nome" name="nome" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">E-mail</label>
                        <input type="email" class="form-control" id="email" name="email" required>
                    </div>
                    <div class="mb-3">
                        <label for="mensagem" class="form-label">Mensagem</label>
                        <textarea class="form-control" id="mensagem" name="mensagem" rows="5" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary" disabled>Enviar</button>
                </form>
            </div>

            <div class="col-md-6">
                <h3>Informações de Contato</h3>
                <p><strong>Email:</strong> suporte@hireup.com.br</p>
                <p><strong>Telefone:</strong> (11) 98765-4321</p>
                <p><strong>Endereço:</strong> Rua dos Prestadores, 123 - São Paulo/SP</p>

                <h5 class="mt-4">Redes Sociais</h5>
                <a href="#" class="btn btn-outline-primary btn-sm me-2">Instagram</a>
                <a href="#" class="btn btn-outline-primary btn-sm me-2">Facebook</a>
                <a href="#" class="btn btn-outline-primary btn-sm">LinkedIn</a>
            </div>
        </div>
    </section>

    <%@ include file="./includes/footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
