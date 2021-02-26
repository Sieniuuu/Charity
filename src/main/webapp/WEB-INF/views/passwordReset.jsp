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
      <h2>Wpisz adres email</h2>
      <form method="post">
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <div class="form-group">
              <input type="text" name="username" placeholder="Email" />
          </div>
        <span class="error-text">${empty error ? " " : error }</span>
        <span class="succes-text">${empty succes ? " " : succes }</span>
        <div class="form-group form-group--buttons">
          <button class="btn" type="submit">Wyślij</button>
        </div>
      </form>
    </section>

    <%@ include file="parts/footer.jsp" %>
