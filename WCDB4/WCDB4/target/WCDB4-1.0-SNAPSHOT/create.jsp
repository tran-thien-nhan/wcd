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
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1>Create form</h1>
                
                <form action="employee?action=create" method="post">
                    <div class="mt-2 form-group">
                        <label>Name: </label>
                        <input type="text" class="form-control" value="${name}"
                               placeholder="Enter name" name="name"/>
                    </div>
                    <div class="mt-2 form-group">
                        <label>Age </label>
                        <input  type="number" class="form-control" 
                                value="${age}"
                                placeholder="Enter age" name="age"/>
                    </div>
                    <div class="mt-2 form-group">
                        <label>Salary </label>
                        <input  type="number" class="form-control" 
                                value="${salary}"
                                placeholder="Enter salary" name="salary"/>
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Submit</button>
                </form>
            </div>
        </div>

    </body>
</html>
