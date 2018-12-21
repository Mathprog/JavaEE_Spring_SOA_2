<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 15/11/2018
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method = "POST" action = "${contextPath}/usager/pret">
    <table>
        <tr>
            <td><label >Name</label></td>
            <td><input name = "email" /></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type = "submit" value = "Submit"/>
            </td>
        </tr>
    </table>
</form>
Infos détaillés :
<form method = "POST" action = "${contextPath}/usager/pret/detail">
    <table>
        <tr>
            <td><label >Name</label></td>
            <td><input name = "email" /></td>
        </tr>
        <tr>
            <td colspan = "2">
                <input type = "submit" value = "Submit"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
