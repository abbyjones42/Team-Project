/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package music.business;

/**
 *
 * @author jared
 */
import java.text.NumberFormat;
import java.io.Serializable;

public class Product implements Serializable {

    private long productId;
    private String code;
    private String description;
    private double price;

    public Product() {
    }

    public Product(long productID, String code, String description, double price) {
        
    }

    public long getId() {
        return productId;
    }

    public void setId(long productId) {
        this.productId = productId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getArtistName() {
        String artistName
                = description.substring(0, description.indexOf(" - "));
        return artistName;
    }

    public String getAlbumName() {
        String albumName
                = description.substring(description.indexOf(" - ") + 3);
        return albumName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }

    public String getImageURL() {
        String imageURL = "/musicStore/images/" + code + "_cover.jpg";
        return imageURL;
    }

    public String getProductType() {
        return "Audio CD";
    }
}
