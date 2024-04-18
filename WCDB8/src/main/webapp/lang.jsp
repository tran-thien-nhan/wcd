<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="messages"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h1>MULTIPLE LANGUAGE</h1>
            <form action="language" class="my-2">
                <label class="form-label">Select Language</label>
                <select class="form-select" name="lang">
                    <option value="en">English</option>
                    <option value="fr">France</option>
                    <option value="german">German</option>
                </select>
                <button type="submit" class="btn btn-primary my-2">Submit</button>
            </form>
            <div class="card" style="width:400px">
                <img class="card-img-top" src="https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_square.jpg" alt="Card image" style="width:100%">
                <div class="card-body">
                    <h4 class="card-title"><fmt:message key="welcome"/> ! </h4>
                    <h4 class="card-title"><fmt:message key="name"/>: Nhan </h4>
                    <h4 class="card-title"><fmt:message key="age"/>: 26 </h4>
                    <h4 class="card-title"><fmt:message key="bio"/>: read book </h4>
                </div>
            </div>
        </div>
    </body>
</html>
