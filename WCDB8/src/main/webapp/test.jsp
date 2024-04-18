<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
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
        <h1>Demo JSTL</h1>
        <c:set var="name" value="Nguyen Van A" />
        <h1>
            Name: <c:out value="${name}" />
        </h1>
        <c:remove var="name" />
        <h1>After removed:</h1>
        <c:if test="${empty name}">
            <h1>
                Name: <c:out value="${name}" />
            </h1>
        </c:if>
        <h1>list of books:</h1>
        <c:set var="books" value="${['Java', 'PHP', 'C#']}" />
        <ul>
            <c:forEach items="${books}" var="book">
                <li>
                    <h2>
                        <c:out value="${book}" />
                    </h2>
                </li>
            </c:forEach>
        </ul>
        <c:set var="age" value="25" />
        <c:choose>
            <c:when test="${age lt 18}"> <!-- age < 18 -->
                <h1>Child</h1>
            </c:when>
            <c:when test="${age ge 18 and age lt 30}"> <!-- 18 <= age < 30 -->
                <h1>Young</h1>
            </c:when>
            <c:otherwise>
                <h1>Old</h1>
            </c:otherwise>
        </c:choose>
        <sql:setDataSource var="dataSource" driver="com.mysql.cj.jdbc.Driver"
                           url="jdbc:mysql://localhost:3306/wcdb7db" user="root" password="" />
        <sql:query var="result" dataSource="${dataSource}">
            SELECT * FROM products
        </sql:query>
        <div class="container mt-3">
            <table class="table table-striped">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Price</th>
                </tr>
                <c:forEach var="row" items="${result.rows}">
                    <tr>
                        <td><c:out value="${row.id}" /></td>
                        <td><c:out value="${row.name}" /></td>
                        <td><c:out value="${row.price}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
