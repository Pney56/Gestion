
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Inscription à un Cours</title>
    <!-- liens vers CSS -->
</head>
<body>
<h1>Inscription à un Cours</h1>
<form action="inscrireEtudiant" method="post">
    <div>
        <label for="etudiant">Étudiant:</label>
        <select id="etudiant" name="etudiantId">
            <c:forEach var="etudiant" items="${etudiants}">
                <option value="${etudiant.id}">${etudiant.nom} ${etudiant.prenom}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label for="cours">Cours:</label>
        <select id="cours" name="coursId">
            <c:forEach var="cours" items="${cours}">
                <option value="${cours.id}">${cours.libelle}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <input type="submit" value="Inscrire l'Étudiant"/>
    </div>
</form>
<a href="listeInscriptions.jsp">Retour à la liste des inscriptions</a>
</body>
</html>
