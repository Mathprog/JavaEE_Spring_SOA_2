<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 12/01/2019
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<h2> Réservation de l'ouvrage : ${ouvrage.titre}</h2>


<div class="row">
    <form class="col s12" method = "POST" action = "${contextPath}/reservation/create">
        <input value="${ouvrage.id}" name = "ouvrageId" type="hidden"/>
        <div class="row">
            <div class="input-field col s6">
                <label>Votre mail :</label>
                <input placeholder="Votre email." name = "email" type="text" />
            </div>
        </div>
        <button class="btn waves-effect waves-light" type="submit" name="action">Réserver
            <i class="material-icons right">send</i>
        </button>
    </form>
</div>