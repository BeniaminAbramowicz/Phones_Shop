<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="/WEB-INF/include/header.app" %>
    <title>Products list</title>
</head>
<body>
<wrapper class="d-flex flex-column">
    <div class="logo">
        <img src="/resources/img/logo.jpg" max-width="100%" height="auto" style = "display:block; margin-left:auto; margin-right:auto;" />
    </div>
    <%@include file="/WEB-INF/include/navbar.app" %>
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
                    <h5 class="card-text">About product:<p>${product.description}</p></h5>
                </div>
            </div>
        </td>
        </tr>
        </c:forEach>
        </tbody>
        </table>
    <%@include file="/WEB-INF/include/footer.app" %>
</wrapper>
</body>
</html>