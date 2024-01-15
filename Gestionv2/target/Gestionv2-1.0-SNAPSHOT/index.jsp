<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style.css">
<head>
    <title>Accueil - Gestion Scolaire</title>
</head>
<body>
<h1>Bienvenue dans le Système de Gestion Scolaire</h1>
<nav>
    <h2>Cours</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/cours/list" class="btn">Liste des cours</a></li>
        <li><a href="${pageContext.request.contextPath}/cours/formulaire" class="btn">Ajouter un cours</a></li>
    </ul>
    <h2>Étudiants</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/etudiant/list" class="btn">Liste des étudiants</a></li>
        <li><a href="${pageContext.request.contextPath}/etudiant/formulaire" class="btn">Ajouter un étudiant</a></li>
    </ul>
    <h2>Inscriptions</h2>
    <ul>
        <li><a href="${pageContext.request.contextPath}/inscription/liste" class="btn">Liste des inscriptions</a></li>
        <li><a href="${pageContext.request.contextPath}/inscription/formulaire" class="btn">Inscrire un étudiant</a></li>
    </ul>
</nav>
</body>
</html>
