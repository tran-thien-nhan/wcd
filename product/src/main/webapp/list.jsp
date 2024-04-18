<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>List Product</title>
    </head>
    <body>
        <div class="container">
            <h2>List Product</h2>
            <a href="product?action=create" class="btn btn-primary">Add Product</a>
            <!--sort tang dan-->
            <a href="product?action=sortAsc" class="btn btn-warning">Sort Asc</a>
            <!--sort giam dan-->
            <a href="product?action=sortDesc" class="btn btn-success">Sort Desc</a>
            <!-- tim kiem theo name -->
            <form action="product?action=search" method="post" class="my-2">
                <input type="text" name="name" placeholder="Enter name" class="form-control">
            </form>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${products}"> 
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.name}</td>
                            <td>${p.price}</td>
                            <td>${p.quantity}</td>
                            <td>
                                <a href="product?action=edit&id=${p.id}" class="btn btn-secondary">Edit</a>
                                <a href="product?action=delete&id=${p.id}" class="btn btn-danger">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
