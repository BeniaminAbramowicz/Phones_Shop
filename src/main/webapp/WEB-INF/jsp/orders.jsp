<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/include/header.app" %>
    <title>Home Page</title>
</head>
<body>
<wrapper class="d-flex flex-column">
<main class="container-fluid py-3 flex-fill">
    <div class="logo">
        <img src="/resources/img/logo.jpg" max-width="100%" height="auto" style = "display:block; margin-left:auto; margin-right:auto;" />
    </div>
    <%@include file="/WEB-INF/include/navbar.app" %>
    <div class="alert alert-danger" id="error" style="align:center;margin:10px;font-size:18px;display:none;" role="alert">
        <c:if test="${not empty error}">
            <p id="errtext">${error}</p>
        </c:if>
    </div>
    <table class="table" id="order">
        <thead>
        <tr>
            <th scope="col">Order ID</th>
            <th scope="col">Order status</th>
            <th scope="col">Total price</th>
            <th scope="col">Order details</th>
            <sec:authorize access="hasRole('ROLE_USER')">
            <th scope="col">Close Order</th>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <th scope="col">Change status</th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.orderId}</td>
            <td style="text-transform:lowercase">${order.status}</td>
            <td>${order.totalPrice}</td>
            <td>
            <form method="get" action="/orders/orderdetails">
                <input type="hidden" name="orderId" value="${order.orderId}">
                <button type="submit" class="btn btn-success">Items list</button>
            </form>
            </td>
            <sec:authorize access="hasRole('ROLE_USER')">
            <td>
            <form method="post" action="/orders/closeorder"  onsubmit="return confirmClosing(this, '${pageContext.request.contextPath}/orders/closeorder')">
                <input type="hidden" name="orderId" value="${order.orderId}">
                <button type="submit" class="btn btn-danger" style="display:none;">Close and send order</button>
            </form>
            </td>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <td>
                    <form method="post" action="/manageorders/changestatus" class="statusform" style="display:none;">
                        <input type="hidden" name="orderId" value="${order.orderId}">
                        <select class="form-control-sm span3" name="status">
                            <option value="${enums[0]}">${enums[0]}</option>
                            <option value="${enums[1]}">${enums[1]}</option>
                        </select>
                        <button type="submit" class="btn btn-primary">Change order status</button>
                    </form>
                </td>
            </sec:authorize>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
    <%@include file="/WEB-INF/include/footer.app" %>
</wrapper>
<script>
    document.addEventListener("DOMContentLoaded", function(){
        var err = document.getElementById("errtext");
        if(err !== null){
            document.getElementById("error").style.display = "block";
        }
        if(window.location.href === "http://localhost:8080/orders") {
            var table = document.getElementById("order");
            var tbody = table.getElementsByTagName("tbody");
            var tr = tbody[0].getElementsByTagName("tr");
            var buttons = tbody[0].getElementsByClassName("btn btn-danger");
            for (var i = 0; i < tr.length; i++) {
                if (tr[i].getElementsByTagName("td")[1].innerHTML === "OPEN") {
                    buttons[i].style.display = "block";
                }
            }
        } else if(window.location.href === "http://localhost:8080/manageorders"){
            var tablead = document.getElementById("order");
            var tbodyad = tablead.getElementsByTagName("tbody");
            var trad = tbodyad[0].getElementsByTagName("tr");
            var changeform = tbodyad[0].getElementsByClassName("statusform");
            for (var i = 0; i < trad.length; i++) {
                if (trad[i].getElementsByTagName("td")[1].innerHTML === "CLOSED") {
                    changeform[i].style.display = "inline-block";
                }
            }
        }
    })

    function confirmClosing(closeForm, closeUrl){
        if (confirm("You can't make any changes to order after closing it. Are you sure?")){
            closeForm.action = closeUrl;
            return true;
        }
        return false;
    }
</script>
</body>
</html>
