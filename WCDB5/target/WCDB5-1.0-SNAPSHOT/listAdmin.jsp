<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List Admin</title>
</head>
<body>
<div class="container mt-3">
    <h2>Admin List</h2>
    <a class="btn btn-primary" href="login?action=logout">Logout</a>
    <a class="btn btn-primary" href="user?action=create">Create a new user</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Id</th>
            <th>Username</th>
            <th>role</th>
            <th>password</th>
            <th>age</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="u" items="${admins}">
            <tr>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>${u.role}</td>
                <td>${u.password}</td>
                <td>${u.age}</td>
                <td>
                    <a class="btn btn-danger" href="user?action=delete&id=${u.id}">Delete</a>
                    <a class="btn btn-warning" href="user?action=edit&id=${u.id}">Edit</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
