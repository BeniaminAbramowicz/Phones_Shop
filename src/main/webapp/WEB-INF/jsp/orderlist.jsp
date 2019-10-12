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
    <div class="alert alert-danger" id="error"style="align:center;margin:10px;font-size:18px;display:none" role="alert">
        <c:if test="${not empty error}">
            <p id="errtext">${error}</p>
        </c:if>
    </div>
    <div class="alert alert-info" id="error"style="align:center;margin:10px;font-size:18px;" role="alert">
        <p id="orderinfo" style="visibility:hidden">${status}</p>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Product Name</th>
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
                    <form method="post" action="/orders/orderdetails/deleteitem" onsubmit="return confirmClosing(this, '${pageContext.request.contextPath}/orders/orderdetails/deleteitem')">
                        <input type="hidden" name="productId" value="${orderList.product.productId}">
                        <input type="hidden" name="orderListId" value="${orderList.orderListId}">
                        <input type="hidden" name="orderId" value="${orderList.order.orderId}">
                        <input type="number" name="quantity" min="0" id="quantity">
                        <button type="submit" class="btn btn-danger">Remove items</button>
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
        var info = document.getElementById("orderinfo");
        switch(info.innerHTML){
            case 'OPEN':
                info.innerHTML = "This order is open";
                info.style.visibility = "visible";
                break;
            case 'CLOSED':
                info.innerHTML = "This order is closed (No changes can be made)";
                info.style.visibility = "visible";
                break;
            case 'REALIZED':
                info.innerHTML = "This order has been realized (No changes can be made)";
                info.style.visibility = "visible";
                break;
            case 'CANCELLED':
                info.innerHTML = "This order has been cancelled (No changes can be made)";
                info.style.visibility = "visible";
                break;
        }
    })

    function confirmClosing(closeForm, closeUrl){
        if (confirm("Are you sure about removing these items from order?")){
            closeForm.action = closeUrl;
            return true;
        }
        return false;
    }
</script>
</body>
</html>