<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/include/header.app" %>
    <script type="text/javascript" src="/resources/scripts/basicerror.js"></script>
    <title>Products list</title>
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
        <table class="table">
        <tbody>
        <c:forEach var="product" items="${productList}">
        <tr>
        <td>
            <div class="card w-100 flex-row text-center">
                <div class="col-lg-6">
                    <img src="${product.picture}"/>
                </div>
                <div class="col-lg-4">
                    <h1 class="card-title">${product.name}</h1>
                    <h2 class="card-text">Price: ${product.price} PLN</h2>
                    <h4 class="card-text">Quantity: ${product.itemsNumber}</h4>
                    <h5 class="card-text">About product:${product.description}</h5>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <form method="get" action="/allproducts/editproduct/${product.productId}">
                    <input type="hidden" name="productId" value="${product.productId}">
                    <button type="submit" class="btn btn-success">Edit product</button>
                    </form>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <form method="post" action="/allproducts/deleteproduct/${product.productId}">
                    <input type="hidden" name="productId" value="${product.productId}">
                    <button type="submit" class="btn btn-danger">Delete product</button>
                    </form>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <form method="post" action="/orderitem">
                            <input type="hidden" name="productId" value="${product.productId}"/>
                            <label for="quantity">Number of items</label>
                            <input type="number" name="quantity" min="0" id="quantity"/>
                            <input type="hidden" name="price" value="${product.price}">
                            <button type="submit" class="btn btn-primary">Add item to order</button>
                        </form>
                    </sec:authorize>
                </div>
            </div>
        </td>
        </tr>
        </c:forEach>
        </tbody>
        </table>
</main>
    <%@include file="/WEB-INF/include/footer.app" %>
</wrapper>
</body>
</html>