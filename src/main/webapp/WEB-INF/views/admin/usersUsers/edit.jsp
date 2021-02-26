<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../adminParts/header.jsp" %>

<div class="card-body">
    <form:form method="post" modelAttribute="user">
        <form:hidden path="id"/>
        <div class="form-group">
            Imię: <form:input path="firstName" class="form-control" placeholder="Imię"/>
            <span class="error-text"><form:errors path="firstName"/></span>
        </div>
        <div class="form-group">
            Nazwisko: <form:input path="lastName" class="form-control" placeholder="Nazwisko"/>
            <label><span class="error-text"><form:errors path="lastName"/></span></label>
        </div>
        <button type="submit" class="btn btn-primary">Zapisz</button>
    </form:form>
</div>

<%@ include file="../adminParts/footer.jsp" %>






