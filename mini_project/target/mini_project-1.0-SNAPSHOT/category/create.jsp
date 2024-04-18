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
                <h1>Create category form</h1>
                <form action="category?action=create" method="post">
                    <div class="mt-2 form-group">
                        <label>Category Name </label>
                        <input type="text" class="form-control" 
                               placeholder="Enter category name" name="name"/>
                    </div>
                    <button type="submit" class="btn btn-primary mt-2">Create</button>
                    <a class="btn btn-warning mt-2" href="category">Back to list category</a>
                </form>
            </div>
        </div>

    </body>
</html>