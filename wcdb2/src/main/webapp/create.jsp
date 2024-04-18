<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>Create Page</title>
    </head>
    <body>
        <div class="container">
            <h1>Create Employee</h1>
            <form action="employee?action=create" method="post">
                <div class="mb-3 mt-3">
                    <label for="name" class="form-label">Name: </label>
                    <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
                </div>
                <div class="mb-3">
                    <label for="age" class="form-label">Age: </label>
                    <input type="number" class="form-control" id="age" placeholder="Enter age" name="age">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email: </label>
                    <input type="text" class="form-control" id="email" placeholder="Enter email" name="email">
                </div>
                <button type="submit" class="btn btn-primary">Create</button>
            </form>
        </div>
    </body>
</html>
