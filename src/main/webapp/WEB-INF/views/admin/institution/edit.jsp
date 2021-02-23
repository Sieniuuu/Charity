<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../adminParts/header.jsp" %>


    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="text-left h3 mb-0 text-gray-800">UsersCRUD</h1>

    </div>
        <div class="card-body">
           <form:form method="post" modelAttribute="institution">
               <form:hidden path="id"/>
                <div class="form-group">
                    Nazwa: <form:input path="name" class="form-control" placeholder="Nazwa organizacji"/>
                    <label><span class="error-text"><form:errors path="name"/></span></label>
                </div>
                <div class="form-group">
                    Opis: <form:input path="description" class="form-control" placeholder="Opis organizacji"/>
                    <label><span class="error-text"><form:errors path="description"/></span></label>
                </div>
                <button type="submit" class="btn btn-primary">Zapisz</button>
           </form:form>
        </div>
    </div>
</div>
<%@ include file="../adminParts/footer.jsp" %>






