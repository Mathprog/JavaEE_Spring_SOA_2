<%--
  Created by IntelliJ IDEA.
  User: mathi
  Date: 06/12/2018
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <br><br>
        <h1 class="header center orange-text"><tiles:getAsString name="secondtitle" /></h1>
        <div class="row center">
            <h5 class="header col s12 light"><tiles:getAsString name="description" /></h5>
        </div>
        <br><br>
    </div>
</div>