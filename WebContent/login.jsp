<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page de Login</title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<form method="post" action="LoginServlet">

<label for="pseudo">Identifiant:</label> <input type="text" name="pseudo" id="pseudo" required ><br>
<label for="mdp">Mot de passe:</label> <input type="password" name="mdp" id="mdp" required ><br>
<input type="submit" value="Connexion">
<input type="checkbox" name="souvenir" value="1" > Se souvenir de moi <br>
<a href= >Mot de passe oublié</a>
</form>

<form action="CreaCompteServlet" method="get">
<input type="submit" value="Créer un compte">
</form>

${message1}
${message2}

</body>
</html>