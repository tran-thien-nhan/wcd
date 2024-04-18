<%@page import="models.Product"%>
<% Product product = (Product) request.getAttribute("product"); %>
<%-- 
    Document   : edit
    Created on : Mar 19, 2024, 5:16:35 PM
    Author     : judyh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>Edit</title>
    </head>
    <body>
        <div class="container mt-3">
            <h2>Edit form</h2>
            <form action="Product?action=update" method="post">
                <input type="hidden" id="id" name="id" value="<%= request.getParameter("id") %>"/>
                <div class="mb-3 mt-3">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" placeholder="Enter name" name="name" value="<%= product.getName() %>">
                </div>
                <div class="mb-3">
                    <label for="price">Price:</label>
                    <input type="text" class="form-control" id="price" placeholder="Enter price" name="price" value="<%= product.getPrice() %>">
                </div>
                <div class="mb-3">
                    <label for="quantity">Quantity:</label>
                    <input type="number" class="form-control" id="quantity" placeholder="Enter quantity" name="quantity" value="<%= product.getQuantity() %>">
                </div>
                <button type="submit" class="btn btn-primary">Update</button>
            </form>
        </div>
    </body>
</html>
