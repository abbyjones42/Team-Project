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
        
        <form action="ProductSelectServlet" method="POST">
         <p>
                <label for="code">Code:</label>
                <input type="text" id="code" name="code" value="<%= request.getAttribute("productCode")%>" required>
            </p>
            <p>
                <label for="description">Description:</label>
                <input type="text" id="description" name="description" value="<%= request.getAttribute("productDescription")%>" required>
            </p>
            <p>
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" value="<%= request.getAttribute("productPrice")%>" required>
            </p>
        </form>
 
        <form action="deleteProduct" method="POST">
            <input type="hidden" name="code" value="${productCode}"/>
            <button type="submit">Delete</button>
            <button onclick = "products.html">Cancel</button>
        </form>
</body>
</html>
