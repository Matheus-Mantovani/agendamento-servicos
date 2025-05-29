<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>HireUp</title>
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/images/favicon.ico">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <%@ include file="./includes/header.jsp" %>

    <section class="bg-primary text-white text-center py-5">
        <div class="container">
            <h1 class="display-4">Bem-vindo à HireUp</h1>
            <p class="lead mt-3">Sua plataforma de agendamento de serviços com praticidade, eficiência e confiança.</p>
        </div>
    </section>

    <section class="container py-5">
        <div class="row text-center">
            <div class="col-md-4">
                <img src="https://cdn-icons-png.flaticon.com/512/4202/4202843.png" alt="Agendamento fácil" width="80">
                <h4 class="mt-3">Agendamento Fácil</h4>
                <p>Agende serviços em poucos cliques, de forma simples e intuitiva.</p>
            </div>
            <div class="col-md-4">
                <img src="https://cdn-icons-png.flaticon.com/512/3198/3198544.png" alt="Profissionais verificados" width="80">
                <h4 class="mt-3">Profissionais Verificados</h4>
                <p>Conecte-se com prestadores de serviços de confiança e qualidade.</p>
            </div>
            <div class="col-md-4">
                <img src="https://cdn-icons-png.flaticon.com/512/190/190411.png" alt="Gerenciamento prático" width="80">
                <h4 class="mt-3">Gerencie Seus Agendamentos</h4>
                <p>Acompanhe o status, aceite ou recuse solicitações com facilidade.</p>
            </div>
        </div>
    </section>

    <section class="bg-light text-center py-5 mb-5">
        <div class="container">
            <h2 class="mb-3">Junte-se à HireUp hoje mesmo</h2>
            <p class="lead">Clientes satisfeitos e prestadores valorizados em um só lugar.</p>
            <a href="controller.do?action=escolher-cadastro-page" class="btn btn-primary btn-lg">Cadastre-se gratuitamente</a>
        </div>
    </section>

    <%@ include file="./includes/footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
