<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 12/11/2018
  Time: 00:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Title</title>
</head>
<body>

<h2> Les ouvrages !! </h2>
<c:forEach items="${ ouvrages }" var="ouvrage" varStatus="status">
    <p>Ouvrage : <c:out value="${ status.count }" /> : <c:out value="${ ouvrage.titre }" /> ! Exemplaires disponibles : <c:out value="${ ouvrage.nbDispo }" /></p>
</c:forEach>
</body>
</html>
