<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/include/header.app" %>
    <script type="text/javascript" src="/resources/scripts/basicerror.js"></script>
    <script type="text/javascript" src="/resources/scripts/orderpage.js"></script>
    <title>Home Page</title>
</head>
<body>
<wrapper class="d-flex flex-column">
<main class="container-fluid py-3 flex-fill">
    <div class="logo">
        <img src="/resources/img/logo.jpg" id="logoimg"/>
    </div>
    <%@include file="/WEB-INF/include/navbar.app" %>
    <div class="alert alert-danger" id="error" role="alert">
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
            <sec:authorize access="hasRole('ROLE_USER')">
            <th scope="col">Order details</th>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th scope="col">Order details</th>
            </sec:authorize>
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
            <td class="lowercase">${order.status}</td>
            <td>${order.totalPrice}</td>
            <sec:authorize access="hasRole('ROLE_USER')">
            <td>
            <form method="get" action="/orders/orderdetails">
                <input type="hidden" name="orderId" value="${order.orderId}">
                <button type="submit" class="btn btn-success">Items list</button>
            </form>
            </td>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <td>
                    <form method="get" action="/manageorders/details">
                        <input type="hidden" name="orderId" value="${order.orderId}">
                        <button type="submit" class="btn btn-success">Items list</button>
                    </form>
                </td>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
            <td>
            <form method="post" action="/orders/closeorder"  onsubmit="return confirmClosing(this, '${pageContext.request.contextPath}/orders/closeorder')">
                <input type="hidden" name="orderId" value="${order.orderId}">
                <button type="submit" class="btn btn-danger" id="closeorder">Close and send order</button>
            </form>
            </td>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <td>
                    <form method="post" action="/manageorders/changestatus" class="statusform">
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
</body>
</html>
