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
import music.data.ProductIO;

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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long productID = Long.parseLong(request.getParameter("productID"));
        String code = request.getParameter("code");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        Product product = new Product(productID, code, description, price);

        ProductDB.updateProduct(product);
        response.sendRedirect("products.html");
    }
}
