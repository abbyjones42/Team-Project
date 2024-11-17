<%-- 
    Document   : products
    Created on : Oct 26, 2024, 4:25:25 PM
    Author     : jared
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="music.business.Product" %>
<%@ page import="music.data.ProductDB" %>

<%
    List<Product> products = ProductDB.selectProducts();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
    <style>
        table {
            border-collapse: collapse;
            border: 2px solid black;
            width: 100%;
        }
        th, td {
            padding: 8px;
            border: 2px solid black;
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
        <% for (Product product : products) { %>
        <tr>
            <td><%= product.getCode() %></td>
            <td><%= product.getDescription() %></td>
            <td><%= product.getPriceCurrencyFormat() %></td>
            <td><a href="ProductSelectServlet?code=<%= product.getCode() %>">Edit</a></td>
            <td><a href="ProductDeleteServlet?code=<%= product.getCode() %>">Delete</a></td>
        </tr>
        <% } %>
    </table>
    <button><a href="product.jsp">Add Product</a></button>
</body>
</html>