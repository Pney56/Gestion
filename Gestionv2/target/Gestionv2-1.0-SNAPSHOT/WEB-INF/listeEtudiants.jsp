<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style.css">
<head>
    <title>Liste des Étudiants</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/" class="btn">Retour à la page d'accueil</a>

<h1 class="my-heading">Liste des Étudiants</h1>
<table border="1" class="my-table">
    <thead>
    <tr>
        <th class="my-table-header">Nom</th>
        <th class="my-table-header">Prénom</th>
        <th class="my-table-header">Classe</th>
        <th class="my-table-header">Date de Naissance</th>
        <th class="my-table-header">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="etudiant" items="${listeEtudiants}">
        <tr>
            <td class="my-table-cell"><c:out value="${etudiant.nom}"/></td>
            <td class="my-table-cell"><c:out value="${etudiant.prenom}"/></td>
            <td class="my-table-cell"><c:out value="${etudiant.classe}"/></td>
            <td class="my-table-cell"><c:out value="${etudiant.dateNaissance}"/></td>
            <td class="my-table-cell">
                <a href="${pageContext.request.contextPath}/etudiant/details?id=${etudiant.id}" class="btn">Détails</a>
                |
                <a href="#" onclick="confirmerSuppression('${etudiant.id}')" class="btn">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>
    <a href="${pageContext.request.contextPath}/etudiant/formulaire" class="btn">Ajouter un nouvel étudiant</a>
</p>

<script>
    function confirmerSuppression(id) {
        var response = confirm("Êtes-vous sûr de vouloir supprimer cet étudiant ?");
        if (response) {
            window.location.href = "${pageContext.request.contextPath}/etudiant/delete?id=" + id;
        }
    }
</script>
</body>
</html>
