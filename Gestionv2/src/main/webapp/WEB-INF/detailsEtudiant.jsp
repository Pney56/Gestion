<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style.css">
<head>
    <title>Détails de l'Étudiant</title>
    <!-- liens vers CSS -->
</head>
<body>
<h1>Détails de l'Étudiant</h1>
<div>
    <p><strong>Nom:</strong> ${etudiant.nom}</p>
    <p><strong>Prénom:</strong> ${etudiant.prenom}</p>
    <p><strong>Classe:</strong> ${etudiant.classe}</p>
    <p><strong>Date de Naissance:</strong> ${etudiant.dateNaissance}</p>
</div>


<h2>Cours inscrits</h2>
<ul>
    <c:forEach var="inscription" items="${inscriptionDAO.findInscriptionsByStudentId(etudiant.id)}">
        <li>${inscription.cours.libelle} (Code: ${inscription.cours.code})</li>
    </c:forEach>
</ul>




<a href="${pageContext.request.contextPath}/etudiant/formulaire?id=${etudiant.id}" class="btn">${etudiant != null ? 'Modifier l\'Étudiant' : 'Ajouter l\'Étudiant'}</a>
 |
<a href="#" onclick="confirmerSuppression('${etudiant.id}')" class="btn">Supprimer cet étudiant</a> |
<a href="${pageContext.request.contextPath}/etudiant/liste" class="btn">Liste des étudiants</a>


<script>
    function confirmerSuppression(id) {
        var response = confirm("Êtes-vous sûr de vouloir supprimer cet étudiant ?");
        if(response) {
            // code pour gérer la suppression
            window.location.href = "${pageContext.request.contextPath}/etudiant/supprimerEtudiant?id=" + id;
        }
    }
</script>
</body>
</html>
