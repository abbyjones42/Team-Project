/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package music.data;

import jakarta.persistence.*;
import jakarta.persistence.EntityManager;
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
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction JTA = em.getTransaction();
        try {
            JTA.begin();
            em.persist(product); // Insert the user entity
            JTA.commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            if (JTA.isActive()) {
                JTA.rollback();
            }
            return 0;
        } finally {
            em.close();
        }

    }

    // Method to update an existing product
    public static int updateProduct(Product product) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction JTA = em.getTransaction();
        try {
            JTA.begin();
            em.merge(product); // Insert the user entity
            JTA.commit();
            return 1;
        } catch (Exception e) {
            System.out.println(e);
            if (JTA.isActive()) {
                JTA.rollback();
            }
            return 0;
        } finally {
            em.close();
        }
    }

    // Method to delete a product by ID
    public static int deleteProduct(long productId) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction JTA = em.getTransaction();
        try {
            JTA.begin();
            Product product = em.find(Product.class, productId);
            if (product != null) {
                em.remove(product); // Remove the user entity
                JTA.commit();
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e);
            if (JTA.isActive()) {
                JTA.rollback();
            }
            return 0;
        } finally {
            em.close();
        }
    }

    // Method to select a product by code
    public static Product selectProduct(String code) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String jpql = "SELECT p FROM Product p WHERE p.code = ?";
        try {
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("code", code);
            return query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Error selecting product: " + e);
            return null;
        } finally {
            em.close();
        }
    }

    // Method to select all products
    public static List<Product> selectProducts() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        List<Product> products = new ArrayList<>();
        String jpql = "SELECT p FROM Product p";

        try {
            products = em.createQuery(jpql, Product.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error in selectProducts: " + e);
        } finally {
            em.close();
        }

        return products;
    }
}
