<%-- 
    Document   : edit
    Created on : Mar 30, 2024, 4:19:36 PM
    Author     : judyh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>Edit Product</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1>Edit form</h1>
                <form action="product?action=update" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${product.id}"/>
                    <div class="mt-2 form-group">
                        <label>Name </label>
                        <input type="text" class="form-control"
                               placeholder="Enter name" name="name" value="${product.name}"/>
                    </div>
                    <div class="mt-2 form-group">
                        <label>Price:</label>
                        <input type="number" class="form-control"
                               placeholder="Enter price" name="price" value="${product.price}"/>
                    </div>
                    <div class="mt-2 form-group">
                        <label>Description </label>
                        <input type="text" class="form-control"
                               placeholder="Enter description" name="description" value="${product.description}"/>
                    </div>
                    <div class="mt-2 form-group">
                        <input type="file" class="form-control"
                               name="image">
                        <img src="uploads/${product.imageName}" width="150px" height="100px" class="my-2"/>
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Submit</button>
                    <a class="btn btn-warning mt-2" href="product">Back to list</a>
                </form>
            </div>
        </div>

    </body>
</html>
