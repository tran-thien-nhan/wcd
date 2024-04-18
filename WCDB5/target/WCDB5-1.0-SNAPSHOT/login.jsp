<%-- 
    Document   : login
    Created on : Mar 28, 2024, 3:52:30 PM
    Author     : judyh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>Login Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4 offset-md-4 my-4">
                    <h2>Login</h2>
                    <c:if test="${not empty invalid}">
                        <div class="alert alert-danger"><strong>Danger!</strong>${invalid}</div>
                    </c:if>
                    <form action="login" method="post">
                        <div class="form-group
                                <label for="username">Username</label>
                                <input type="text" name="username" class="form-control" required>
                        </div>
                        <div class="form-group
                                <label for="password">Password</label>
                                <input type="password" name="password" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-primary my-2">Login</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
