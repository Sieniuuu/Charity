<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="adminParts/header.jsp" %>
<div class="container-fluid">

    <!-- Page Heading -->

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h2 class="m-0 font-weight-bold text-primary">Lista ostatnich darów:</h2>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <tr>
                        <th>ID</th>
                        <th>ILOŚĆ</th>
                        <th>KATEGORIE</th>
                        <th>INSTYTUCJA</th>
                        <th>ADRES ODBIORU</th>
                        <th>SZCZEGÓŁY ODBIORU</th>
                        <th>DARCZYŃCA</th>
                        <th>STATUS</th>
                        <th>AKCJA</th>
                    </tr>
                    <c:forEach items="${lastDonations}" var="donation">
                        <tr>
                            <td>${donation.id}</td>
                            <td>${donation.quantity}</td>
                            <td>
                                <c:forEach items="${donation.categories}" var="category">
                                    <li><i>${category.name}</i><br></li>
                                </c:forEach>
                            </td>
                            <td>${empty donation.institution.name ? "BRAK" : donation.institution.name }</td>
                            <td>${donation.fullAddress}</td>
                            <td>${donation.deliver()}</td>
                            <td>${empty donation.user.fullName ? "Anonimowy" : donation.user.fullName}<br></td>
                            <c:choose>
                            <c:when test="${donation.recived == true}">
                            <td> ODEBRANY </td>
                                </c:when>
                                <c:otherwise>
                            <td> NIEODEBRANY </td>
                                </c:otherwise>
                                </c:choose>
                            <td>
                                <div class="btn-group" role="group">
                                    <button id="btnGroupDrop3" type="button" class="btn btn-success dropdown-toggle"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        AKTUALIZUJ STATUS
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                                        <a class="dropdown-item" href='<c:url value="/admin/recived?id=${donation.id}"/>'
                                           onclick="return confirm('Jesteś pewny że dar został odebrany?')">
                                            ODEBRANY </a>
                                        <a class="dropdown-item" href='<c:url value="/admin/unclimed?id=${donation.id}"/>'
                                           onclick="return confirm('Jesteś pewny że dar nie został odebrany?')">
                                            NIEODEBRANY </a>
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
<%@ include file="adminParts/footer.jsp" %>

