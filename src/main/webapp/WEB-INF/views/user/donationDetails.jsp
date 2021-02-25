<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="userParts/header.jsp" %>
<div class="container-fluid">

    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h2 class="m-0 font-weight-bold text-primary">Szczegóły darowizny:
            </h2>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table">
                    <tbody>
                    <tr>
                        <th>ILOŚĆ WORKÓW:</th>
                        <td>${donation.quantity}</td>
                    </tr>
                    <tr>
                        <th>KATEGORIE:</th>
                        <td>
                            <c:forEach items="${donation.categories}" var="category">
                                <li><i>${category.name}</i><br></li>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <th>INSTYTUCJA:</th>
                        <td>${empty donation.institution.name ? "-----" : donation.institution.name }</td>
                    </tr>
                    <tr>
                        <th>ADRES ODBIORU:</th>
                        <td><b>MIASTO:</b> ${donation.city}<br>
                            <b>KOD POCZTOWY:</b> ${donation.zipCode}<br>
                            <b>ULICA:</b> ${donation.street}</td>
                    </tr>
                    <tr>
                        <th>TERMIN ODBIORU:</th>
                        <td><b>DATA:</b> ${donation.pickUpDate}<br>
                            <b>GODZINA:</b> ${donation.pickUpTime}</td>
                    </tr>
                    <tr>
                        <th>TWÓJ KOMENTARZ:</th>
                        <td>${donation.pickUpComment}</td>
                    </tr>
                    <tr>
                        <th>STATUS:</th>
                        <td>${donation.recived}</td>
                    </tr>
                    <tr>
                        <th>KONTAKT:</th>
                        <td>${donation.phone}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@ include file="userParts/footer.jsp" %>
