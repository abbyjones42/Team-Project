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
 * @author abigail jones
 */
@WebServlet(name = "ProductDeleteServlet", urlPatterns = {"/ProductDeleteServlet"})
public class ProductDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productCode = request.getParameter("code");

        if (productCode == null || productCode.isEmpty()) {
            response.sendRedirect("products.jsp");
            return;
        }

        // Retrieve the product from the database by code
        Product product = ProductDB.selectProduct(productCode);
        if (product == null) {
            response.sendRedirect("products.jsp");
            return;
        }

        // Set the product attributes to forward to confirmdelete.jsp
        request.setAttribute("productID", product.getId());
        request.setAttribute("productCode", product.getCode());
        request.setAttribute("productDescription", product.getDescription());
        request.setAttribute("productPrice", product.getPriceCurrencyFormat());

        
        request.getRequestDispatcher("confirmdelete.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the productID from the form
        String productIDStr = request.getParameter("productID");

        if (productIDStr == null || productIDStr.isEmpty()) {
            response.sendRedirect("products.jsp");
            return;
        }

        try {
            long productID = Long.parseLong(productIDStr);

            // Delete the product from the database by ID
            ProductDB.deleteProduct(productID);

            
            response.sendRedirect("products.jsp");
        } catch (NumberFormatException e) {
            response.sendRedirect("products.jsp");
        }
    }
}
