<%-- 
    Document   : list
    Created on : Mar 21, 2024, 3:32:34 PM
    Author     : judyh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>list Employee</title>
    </head>
    <body>
        <div class="container mt-3">
            <h2>List Employee</h2>   
            <a href="employee?action=create" class="btn btn-primary">create new employee</a>
            <a href="employee?action=sort&sort=age_asc" class="btn btn-primary">sort by age asc</a>
            <a href="employee?action=sort&sort=age_desc" class="btn btn-primary">sort by age desc</a>
<%--            find one employee--%>
            <form action="find" method="get">
                <input type="text" name="id" placeholder="enter id">
                <input type="submit" value="find employee" class="btn btn-primary">
            </form>
            <table class="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Age</th>
                        <th>Email</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="e" items="${employees}">
                        <tr>
                            <td>${e.id}</td>
                            <td>${e.name}</td>
                            <td>${e.age}</td>
                            <td>${e.email}</td>
                            <td>
                                <a href="employee?action=edit&id=${e.id}" class="btn btn-warning">edit</a>
                                <a href="employee?action=delete&id=${e.id}" class="btn btn-danger">delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
