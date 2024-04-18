<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>category List</title>
    </head>
    <body>
        <div class="container mt-3">
            <h2>Category List</h2>
            <a class="btn btn-primary" href="category?action=create">Create a new Category</a>
            <a class="btn btn-success" href="product?action=list">List product</a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Category</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="c" items="${categories}">
                        <tr>
                            <td>${c.id}</td>
                            <td>${c.name}</td>
                            <td>
                                <a class="btn btn-warning" href="category?action=edit&id=${c.id}">Edit</a>
                                <a class="btn btn-info" href="product?action=viewByCategory&id=${c.id}">View Products Of This Category</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
