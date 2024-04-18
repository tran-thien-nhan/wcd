<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <form action="product?action=edit" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="<c:out value="${product.id}"/>"/>
            <div class="mt-2 form-group">
                <label>Name </label>
                <input type="text" class="form-control"
                       placeholder="Enter name" name="name" value="<c:out value="${product.name}"/>"/>
            </div>
            <div class="mt-2 form-group">
                <label>Price:</label>
                <input type="number" class="form-control"
                       placeholder="Enter price" name="price" value="<c:out value="${product.price}"/>"/>
            </div>
            <!--status-->
            <div class="mt-2 form-group">
                <label>Status:</label>
                <select class="form-control" name="status">
                    <option value="1" <c:if test="${product.status == true}">selected</c:if>>Active</option>
                    <option value="0" <c:if test="${product.status == false}">selected</c:if>>Inactive</option>
                </select>
            </div>
            <div class="mt-2 form-group">
                <input type="file" class="form-control"
                       name="image">
                <img src="uploads/<c:out value="${product.image}"/>" width="150px" height="100px" class="my-2"/>
            </div>
            <input type="hidden" name="oldFileName" value="${product.image}">
            <button type="submit" class="btn btn-primary mt-2">Submit</button>
            <a class="btn btn-warning mt-2" href="product">Back to list</a>
        </form>
    </div>
</div>
</body>
</html>
