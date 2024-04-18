<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>
    </head>
    <body>
        <div class="container mt-3">
            <h2>Product List</h2>
            <a class="btn btn-primary" href="product?action=create">Create a new product</a>
<%--            search price 2 input fromPrice và toPrice--%>
            <form action="product?action=search" method="post">
                <div class="row mt-3">
                    <div class="col-3">
                        <input type="number" class="form-control" name="fromPrice" placeholder="From Price">
                    </div>
                    <div class="col-3">
                        <input type="number" class="form-control" name="toPrice" placeholder="To Price">
                    </div>
                    <div class="col-3">
                        <button type="submit" class="btn btn-primary">Search</button>
                    </div>
                </div>
            </form>
            <c:if test="${not empty error}">
                <div class="alert alert-danger mt-3">${error}</div>
                <a class="btn btn-primary" href="product?action=list">Back to list</a>
            </c:if>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Category</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${products}">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.name}</td>
                            <td>${p.price}</td>
                            <td>${p.status}</td>
                            <td>${p.cate}</td>
                            <td>
                                <a class="btn btn-primary" href="product?action=changeStatus&id=${p.id}">Change Status</a>
                                <a class="btn btn-danger" onclick="" href="product?action=delete&id=${p.id}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
    <script>
        function confirmDelete(productId) {
            // Hiển thị hộp thoại xác nhận
            if (confirm("Bạn có chắc chắn muốn xóa sản phẩm này không?")) {
                window.location.href = `product?action=delete&id=${productId}`;
            }
        }
    </script>
</html>
