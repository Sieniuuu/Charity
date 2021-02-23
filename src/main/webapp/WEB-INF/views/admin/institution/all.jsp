<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../adminParts/header.jsp" %>
<div class="container-fluid">

    <!-- Page Heading -->

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h2 class="m-0 font-weight-bold text-primary">Lista instytucji:</h2>
        </div>
        <a href='<c:url value="/institution/add"/>'>
            <button type="button" class="btn btn-primary btn-lg btn-block">Dodaj nową</button>
        </a>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <tr>
                        <th>ID</th>
                        <th>NAZWA</th>
                        <th>OPIS</th>
                        <th>OPCJE</th>
                    </tr>
                    <c:forEach items="${allInstitutions}" var="institution">
                        <tr>
                            <td>${institution.id}</td>
                            <td>${institution.name}</td>
                            <td>${institution.description}</td>
                            <td>
                                <div class="btn-group" role="group">
                                    <button id="btnGroupDrop3" type="button" class="btn btn-success dropdown-toggle"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">AKCJA</button>
                                    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                                        <a class="dropdown-item" href='<c:url value="/institution/edit?id=${institution.id}"/>'> EDYTUJ </a>
                                        <a class="dropdown-item" href='<c:url value="/institution/delete?id=${institution.id}"/>'
                                           onclick="return confirm('Jesteś pewny że chcesz usunąć organizację?')">
                                            USUŃ </a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file="../adminParts/footer.jsp" %>


