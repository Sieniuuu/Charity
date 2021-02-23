<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Form Nav</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>

<nav class="container container--70">
    <sec:authorize access="isAuthenticated()">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj <sec:authentication property="principal.username"/>
                <ul class="dropdown">
                    <li><a href="<c:url value="/admin/"/>" class="btn btn--small btn--without-border" >Panel zarządzania</a></li>
                    <li>
                        <form action="<c:url value="/logout"/>" method="post" >
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input style="color:Red;" class="btn btn--small btn--without-border" type="submit" value="Wyloguj">
                        </form>
                    </li>
                </ul>
            </li>
        </ul>
    </sec:authorize>

    <sec:authorize access="!isAuthenticated()">
        <ul class="nav--actions">
            <li><a href='<c:url value="/login"/>' class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href='<c:url value="/register"/>' class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </ul>
    </sec:authorize>

    <ul>
        <li><a href="/" class="btn btn--without-border active">Start</a></li>
        <li><a href="/#steps" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="/#about-us" class="btn btn--without-border">O nas</a></li>
        <li><a href="/#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <li><a href='<c:url value="/donation/form"/>' class="btn btn--without-border">Przekaż dary</a></li>
        <li><a href="/#contact" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>