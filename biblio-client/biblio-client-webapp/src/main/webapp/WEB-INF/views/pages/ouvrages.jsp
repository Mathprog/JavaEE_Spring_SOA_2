<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 06/12/2018
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<div class="row">
<c:forEach items="${ ouvragesDispos }" var="ouvrageDispo" varStatus="status">

        <div class="col s6 m3">
            <div class="card">
                <div class="card-image">
                    <img src="data:image/jpeg;base64,<c:out value="${ ouvrageDispo.imageBase64DataString}"/>">
                </div>
                <div class="card-content">
                    <span class="card-title"><c:out value="${ ouvrageDispo.titre }"/></span>
                    <p> Ecrit par : <i><c:out value="${ ouvrageDispo.auteur }"/></i></p>
                    <p><c:out value="${ ouvrageDispo.resume }"/></p>
                    <p><c:out value="${ ouvrageDispo.nbDispo }" /> exemplaires disponibles.</p>
                </div>
            </div>
        </div>
</c:forEach>


<c:forEach items="${ ouvragesNonDispos }" var="ouvrageNonDispo" varStatus="status">
        <div class="col s6 m3">
            <div class="card">
                <div class="card-image">
                    <img src="data:image/jpeg;base64,<c:out value="${ ouvrageNonDispo.imageBase64DataString}"/>">
                </div>
                <div class="card-content">
                    <span class="card-title"><c:out value="${ ouvrageNonDispo.titre }"/></span>
                    <p> Ecrit par : <i><c:out value="${ ouvrageNonDispo.auteur }"/></i></p>
                    <p><c:out value="${ ouvrageNonDispo.resume }"/></p>
                    <p><c:out value="${ ouvrageNonDispo.nbDispo }"/> exemplaires disponibles.</p>
                </div>
            </div>
        </div>
</c:forEach>

</div>

