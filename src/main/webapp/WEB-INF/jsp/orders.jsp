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
    <div class="logo">
        <img src="/resources/img/logo.jpg" max-width="100%" height="auto" style = "display:block; margin-left:auto; margin-right:auto;" />
    </div>
    <%@include file="/WEB-INF/include/navbar.app" %>
    <table class="table">
    <thead>
        <tr>
            <th scope="col">email</th>
            <th scope="col">id</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>${user.email}</td>
            <td>${user.userId}</td>
        </tr>
    </tbody>
    </table>
    <form method="post" action="/createorder" modelAttribute="order">
    <input type="hidden" name="userId" value="${user.userId}">
    <button type="submit" class="btn btn-success">Create order</button>
    </form>
    <form method="post" action="/test" modelAttribute="order">
    <button type="submit" class="btn btn-success">test</button>
    </form>
    <%@include file="/WEB-INF/include/footer.app" %>
</wrapper>
</body>
</html>