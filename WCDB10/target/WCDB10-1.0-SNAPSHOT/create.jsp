<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1>Create form</h1>
                <form action="product?action=create" method="post" enctype="multipart/form-data">
                    <div class="mt-2 form-group">
                        <label>Name </label>
                        <input type="text" class="form-control" 
                               placeholder="Enter name" name="name"/>
                        <c:if test="${not empty errors['name']}">
                            <div class="text-danger">${errors["name"]}</div>
                        </c:if>
                    </div>
                    <div class="mt-2 form-group">
                        <label>Price:</label>
                        <input type="number" class="form-control" 
                                placeholder="Enter price" name="price"/>
                        <c:if test="${not empty errors['price']}">
                            <div class="text-danger">${errors["price"]}</div>
                        </c:if>
                    </div>
                    <!--status-->
                    <div class="mt-2 form-group">
                        <label>Status:</label>
                        <select class="form-control" name="status">
                            <option value="True">Active</option>
                            <option value="False">Inactive</option>
                        </select>
                    </div>
                    <div class="mt-2 form-group">
                        <label>Image </label>
                        <input type="file" class="form-control" 
                               name="image">
                        <c:if test="${not empty errors['image']}">
                            <div class="text-danger">${errors["image"]}</div>
                        </c:if>
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Submit</button>
                    <a class="btn btn-warning mt-2" href="product">Back to list</a>
                </form>
            </div>
        </div>

    </body>
</html>