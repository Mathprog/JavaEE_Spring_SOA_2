<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 06/12/2018
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page trimDirectiveWhitespaces="true" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><tiles:getAsString name="title" /></title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="<c:url value='/static/css/materialize.min.css' />" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="<c:url value='/static/css/app.css' />" type="text/css" rel="stylesheet" media="screen,projection"/>

</head>

<body>
        <tiles:insertAttribute name="menu" />
        <tiles:insertAttribute name="header" />

            <div class="container demo">
                <div class="section">
            <tiles:insertAttribute name="body" />
                </div>
            </div>
        <tiles:insertAttribute name="footer" />

        <!--  Scripts-->
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script src="<c:url value='/static/js/materialize.min.js' />"></script>
        <script src="<c:url value='/static/js/init-js.js' />"></script>


</body>
</html>