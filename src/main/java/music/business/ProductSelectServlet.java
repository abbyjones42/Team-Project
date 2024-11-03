/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package music.business;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import music.data.ProductIO;

/**
 *
 * @author jared
 */
@WebServlet(name = "ProductSelectServlet", urlPatterns = {"/ProductSelectServlet"})
public class ProductSelectServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize ProductIO with the correct file path
        String filePath = getServletContext().getRealPath("/WEB-INF/product.txt");
        ProductIO.init(filePath);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String code = request.getParameter("code");

        // Retrieve the product using ProductIO
        Product product = ProductIO.selectProduct(code);

        if (product != null) {
            // Set the product as a request attribute
            request.setAttribute("productID", product.getId());
            request.setAttribute("productCode", product.getCode());
            request.setAttribute("productDescription", product.getDescription());
            request.setAttribute("productPrice", product.getPrice());
            request.setAttribute("isUpdate", true);

            // Forward to product.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
            dispatcher.forward(request, response);
        } else {
            // Handle the case where the product is not found
            request.setAttribute("isUpdate", false);
            response.sendRedirect("products.html"); // or show an error page
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle form submission (update product)
        String productIDStr = request.getParameter("productID");
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");

        // Flag to track validation errors
        boolean errors = false;

        // Validate fields and set error messages
        if (code == null || code.trim().isEmpty()) {
            request.setAttribute("codeError", "Code is required. Please enter a valid code.");
            errors = true;
        }
        if (description == null || description.trim().isEmpty()) {
            request.setAttribute("descriptionError", "Description is required. Please enter a valid description.");
            errors = true;
        }

        double price = 0;
        if (priceStr == null || priceStr.trim().isEmpty()) {
            request.setAttribute("priceError", "Price is required. Please enter a valid price. (Ex. 19.99)");
            errors = true;
        } else {
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                request.setAttribute("priceError", "Invalid price format.");
                errors = true;
            }
        }

        // If there are errors, forward back to the form with errors
        if (errors) {
            request.setAttribute("productCode", code);
            request.setAttribute("productDescription", description);
            request.setAttribute("productPrice", priceStr);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Create or update product if there are no errors
        Product product = new Product();

        // Set product ID if updating an existing product
        if (productIDStr != null && !productIDStr.isEmpty()) {
            long productID = Long.parseLong(productIDStr);
            product.setId(productID);
        } else {
            // Assign a new ID if inserting a new product
            List<Product> productList = ProductIO.selectProducts();
            long maxId = productList.stream()
                    .mapToLong(Product::getId)
                    .max()
                    .orElse(0);
            product.setId(maxId + 1);
        }

        // Set product fields from validated input
        product.setCode(code);
        product.setDescription(description);
        product.setPrice(price);

        // Save or update the product in the database
        if (productIDStr != null && !productIDStr.isEmpty()) {
            ProductIO.updateProduct(product);
        } else {
            ProductIO.insertProduct(product);
        }

        // Redirect to product list or confirmation page
        response.sendRedirect("products.html");
    }
}
