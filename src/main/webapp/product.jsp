<%-- 
    Document   : product
    Created on : Oct 26, 2024, 3:21:42 PM
    Author     : jared
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Product Maintenance</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Product</h1>
        <form action="ProductSelectServlet" method="POST">
            <input type="hidden" id="productID" name="productID" value="<%= request.getAttribute("productID") != null ? request.getAttribute("productID"): ""%>">
            <p>
                <label for="code">Code:</label>
                <input type="text" id="code" name="code" value="<%= request.getAttribute("productCode") != null ? request.getAttribute("productCode"): ""%>" required>
            </p>
            <p>
                <label for="description">Description:</label>
                <input type="text" id="description" name="description" value="<%= request.getAttribute("productDescription") != null ? request.getAttribute("productDescription"): ""%>" required>
            </p>
            <p>
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" value="<%= request.getAttribute("productPrice") != null ? request.getAttribute("productPrice"): ""%>" required>
            </p>
            <button type="submit">Update Product</button>
            <button><a href="products.html">View Products</button>
        </form>
    </body>
</html>
