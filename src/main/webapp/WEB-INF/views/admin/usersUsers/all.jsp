<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../adminParts/header.jsp" %>
<div class="container-fluid">

    <!-- Page Heading -->

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h2 class="m-0 font-weight-bold text-primary">Lista użytkowników:</h2>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <tr>
                        <th>ID</th>
                        <th>IMIĘ</th>
                        <th>NAZWISKO</th>
                        <th>EMAIL</th>
                        <th>STATUS</th>
                        <th>OPCJE</th>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.email}</td>

                            <c:choose>
                                <c:when test="${user.enabled == true}">
                                    <td> AKTYWNY</td>
                                </c:when>
                                <c:otherwise>
                                    <td> ZABLOKOWANY</td>
                                </c:otherwise>
                            </c:choose>
                            <td>
                                <div class="btn-group" role="group">
                                    <button id="btnGroupDrop3" type="button" class="btn btn-success dropdown-toggle"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        EDYTUJ
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                                        <a class="dropdown-item" href='<c:url value="/admin/editUser?id=${user.id}"/>'>
                                            ZMIEŃ DANE </a>
                                        <a class="dropdown-item" href='<c:url value="/admin/editUserPassword?id=${user.id}"/>'>
                                            ZMIEŃ HASŁO </a>
                                        <a class="dropdown-item" href='<c:url value="/admin/editUserEmail?id=${user.id}"/>'>
                                            ZMIEŃ EMAIL </a>
                                    </div>
                                </div>

                                <div class="btn-group" role="group">
                                    <button id="btnGroupDrop4" type="button" class="btn btn-secondary dropdown-toggle"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        AKTUALIZUJ
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                                        <c:choose>
                                            <c:when test="${user.enabled == true}">
                                                <a class="dropdown-item"
                                                   href='<c:url value="/admin/block?id=${user.id}"/>'
                                                   onclick="return confirm('Jesteś pewny że chcesz zablokować użytkownika?')">
                                                    ZABLOKUJ </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="dropdown-item"
                                                   href='<c:url value="/admin/unblock?id=${user.id}"/>'
                                                   onclick="return confirm('Jesteś pewny że chcesz odblokować użytkownika?')">
                                                    ODBLOKUJ </a>
                                            </c:otherwise>
                                        </c:choose>
                                        <a class="dropdown-item" href='<c:url value="/admin/deleteUser?id=${user.id}"/>'
                                           onclick="return confirm('Jesteś pewny że chcesz usunąć użytkownika?')">
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
<!-- /.container-fluid -->
<%@ include file="../adminParts/footer.jsp" %>

