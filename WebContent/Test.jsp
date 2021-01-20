<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<a href="<%=request.getContextPath()+"/PageProfilServlet"%>?pseudo=${model.utilisateur.pseudo}">${model.utilisateur.pseudo}</a>




</body>
</html>