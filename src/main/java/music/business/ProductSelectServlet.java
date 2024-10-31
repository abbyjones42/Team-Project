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

            // Forward to product.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
            dispatcher.forward(request, response);
        } else {
            // Handle the case where the product is not found
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
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product();

        if (productIDStr != null && !productIDStr.isEmpty()) {
            // Update existing product
            long productID = Long.parseLong(productIDStr);
            product.setId(productID);
        } else {
            // Insert new product with an incremented ID
            List<Product> productList = ProductIO.selectProducts();
            long maxId = productList.stream()
                    .mapToLong(Product::getId)
                    .max()
                    .orElse(0);
            product.setId(maxId + 1);
        }

        product.setCode(code);
        product.setDescription(description);
        product.setPrice(price);

        if (productIDStr != null && !productIDStr.isEmpty()) {
            ProductIO.updateProduct(product);
        } else {
            ProductIO.insertProduct(product);
        }

        // Redirect to the product list or confirmation page
        response.sendRedirect("products.html");
    }
}
