<%-- 
    Document   : create
    Created on : Mar 19, 2024, 4:51:08 PM
    Author     : judyh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>Create</title>
    </head>
    <body>
        <div class="container mt-3">
            <h2>Create form</h2>
            <form action="Product?action=create" method="post">
                <div class="mb-3 mt-3">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
                </div>
                <div class="mb-3">
                    <label for="price">Price:</label>
                    <input type="text" class="form-control" id="price" placeholder="Enter price" name="price">
                </div>
                <div class="mb-3">
                    <label for="quantity">Quantity:</label>
                    <input type="number" class="form-control" id="quantity" placeholder="Enter price" name="quantity">
                </div>
                <button type="submit" class="btn btn-primary">Create</button>
            </form>
        </div>
    </body>
</html>
