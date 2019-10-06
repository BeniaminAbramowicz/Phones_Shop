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
    <div class="alert alert-danger" id="error" style="align:center;margin:10px;display:none;" role="alert">
        <c:if test="${not empty error}">
            <p id="errtext">${error}</p>
        </c:if>
    </div>
    <sec:authorize access="hasRole('ROLE_USER')">
    <form method="post" action="/createorder" modelAttribute="order">
    <input type="hidden" name="userId" value="${user.userId}">
    <button type="submit" class="btn btn-success">Open new order</button>
    </form>
    </sec:authorize>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Order ID</th>
            <th scope="col">Order status</th>
            <th scope="col">Total price</th>
            <th scope="col">Order details</th>
            <th scope="col">Close Order</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.status}</td>
            <td>${order.totalPrice}</td>
            <td>
            <form method="get" action="/orders/orderdetails">
                <input type="hidden" name="orderId" value="${order.orderId}">
                <button type="submit" class="btn btn-success">Items list</button>
            </form>
            </td>
            <td>
            <form method="post" action="/orders/closeorder"  onsubmit="return confirmClosing(this, '${pageContext.request.contextPath}/orders/closeorder')">
                <input type="hidden" name="orderId" value="${order.orderId}">
                <button type="submit" class="btn btn-danger">Close and send order</button>
            </form>
            </td>
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
