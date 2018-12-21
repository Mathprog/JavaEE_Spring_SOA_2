<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 06/12/2018
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" %>

<div class="row">
    <form class="col s12" method = "POST" action = "${contextPath}/usager/prets/details">
        <div class="row">
            <div class="input-field col s6">
                <label>Votre mail :</label>
                <input placeholder="Votre email." name = "email" type="text" />
            </div>
        </div>
        <button class="btn waves-effect waves-light" type="submit" name="action">Mes prêts
            <i class="material-icons right">send</i>
        </button>
    </form>
</div>