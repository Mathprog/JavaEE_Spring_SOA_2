<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 08/12/2018
  Time: 20:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<div class="row">
    <form class="col s12" method = "POST" action = "${contextPath}/ouvrages/recherche">
        <div class="row">
            <div class="input-field col s6">
                <label >Titre</label>
                <input placeholder="Titre souhaité." name = "titre" type="text" />
            </div>
        </div>
        <button class="btn waves-effect waves-light" type="submit" name="action">Rechercher
            <i class="material-icons right">send</i>
        </button>
    </form>
</div>