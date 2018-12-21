<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 06/12/2018
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav class="nav light-blue lighten-1" role="navigation">
    <a href="${contextPath}/"></a>
    <ul id="menu">
        <li><a href="${contextPath}/">Home</a></li>
        <li><a href="${contextPath}/usager/prets">Voir mes prêts</a></li>
        <li><a href="${contextPath}/ouvrages/liste">Livres</a></li>
        <li><a href="${contextPath}/ouvrages/recherche">Recherche de livres</a></li>
    </ul>
</nav>

