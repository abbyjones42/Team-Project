<%-- 
    Document   : confirmdelete
    Created on : Oct 27, 2024, 7:46:56 PM
    Author     : abigail jones
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Deletion Page</title>
    </head>
    <body>
        <h2>Confirm Deletion</h2>
        <p>Are you sure you want to delete the following item?</p>
        <p>Code: <%= request.getAttribute("productCode") != null ? request.getAttribute("productCode") : "" %></p>
        <p>Description: <%= request.getAttribute("productDescription") != null ? request.getAttribute("productDescription") : "" %></p>
        <p>Price: <%= request.getAttribute("productPrice") != null ? request.getAttribute("productPrice") : "" %></p>

        <form action="ProductDeleteServlet" method="POST">
            <input type="hidden" name="productID" value="<%= request.getAttribute("productID") %>"/>
            <button type="submit">Delete</button>
            <button type="button"><a href="products.jsp">Cancel</button></a>
        </form>
    </body>
</html>
