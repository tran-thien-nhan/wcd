<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>
    </head>
    <body>
        <div class="container mt-3">
            <h2>Product List</h2>
            <a class="btn btn-primary" href="product?action=create">Create a new Product</a>
            <!--search by name-->
            <form action="product?action=search" method="post">
                <div class="input-group mb-3">
                    <input type="text" class="form-control mt-2" placeholder="Search by name" name="name">
                    <button class="btn btn-outline-secondary" type="submit">Search</button>
                </div>
            </form>
            <!--sort by price asc-->
            <a class="btn btn-info" href="product?action=sortPriceAsc">Sort by price asc</a>
            <!--sort by price desc-->
            <a class="btn btn-info" href="product?action=sortPriceDesc">Sort by price desc</a>

            <form action="product?action=filterStatus" method="post">
                <div class="input-group mb-3 mt-2">
                    <select class="form-select" name="status">
                        <!--all-->
                        <option value="All">All</option>
                        <option value="True">Active</option>
                        <option value="False">Inactive</option>
                    </select>
                    <button class="btn btn-outline-secondary" type="submit">Filter</button>
                </div>
            </form>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Image</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${products}">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.name}</td>
                            <td>${p.price}</td>
                            <td>${p.status}</td>
                            <td><img src="uploads/${p.image}" width="150px" height="100px"></td>
                            <td>
                                <a class="btn btn-warning" href="product?action=edit&id=${p.id}">Edit</a>
                                <a class="btn btn-danger" href="product?action=delete&id=${p.id}">Delete</a>
                                <a class="btn btn-info" href="product?action=updateStatus&id=${p.id}">Update Status</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
