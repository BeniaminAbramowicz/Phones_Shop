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
    <input type="hidden" name="userId" value="${user.userId}">
    <div class="logo">
        <img src="/resources/img/logo.jpg" max-width="100%" height="auto" style = "display:block; margin-left:auto; margin-right:auto;" />
    </div>
    <%@include file="/WEB-INF/include/navbar.app" %>
    <table class="table">
        <thead>
        <tr>
            <th scope=""col">Product Name</th>
            <th scope="col">Quantity</th>
            <th scope="col">Price</th>
            <th scope="col">Remove Items</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="orderList" items="${orderListItems}">
            <tr>
                <td>${orderList.product.name}</td>
                <td>${orderList.quantity}</td>
                <td>${orderList.price}</td>
                <td>
                    <form method="post" action="/orders/orderdetails/deleteitem/{orderListId}">
                        <input type="hidden" name="orderListId" value="${orderList.orderListId}">
                        <input type="number" name="quantity" min="0" id="quantity">
                        <button type="submit" class="btn btn-danger">Remove items</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%@include file="/WEB-INF/include/footer.app" %>
</main>
</wrapper>
</body>
</html>