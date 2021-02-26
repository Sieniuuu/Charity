<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../adminParts/header.jsp" %>
<div class="container-fluid">

    <!-- Page Heading -->

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h2 class="m-0 font-weight-bold text-primary">Lista administratorów:</h2>
        </div>
        <a href='<c:url value="/admin/add"/>'>
            <button type="button" class="btn btn-primary btn-lg btn-block">Dodaj nowego</button>
        </a>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <tr>
                        <th>ID</th>
                        <th>IMIĘ</th>
                        <th>NAZWISKO</th>
                        <th>EMAIL</th>
                        <th>OPCJE</th>
                    </tr>
                    <c:forEach items="${admins}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.email}</td>
                            <td>
                                <div class="btn-group" role="group">
                                    <button id="btnGroupDrop3" type="button" class="btn btn-success dropdown-toggle"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        AKCJA
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                                        <a class="dropdown-item" href='<c:url value="/admin/edit?id=${user.id}"/>'>
                                            ZMIEŃ DANE</a>
                                        <a class="dropdown-item"
                                           href='<c:url value="/admin/editPassword?id=${user.id}"/>'> ZMIEŃ HASŁO </a>
                                        <a class="dropdown-item"
                                           href='<c:url value="/admin/editEmail?id=${user.id}"/>'> ZMIEŃ EMAIL </a>
                                    </div>
                                </div>
                                <a href='<c:url value="/admin/delete?id=${user.id}"/>'>
                                    <button type="button" class="btn btn-primary"
                                            onclick="return confirm('Jesteś pewny że chcesz usunąć tego administratora?')">
                                        USUŃ
                                    </button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <span class="error-text">${empty error ? " " : error }</span>
        </div>
    </div>
</div>
<!-- /.container-fluid -->
<%@ include file="../adminParts/footer.jsp" %>

