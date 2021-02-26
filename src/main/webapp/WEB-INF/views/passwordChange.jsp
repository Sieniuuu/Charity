<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <%@include file="parts/header.jsp"%>
</header>

<section class="login-page">
<form:form method='post' modelAttribute="user">
    <form:hidden path="id"/>
    <div class="form-group">
        <form:password path="password" placeholder="Hasło"  /><br>
        <span class="error-text"><form:errors path="password"/></span>
    </div>
    <div class="form-group">
        <input type="password" name="confirmPassword" placeholder="Powtórz hasło" required />
    </div>
    <span class="error-text">${empty error ? " " : error }</span>
    <div class="form-group form-group--buttons">
        <button class="btn" type="submit">Zatwierdź</button>
    </div>
</form:form>
</section>

<%@ include file="parts/footer.jsp" %>






