<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style.css">
<head>
    <title>Détails du Cours</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/" class="btn">Retour à la page d'accueil</a>

<h1>Détails du Cours</h1>
<div>
    <p><strong>Code du cours:</strong> ${cours.code}</p>
    <p><strong>Libellé:</strong> ${cours.libelle}</p>
</div>

<h2>Liste des étudiants inscrits</h2>
<c:choose>
    <c:when test="${fn:length(inscriptions) == 0}">
        <p>Aucun étudiant inscrit pour ce cours.</p>
    </c:when>
    <c:otherwise>
        <table border="1">
            <thead>
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <!-- Ajout d'autres détails si nécessaire -->
            </tr>
            </thead>
            <tbody>
            <c:forEach var="inscription" items="${inscriptions}">
                <tr>
                    <td><c:out value="${inscription.etudiant.nom}"/></td>
                    <td><c:out value="${inscription.etudiant.prenom}"/></td>
                    <!-- Ajoutez d'autres colonnes si nécessaire -->
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

<div>
    <a href="#" onclick="ouvrirPopup();" class="btn">Modifier ce cours</a> |
    <a href="#" onclick="confirmerSuppression('${cours.code}')" class="btn">Supprimer ce cours</a>
</div>


<!-- Pop-up de modification (caché par défaut) -->
<div id="popup" class="popup">
    <div class="popup-content">
        <span class="close" onclick="fermerPopup()">&times;</span>
        <h2>Modifier le Cours</h2>
        <!-- Formulaire de modification -->
        <form action="#" method="post" onsubmit="return modifierCours();">
            <input type="text" id="nouveau_libelle" placeholder="Nouveau libellé">
            <input type="hidden" id="code_cours" value="${cours.code}">
            <input type="submit" value="Enregistrer les modifications">
        </form>
    </div>
</div>

<!-- JavaScript pour gérer le pop-up -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function ouvrirPopup() {
        var popup = document.getElementById("popup");
        popup.style.display = "block";
    }

    function fermerPopup() {
        var popup = document.getElementById("popup");
        popup.style.display = "none";
    }

    function modifierCours() {
        // Récupérer les données du formulaire
        var nouveauLibelle = document.getElementById("nouveau_libelle").value;
        var codeCours = document.getElementById("code_cours").value;

        // Envoyer les données au serveur pour la mise à jour en utilisant AJAX
        $.ajax({
            type: "POST",
            url: "modifierCours",
            data: {
                code: codeCours,
                libelle: nouveauLibelle
            },
            success: function (response) {
                // Gérer la réponse du serveur (peut-être une confirmation de mise à jour)
                console.log(response);
                // Fermer le pop-up après la mise à jour
                fermerPopup();
            },
            error: function (error) {
                // Gérer les erreurs
                console.error(error);
            }
        });

        // Empêcher la soumission du formulaire
        return false;
    }
</script>
</body>
</html>
