<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 14/11/2018
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="date" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p><c:out value="${ usager.email }" /> !</p>

<c:forEach items="${ usager.prets }" var="pret" varStatus="status">
    <p>Ouvrage : <c:out value="${ status.count }" /> : <c:out value="${ pret.id }" /> ! date d'emprunt : <date:localDate date="${pret.datePret}"  pattern="dd/MM/yyyy"/>, date de fin : <date:localDate date="${pret.dateFin}" pattern="dd/MM/yyyy"/></p>
</c:forEach>

</body>
</html>
