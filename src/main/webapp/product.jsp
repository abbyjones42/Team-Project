<%-- 
    Document   : product
    Created on : Oct 26, 2024, 3:21:42 PM
    Author     : jared
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="validation" uri="/tlds/validation" %>
<!DOCTYPE html>

    <head>
        <title>Product Maintenance</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
        <h1>Product</h1>
        <form action="ProductSelectServlet" method="POST">
            <input type="hidden" id="productID" name="productID" value="<%= request.getAttribute("productID") != null ? request.getAttribute("productID"): ""%>">
            <p>
                <label for="code">Code:</label>
                <input type="text" id="code" name="code" value="<%= request.getAttribute("productCode") != null ? request.getAttribute("productCode"): ""%>">
                <validation:ValidationCustomTag entryName="Code" entryValue='<%= request.getAttribute("productCode") %>' />
                <span style="color:red"><%= request.getAttribute("codeError") != null ? request.getAttribute("codeError") : "" %></span>
            </p>
            <p>
                <label for="description">Description:</label>
                <input type="text" id="description" name="description" value="<%= request.getAttribute("productDescription") != null ? request.getAttribute("productDescription"): ""%>">
                <validation:ValidationCustomTag entryName="Description" entryValue='<%= request.getAttribute("productDescription") %>' />
                <span style="color:red"><%= request.getAttribute("descriptionError") != null ? request.getAttribute("descriptionError") : "" %></span>
            </p>
            <p>
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" value="<%= request.getAttribute("productPrice") != null ? request.getAttribute("productPrice"): ""%>">
                <validation:ValidationCustomTag entryName="Price" entryValue='<%= request.getAttribute("productPrice") %>' />
                <span style="color:red"><%= request.getAttribute("priceError") != null ? request.getAttribute("priceError") : "" %></span>
            </p>
            <button type="submit">Update Product</button>
            <button><a href="products.html">View Products</button>
        </form>
    

