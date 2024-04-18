<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todo List</title>
    </head>
    <body>
        <div class="container mt-3">
            <h2>Todo List</h2>
            <!-- form to enter id to delete with a delete button-->
            <form action="todo?action=delete" method="post">
                <div class="form-group">
                    <label for="id">Enter ID to delete:</label>
                    <input type="text" class="form-control" id="id" name="id">
                    <c:if test="${not empty errors['id']}">
                        <div class="text-danger">${errors["id"]}</div>
                    </c:if>
                </div>
                <button type="submit" class="btn btn-danger">Delete</button>
            </form>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Dated</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="t" items="${todo}">
                        <tr>
                            <td>${t.id}</td>
                            <td>${t.title}</td>
                            <td>${t.dated}</td>
                            <td>${t.status}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
