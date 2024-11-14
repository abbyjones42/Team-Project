/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package music.data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import music.business.Product;


/**
 *
 * @author abigail
 */
public class ProductDB {

    // Method to add a new product
    public static int addProduct(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        String sql = "INSERT INTO Product (ProductCode, ProductDescription, ProductPrice) VALUES (?, ?, ?)";
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Method to update an existing product
    public static int updateProduct(Product product) {
        ConnectionPool pool = ConnectionPool.getInstance();
        String sql = "UPDATE Product SET ProductCode = ?, ProductDescription = ?, ProductPrice = ? WHERE ProductID = ?";
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setLong(4, product.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Method to delete a product by ID
    public static int deleteProduct(long productId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        String sql = "DELETE FROM Product WHERE ProductID = ?";
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, productId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Method to select a product by code
    public static Product selectProduct(String code) {
        ConnectionPool pool = ConnectionPool.getInstance();
        String sql = "SELECT * FROM Product WHERE ProductCode = ?";
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                    p.setId(rs.getInt("ProductID"));
                    p.setCode(rs.getString("ProductCode"));
                    p.setDescription(rs.getString("ProductDescription"));
                    p.setPrice(rs.getDouble("ProductPrice"));
                    return p;
            }     
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to select all products
    public static List<Product> selectProducts() {
        ConnectionPool pool = ConnectionPool.getInstance();
        String sql = "SELECT * FROM Product";
        List<Product> products = new ArrayList<>();
        try (Connection connection = pool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = new Product(
                    rs.getLong("ProductID"),
                    rs.getString("ProductCode"),
                    rs.getString("ProductDescription"),
                    rs.getDouble("ProductPrice")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}