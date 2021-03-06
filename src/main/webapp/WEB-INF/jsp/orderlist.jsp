<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/include/header.app" %>
    <script type="text/javascript" src="/resources/scripts/basicerror.js"></script>
    <script type="text/javascript" src="/resources/scripts/orderlistpage.js"></script>
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
    <div class="alert alert-info" id="info" role="alert">
        <p id="orderinfo">${status}</p>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Product Name</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
            <sec:authorize access="hasRole('ROLE_USER')">
            <th scope="col">Remove Items</th>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <th scope="col">Remove Items</th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="orderList" items="${orderListItems}">
            <tr>
                <td>${orderList.product.name}</td>
                <td>${orderList.quantity}</td>
                <td>${orderList.price}</td>
                <sec:authorize access="hasRole('ROLE_USER')">
                <td>
                    <form method="post" action="/orders/orderdetails/deleteitem" onsubmit="return confirmRemoval(this, '${pageContext.request.contextPath}/orders/orderdetails/deleteitem')">
                        <input type="hidden" name="productId" value="${orderList.product.productId}">
                        <input type="hidden" name="orderListId" value="${orderList.orderListId}">
                        <input type="number" name="quantity" min="0">
                        <button type="submit" class="btn btn-danger">Remove items</button>
                    </form>
                </td>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <td>
                        <form method="post" action="/manageorders/details/deleteitem" onsubmit="return confirmRemoval(this, '${pageContext.request.contextPath}/manageorders/details/deleteitem')">
                            <input type="hidden" name="productId" value="${orderList.product.productId}">
                            <input type="hidden" name="orderListId" value="${orderList.orderListId}">
                            <input type="number" name="quantity" min="0">
                            <button type="submit" class="btn btn-danger">Remove items</button>
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