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
import music.data.ProductDB;

/**
 *
 * @author jared
 */
@WebServlet(name = "ProductSelectServlet", urlPatterns = {"/ProductSelectServlet"})
public class ProductSelectServlet extends HttpServlet {
    /*@Override
    public void init() throws ServletException {
        super.init();
        // Initialize ProductIO with the correct file path
        String filePath = getServletContext().getRealPath("/WEB-INF/product.txt");
        ProductIO.init(filePath);
    }*/

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String code = request.getParameter("code");

        // Retrieve the product using ProductDB
        Product product = ProductDB.selectProduct(code);

        if (product != null) {
            request.setAttribute("productID", product.getId());
            request.setAttribute("productCode", product.getCode());
            request.setAttribute("productDescription", product.getDescription());
            request.setAttribute("productPrice", product.getPrice());

            RequestDispatcher dispatcher = request.getRequestDispatcher("product.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("products.html"); // or show an error page
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productCode = request.getParameter("code");

        if (productCode == null || productCode.isEmpty()) {
            // Redirect to the product list if no code is provided
            response.sendRedirect("products.jsp");
            return;
        }

        
        Product product = ProductDB.selectProduct(productCode);
        if (product == null) {
            // If the product doesn't exist, redirect to the product list
            response.sendRedirect("products.jsp");
            return;
        }

        // Set product details as request attributes to be accessed in the JSP form
        request.setAttribute("product", product);

        // Forward to the product.jsp page to prepopulate the form fields
        request.getRequestDispatcher("product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long productID = Long.parseLong(request.getParameter("productID"));
        String productCode = request.getParameter("code");
        String productDescription = request.getParameter("description");
        double productPrice = Double.parseDouble(request.getParameter("price"));

        // Create a product object with the new values
        Product product = new Product(productID, productCode, productDescription, productPrice);

        // Update the product in the database
        ProductDB.updateProduct(product);

        // Redirect to the products list page after update
        response.sendRedirect("products.jsp");
    }
}