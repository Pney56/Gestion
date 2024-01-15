<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style.css">
<head>
    <title>Liste des Cours</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/" class="btn">Retour à la page d'accueil</a>

<h1>Liste des Cours</h1>
<table border="1">
    <thead>
    <tr>
        <th>Code du cours</th>
        <th>Libellé</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cours" items="${listCours}">
        <tr>
            <td><c:out value="${cours.code}"/></td>
            <td><c:out value="${cours.libelle}"/></td>
            <td>
                <a href="${pageContext.request.contextPath}/cours/details?code=${cours.code}" class="btn">Modifier</a>
                |
                <a href="#" onclick="confirmerSuppression('${cours.code}')" class="btn">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>
    <a href="${pageContext.request.contextPath}/cours/formulaire" class="btn">Ajouter un nouveau cours</a>
</p>

<script>
    function confirmerSuppression(code) {
        var response = confirm("Êtes-vous sûr de vouloir supprimer le cours avec le code " + code + "?");
        if (response) {
            window.location.href = "${pageContext.request.contextPath}/cours/delete?code=" + code;
        }
    }
</script>
</body>
</html>
