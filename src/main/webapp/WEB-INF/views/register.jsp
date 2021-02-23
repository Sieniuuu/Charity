
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Register</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <%@include file="parts/header.jsp"%>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:input path="firstName" placeholder="Imię" /> <br>
            <span class="error-text"><form:errors path="firstName"/></span>
        </div>
        <div class="form-group">
            <form:input path="lastName" placeholder="Nazwisko" /> <br>
            <span class="error-text"><form:errors path="lastName"/></span>
        </div>
        <div class="form-group">
            <form:input path="email" placeholder="Email" /> <br>
            <span class="error-text"><form:errors path="email"/></span>
        </div>
        <div class="form-group">
            <form:password path="password" placeholder="Hasło" /> <br>
            <span class="error-text"><form:errors path="password"/></span>
        </div>
        <div class="form-group">
            <input type="password" name="confirmPassword" placeholder="Powtórz hasło" />
        </div>
        <span class="error-text">${empty error ? " " : error }</span>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<%@ include file="parts/footer.jsp" %>
