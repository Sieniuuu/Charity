<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../adminParts/header.jsp" %>


<!-- Page Heading -->

<div class="card-body">
    <form:form method="post" modelAttribute="user">
        <form:hidden path="id"/>
        <div class="form-group">
            <form:password path="password" placeholder="Hasło" class="form-control"/> <br>
            <span class="error-text"><form:errors path="password"/></span>
        </div>
        <div class="form-group">
            <input type="password" name="confirmPassword" placeholder="Powtórz hasło" class="form-control"/>
        </div>
        <span class="error-text">${empty error ? " " : error }</span><br>
        <br>

        <button type="submit" class="btn btn-primary">Zapisz</button>
    </form:form>
</div>

<%@ include file="../adminParts/footer.jsp" %>






