<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Brand List</title>
    </head>
    <body>
        <div class="container mt-3">
            <h2>Brand List</h2>
            <a class="btn btn-primary" href="brand?action=create">Create a new brand</a>
            <a class="btn btn-success" href="product?action=list">List product</a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Brand</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="b" items="${brands}">
                        <tr>
                            <td>${b.id}</td>
                            <td>${b.name}</td>
                            <td>
                                <a class="btn btn-warning" href="brand?action=edit&id=${b.id}">Edit</a>
                                <a class="btn btn-info" href="product?action=viewByBrand&id=${b.id}">View Products Of This Brand</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
