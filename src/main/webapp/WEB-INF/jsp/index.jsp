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
    <div class="container">
        <img src="/resources/img/logo.jpg" width="100%" height="300">
    </div>
    <%@include file="/WEB-INF/include/navbar.app" %>
    Home Page
    <%@include file="/WEB-INF/include/footer.app" %>
</wrapper>
</body>
</html>
