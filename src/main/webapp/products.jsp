<%-- 
    Document   : products
    Created on : Oct 26, 2024, 4:25:25 PM
    Author     : jared
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="music.business.Product" %>
<%@ page import="music.data.ProductIO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Maintenance</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        table {
            border-collapse: collapse;
            border: 2px solid black;
            width: 100%;
        }
        th, td {
            border: 2px solid black;
            padding: 8px 10px;
        }
    </style>
</head>
<body>
    <h1>Products</h1>
    <table>
        <tr>
            <th>Code</th>
            <th>Description</th>
            <th>Price</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <%
            List<Product> productList = (List<Product>) request.getAttribute("productList");
            for (Product p : productList) {
        %>
        <tr>
            <td><%= p.getCode() %></td>
            <td><%= p.getDescription() %></td>
            <td>$<%= p.getPrice() %></td>
            <td><a href="ProductSelectServlet?code=<%= p.getCode() %>">Edit</a></td>
            <td><a href="confirmdelete.html">Delete</a></td>
        </tr>
        <%
            }
        %>
    </table>
     <button onclick= "document.location='product.jsp'">Add product</a></button>
</body>
</html>
