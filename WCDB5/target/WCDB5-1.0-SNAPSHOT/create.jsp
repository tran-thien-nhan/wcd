<%-- 
    Document   : create
    Created on : Mar 21, 2024, 4:13:59 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1>Create User</h1>

                <form action="user?action=create" method="post">
                    <div class="mt-2 form-group">
                        <label>Username: </label>
                        <input type="text" class="form-control" value="${username}"
                               placeholder="Enter name" name="username"/>
                        <!-- put errors using errors.put("error", error);-->
                        <c:if test="${not empty errors['username']}">
                            <div class="text-danger">${errors["username"]}</div>
                        </c:if>
                    </div>
                    <div class="mt-2 form-group">
                        <label>Age: </label>
                        <input  type="number" class="form-control"
                                value="${age}"
                                placeholder="Enter age" name="age"/>
                        <c:if test="${not empty errors['age']}">
                            <div class="text-danger">${errors["age"]}</div>
                        </c:if>
                    </div>
                    <div class="mt-2 form-group">
                        <label>Password: </label>
                        <input  type="text" class="form-control"
                                value="${password}"
                                placeholder="Enter password" name="password"/>
                        <c:if test="${not empty errors['password']}">
                            <div class="text-danger">${errors["password"]}</div>
                        </c:if>
                    </div>
                    <div class="mt-2 form-group">
                        <label>Role: </label>
                        <select class="form-control" name="role">
                            <option value="admin">Admin</option>
                            <option value="user">User</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Submit</button>
                </form>
            </div>
        </div>

    </body>
</html>
