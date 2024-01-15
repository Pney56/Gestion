<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Formulaire Étudiant</title>
    <!-- liens vers CSS -->
</head>
<body>
<h1>${etudiant != null ? "Modifier" : "Ajouter"} un Étudiant</h1>
<form action="${etudiant != null ? 'modifierEtudiant' : 'ajouterEtudiant'}" method="post">
    <input type="hidden" name="id" value="${etudiant != null ? etudiant.id : ''}"/> <!-- Champ caché pour l'ID en cas de modification -->
    <div>
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="nom" value="${etudiant != null ? etudiant.nom : ''}" required/>
    </div>
    <div>
        <label for="prenom">Prénom:</label>
        <input type="text" id="prenom" name="prenom" value="${etudiant != null ? etudiant.prenom : ''}" required/>
    </div>
    <!-- Ajout d'autres champs -->
    <div>
        <a href="${pageContext.request.contextPath}/etudiant/details?id=${etudiant.id}" class="btn">Détails</a> |
        <a href="${pageContext.request.contextPath}/etudiant/formulaire?id=${etudiant.id}" class="btn">${etudiant != null ? 'Modifier' : 'Ajouter'} l'Étudiant</a> |
        <a href="#" onclick="confirmerSuppression('${etudiant.id}')" class="btn">Supprimer</a>

    </div>
</form>
<a href="listeEtudiants.jsp">Retour à la liste des étudiants</a>
</body>
</html>
