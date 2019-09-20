package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Product getProduct(int productId);
    List<Product> displayAllProducts();
    List<Product> displaySamsungPhones();
    List<Product> displayXiaomiPhones();
    List<Product> displayLgPhones();
    List<Product> displayApplePhones();
    List<Product> displayOneplusPhones();
    List<Product> displayAccessories();
    void saveProduct(Product product);
    void deleteProduct(int productId);
    void editProduct(String name, String description, BigDecimal price, int itemsNumber, String picture, Boolean isAccessory, int productId);
    void subQuantity(int quantity, int productId);
}
