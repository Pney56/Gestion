<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style.css">
<head>
    <title>${cours != null ? "Modifier" : "Ajouter"} un Cours</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/" class="btn">Retour à la page d'accueil</a>

<h1>${cours != null ? "Modifier" : "Ajouter"} un Cours</h1>

<form action="${pageContext.request.contextPath}/cours" method="post">
    <input type="hidden" name="codeOriginal" value="${cours != null ? cours.code : ''}"/>
    <div>
        <label for="code">Code du cours:</label>
        <input type="text" id="code" name="code" value="${cours != null ? cours.code : ''}" required/>
    </div>
    <div>
        <label for="libelle">Libellé:</label>
        <input type="text" id="libelle" name="libelle" value="${cours != null ? cours.libelle : ''}" required/>
    </div>
    <div>
        <input type="submit" value="${cours != null ? 'Modifier' : 'Ajouter'} le Cours" class="btn"/>
    </div>
</form>
<a href="${pageContext.request.contextPath}/cours/list" class="btn">Retour à la liste des cours</a>
</body>
</html>
