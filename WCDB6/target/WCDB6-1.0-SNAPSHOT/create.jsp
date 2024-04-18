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
                    </div>
                    <div class="mt-2 form-group">
                        <label>Price:</label>
                        <input type="number" class="form-control" 
                                placeholder="Enter price" name="price"/>
                    </div>
                      <div class="mt-2 form-group">
                        <label>Description </label>
                        <input type="text" class="form-control" 
                               placeholder="Enter description" name="description"/>
                    </div>
                    <div class="mt-2 form-group">
                        <label>Image </label>
                        <input type="file" class="form-control" 
                               name="image">
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Submit</button>
                    <a class="btn btn-warning mt-2" href="product">Back to list</a>
                </form>
            </div>
        </div>

    </body>
</html>