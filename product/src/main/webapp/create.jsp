<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
        <title>Create Product</title>
    </head>
    <body>
        <div class="container">
            <h1>Create Product</h1>
            <form action="product?action=create" method="post">
                <div class="mb-3 mt-3">
                    <label for="name" class="form-label">Name: </label>
                    <input type="text" class="form-control" id="name" placeholder="Enter name" name="name" value="${name}">
                    <c:if test="${not empty errors['name']}">
                        <div class="text-danger">${errors["name"]}</div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Price: </label>
                    <input type="text" class="form-control" id="price" placeholder="Enter price" name="price" value="${price}">
                    <c:if test="${not empty errors['price']}">
                        <div class="text-danger">${errors["price"]}</div>
                    </c:if>
                </div>
                <div class="mb-3">
                    <label for="quantity" class="form-label">Quantity: </label>
                    <input type="number" class="form-control" id="quantity" placeholder="Enter quantity" name="quantity" value="${quantity}">
                    <c:if test="${not empty errors['quantity']}">
                        <div class="text-danger">${errors["quantity"]}</div>
                    </c:if>
                </div>
                <button type="submit" class="btn btn-primary">Create</button>
                <a href="product?action=list" class="btn btn-danger my-2">Back</a>
            </form>
        </div>
    </body>
</html>
