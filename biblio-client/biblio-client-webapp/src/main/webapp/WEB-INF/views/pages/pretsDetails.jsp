<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 06/12/2018
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="date" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<div class="row">
<table class="col s12">

    <p><c:out value="${ message }" /></p>
    <thead>
    <tr>
        <th>Titre</th>
        <th>Date d'emprunt</th>
        <th>Date de fin</th>
        <th>Relance</th>
    </tr>
    </thead>
    <tbody>
        <c:forEach items="${ prets }" var="pret" varStatus="status">
            <tr>
                <td><c:out value="${ pret.exemplaire.ouvrage.titre }" /></td>
                <td><date:localDate date="${pret.datePret}"  pattern="dd/MM/yyyy"/> </td>
                <td><date:localDate date="${pret.dateFin}" pattern="dd/MM/yyyy"/></td>
                <c:choose>
                    <c:when test="${empty pret.relance && pret.reservable}">
                <td>
                        <form class="col m6 s12" method = "POST" action = "${contextPath}/relance/add">
                            <div class="row" style="margin-bottom: 0;">
                                <div class="input-field col s12">
                                    <select id="relance" name="weeks"  class="browser-default col s6">
                                        <option value="0">Semaines</option>
                                        <option value="1">1 Semaine</option>
                                        <option value="2">2 Semaines</option>
                                        <option value="3">3 Semaines</option>
                                        <option value="4">4 Semaines</option>
                                    </select>

                                    <input type="hidden" name="pretId" value="${pret.id}"/>
                                <button class="btn waves-effect waves-light col s6" type="submit" name="action">Relance
                                    <i class="material-icons right">send</i>
                                </button>
                                </div>
                            </div>
                        </form>
                </td>
                    </c:when>
                    <c:when test="${!pret.reservable && empty pret.relance}">
                        <td>
                            Vous ne pouvez plus prolonger votre prêt.
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td> <date:localDate date="${pret.relance.dateFin}"  pattern="dd/MM/yyyy"/></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>