<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create Page</title>
</head>
<body>
<div class="container">
    <div class="row">
        <h1>Create Product form</h1>
        <c:if test="${not empty errorPrice}">
            <div class="alert alert-danger mt-3">${errorPrice}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="alert alert-success mt-3">${message}</div>
        </c:if>
        <form action="product?action=create" method="post">
            <input type="hidden" name="action" value="create"/>
            <div class="mt-2 form-group">
                <label>Name </label>
                <input type="text" class="form-control"
                       placeholder="Enter name" name="name"/>
            </div>
            <div class="mt-2 form-group">
                <label>Price </label>
                <input type="number" class="form-control"
                       placeholder="Enter price" name="price"/>
            </div>
            <div class="mt-2 form-group">
                <label>Status</label>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="status_active" name="status" value="1" checked>
                    <label class="form-check-label" for="status_active">Active</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="status_deactive" name="status" value="0">
                    <label class="form-check-label" for="status_deactive">Deactive</label>
                </div>
            </div>
            <div class="mt-2 form-group">
                <label>Category </label>
                <select class="form-select" id="sel1" name="cate">
                    <c:set var="categories" value="${['food','beverage','electrics']}" />
                    <c:forEach var="category" items="${categories}">
                        <option value="${category}">${category}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary mt-2">Submit</button>
            <a class="btn btn-warning mt-2" href="product">Back to list</a>
        </form>
    </div>
</div>

</body>
</html>