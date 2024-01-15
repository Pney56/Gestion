
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Inscriptions</title>
    <!-- liens vers CSS -->
</head>
<body>
<h1>Liste des Inscriptions</h1>
<table border="1">
    <thead>
    <tr>
        <th>Étudiant</th>
        <th>Cours</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="inscription" items="${inscriptions}">
        <tr>
            <td>${inscription.etudiant.nom} ${inscription.etudiant.prenom}</td>
            <td>${inscription.cours.libelle}</td>
            <td>
                <a href="modifierInscription.jsp?id=${inscription.id}">Modifier</a> |
                <a href="#" onclick="confirmerSuppression('${inscription.id}')">Supprimer</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>
    <a href="formulaireInscription.jsp">Ajouter une nouvelle inscription</a>
</p>

<script>
    function confirmerSuppression(id) {
        var response = confirm("Êtes-vous sûr de vouloir supprimer cette inscription ?");
        if(response) {
            // code pour gérer la suppression
            window.location.href = "supprimerInscription?id=" + id;
        }
    }
</script>
</body>
</html>
