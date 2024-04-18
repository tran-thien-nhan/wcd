<%--
  Created by IntelliJ IDEA.
  User: judyh
  Date: 3/23/2024
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>Edit Product</title>
    </head>
    <body>
        <div class="container">
            <h1>Edit Product</h1>
            <form action="product?action=update" method="post">
                <input type="hidden" name="id" value="${product.id}">
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${product.name}">
                    <c:if test="${not empty errors['name']}">
                        <div class="text-danger">${errors["name"]}</div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Price</label>
                    <input type="text" class="form-control" id="price" name="price" value="${product.price}">
                    <c:if test="${not empty errors['price']}">
                        <div class="text-danger">${errors["price"]}</div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="quantity" class="form-label">Quantity</label>
                    <input type="text" class="form-control" id="quantity" name="quantity" value="${product.quantity}">
                    <c:if test="${not empty errors['quantity']}">
                        <div class="text-danger">${errors["quantity"]}</div>
                    </c:if>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
                <a href="product?action=list" class="btn btn-danger my-2">Back</a>
            </form>
    </body>
</html>
