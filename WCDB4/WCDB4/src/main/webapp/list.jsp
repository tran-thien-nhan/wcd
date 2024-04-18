

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
     <div class="container mt-3">
            <h2>Employee List</h2>
            <a class="btn btn-primary" href="employee?action=create">Create a new Employee</a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Age</th> 
                        <th>Salary</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="e" items="${employees}">
                        <tr>
                            <td>${e.id}</td>
                            <td>${e.name}</td>
                            <td>${e.age}</td>
                            <td>${e.salary}</td>
                            <td>
                                <a class="btn btn-danger" href="employee?action=delete&id=${e.id}">Delete</a>
                                <a class="btn btn-warning" href="employee?action=edit&id=${e.id}">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
