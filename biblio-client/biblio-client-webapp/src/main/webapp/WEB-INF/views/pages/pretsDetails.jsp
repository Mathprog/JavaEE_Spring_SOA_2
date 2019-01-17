<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 06/12/2018
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="date" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="row">
    <div class="col s12">

        <p><c:out value="${ message }"/></p>


        <ul class="tabs">
            <li class="tab col s3"><a href="#test3">Mon profil</a></li>
            <li class="tab col s3"><a class="active" href="#test1">Mes prêts</a></li>
            <li class="tab col s3"><a  href="#test2">Mes réservations</a></li>
        </ul>
        <div id="test1" class="col s12">

            <table class="col s12">
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
                        <td><c:out value="${ pret.exemplaire.ouvrage.titre }"/></td>
                        <td><date:localDate date="${pret.datePret}" pattern="dd/MM/yyyy"/></td>
                        <td><date:localDate date="${pret.dateFin}" pattern="dd/MM/yyyy"/></td>
                        <c:choose>
                            <c:when test="${empty pret.relance && pret.reservable}">
                                <td>
                                    <form class="col m6 s12" method="POST" action="${contextPath}/relance/add">
                                        <div class="row" style="margin-bottom: 0;">
                                            <div class="input-field col s12">
                                                <select id="relance" name="weeks" class="browser-default col s6">
                                                    <option value="0">Semaines</option>
                                                    <option value="1">1 Semaine</option>
                                                    <option value="2">2 Semaines</option>
                                                    <option value="3">3 Semaines</option>
                                                    <option value="4">4 Semaines</option>
                                                </select>

                                                <input type="hidden" name="pretId" value="${pret.id}"/>
                                                <button class="btn waves-effect waves-light col s6" type="submit"
                                                        name="action">Relance
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
                                <td><date:localDate date="${pret.relance.dateFin}" pattern="dd/MM/yyyy"/></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>


        <div id="test2" class="col s12"><table class="col s12">

            <thead>
            <tr>
                <th>Titre</th>
                <th>Disponible le</th>
                <th>Date de réservation</th>
                <th>Date limite de retrait</th>
                <th>Place dans la liste d'attente</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ reservations }" var="reservation" varStatus="status">
                <tr>
                    <td><c:out value="${ reservation.ouvrage.titre }"/></td>
                    <td><date:localDate date="${ reservation.ouvrage.dateDispo }" pattern="dd/MM/yyyy"/></td>
                    <td><c:out value="${reservation.dateReservationString}"/></td>
                    <td><date:localDate date="${ reservation.dateLimite }" pattern="dd/MM/yyyy"/></td>
                    <td><c:out value="${ reservation.usagerPlace }"/></td>
                </tr>


            </c:forEach>


            </tbody>
        </table></div>

        <div id="test3" class="col s12">

            <form method = "POST" action = "${contextPath}/usager/update">
                <p>
                    Fonction rappel de prêts 5 jours avant expiration :
                </p>
                <p>
                    <label>

                        <c:choose>
                        <c:when test="${usagerWS.pretExpiration}">
                            <input name="expiration" type="radio" value="checked" checked />
                        </c:when>
                            <c:otherwise>
                                <input name="expiration" type="radio" value="checked" />
                            </c:otherwise>
                        </c:choose>

                        <span>Activé</span>
                    </label>
                </p>
                <p>
                    <label>

                        <c:choose>
                            <c:when test="${usagerWS.pretExpiration}">
                                <input name="expiration" type="radio" value="nonChecked" />
                            </c:when>
                            <c:otherwise>
                                <input name="expiration" type="radio" value="nonChecked" checked/>
                            </c:otherwise>
                        </c:choose>

                        <span>Désactivé</span>
                    </label>
                </p>
                <input type="hidden" value="${usagerWS.email}" name="email">
                <button class="btn waves-effect waves-light" type="submit" name="action">Mes prêts
                    <i class="material-icons right">send</i>
                </button>
            </form>

        </div>




    </div>
</div>