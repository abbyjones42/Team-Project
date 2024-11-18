/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package music.data;

/**
 *
 * @author marwanarbab
 */
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DBUtil {
    // Create the EntityManagerFactory instance once for the entire application
    private static final EntityManagerFactory emf;

    static {
        EntityManagerFactory tempEmf = null;
        try {
            tempEmf = Persistence.createEntityManagerFactory("music_jpa");
        } catch (Exception e) {
            System.err.println("EntityManagerFactory initialization failed: " + e);
            e.printStackTrace();
        }
        emf = tempEmf;
    }

    // Method to retrieve the EntityManagerFactory instance
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}