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
        <img src="/resources/img/logo.jpg" id="logoimg"/>
    </div>
    <%@include file="/WEB-INF/include/navbar.app" %>
    <div class="container">
    <div class="col-md-8 offset-2">
    <div class="card border-black rounded-0">
    <div class="card-body">
    <form class="form-signin" action="/addproduct" modelAttribute="product" method="POST">
      <div class="form-group">
        <label for="name">Product name</label>
        <input type="text"  name="name" class="form-control" id="name" placeholder="Name" required>
      </div>
      <div class="form-group">
        <label for="price">Price</label>
        <input type="text" name="price" class="form-control" id="price" placeholder="Price" required>
      </div>
      <div class="form-group">
         <label for="itemsNumber">Items number</label>
         <input type="text" name="itemsNumber" class="form-control" id="itemsNumber" placeholder="Items number" required>
      </div>
      <div class="form-group">
         <label for="description">Description</label>
         <input type="text" name="description" class="form-control" id="description" placeholder="Description" required>
      </div>
      <div class="form-group form-check-inline">
        <label for="isAccessory">Is accessory</label>
        &nbsp
        <input type="radio" name="isAccessory" value="true" class="form-check-input" autocomplete="off" required>Yes
        &nbsp
        <input type="radio" name="isAccessory" value="false" class="form-check-input" autocomplete="off" required>No
      </div>
      <div class="form-group">
        <label for="picture">Picture link</label>
        <input type="text" name="picture" class="form-control" id="picture" placeholder="Picture link" required>
      </div>
      <button type="submit" class="btn btn-primary">Add product</button>
    </form>
    </div>
    </div>
    </div>
    </div>
</main>
    <%@include file="/WEB-INF/include/footer.app" %>
</wrapper>
</body>
</html>