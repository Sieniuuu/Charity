<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="userParts/header.jsp" %>
<div class="container-fluid">

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h2 class="m-0 font-weight-bold text-primary">Lista moich darów:</h2><br>
        <form method="get">
            <label>
                <b>Sortuj po:</b><br>
                <select name="searchMode" class="form-control">
                    <option value="----" selected="selected" > ---- </option>
                    <option value="id" class="form-control"> DATA UTWORZENIA </option>
                    <option value="pickUpDate" class="form-control"> DATA ODBIORU </option>
                    <option value="status" class="form-control"> STATUS </option>
                </select>
            </label>
            <input type="submit" value="SORTUJ" class="btn btn-primary">
            <input type="submit" value="WYCZYŚĆ" class="btn btn-primary">
        </form>
        </div>
        <hr>
        <a href='<c:url value="/donation/addDonate"/>'>
            <button type="button" class="btn btn-primary btn-lg btn-block">Dodaj nową darowiznę</button>
        </a>
        <hr>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <tr>
                        <th>ID</th>
                        <th>ILOŚĆ</th>
                        <th>KATEGORIE</th>
                        <th>STATUS</th>
                        <th>DATA ODBIORU</th>
                        <th>AKCJA</th>
                    </tr>
                    <c:forEach items="${donations}" var="donation">
                        <tr>
                            <td>${donation.id}</td>
                            <td>${donation.quantity}</td>
                            <td>
                                <c:forEach items="${donation.categories}" var="category">
                                    <li><i>${category.name}</i><br></li>
                                </c:forEach>
                            </td>
                            <c:choose>
                                <c:when test="${donation.recived == true}">
                                    <td>ODEBRANY</td>
                                </c:when>
                                <c:otherwise>
                                    <td>NIEODEBRANY</td>
                                </c:otherwise>
                            </c:choose>
                            <td>${empty donation.reciveDate ? "-----" : donation.reciveDate }</td>
                            <td>
                                <div class="btn-group" role="group">
                                    <button id="btnGroupDrop3" type="button" class="btn btn-success dropdown-toggle"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        AKTUALIZUJ STATUS
                                    </button>
                                    <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                                        <c:choose>
                                            <c:when test="${donation.recived == false}">
                                                <a class="dropdown-item"
                                                   href='<c:url value="/donation/recived?id=${donation.id}"/>'
                                                   onclick="return confirm('Jesteś pewny że dar został odebrany?')">
                                                    ODEBRANY </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="dropdown-item"
                                                   href='<c:url value="/donation/unclimed?id=${donation.id}"/>'
                                                   onclick="return confirm('Jesteś pewny że dar nie został odebrany?')">
                                                    NIEODEBRANY </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            <a href='<c:url value="/donation/details?id=${donation.id}"/>'>
                                <button type="button" class="btn btn-primary">SZCZEGÓŁY</button>
                            </a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<!-- /.container-fluid -->
<%@ include file="userParts/footer.jsp" %>

