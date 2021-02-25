<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="userParts/header.jsp" %>


<!-- Page Heading -->

<div class="card-body">
    <form:form method="post" modelAttribute="user">
        <form:hidden path="id"/>
        <form:hidden path="firstName"/>
        <form:hidden path="lastName"/>
        <form:hidden path="donations"/>
        <form:hidden path="enabled"/>
        <form:hidden path="roles"/>
        <form:hidden path="password"/>
        <div class="form-group">
            <form:input path="email" placeholder="Email" class="form-control"/> <br>
            <span class="error-text"><form:errors path="email"/></span>
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Zapisz</button>
    </form:form>
</div>

<%@ include file="userParts/footer.jsp" %>





