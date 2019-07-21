package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.Product;

import java.util.List;

public interface ProductService {

    List<Product> displayAllProducts();
    List<Product> displaySamsungPhones();
    List<Product> displayXiaomiPhones();
    List<Product> displayLgPhones();
    List<Product> displayApplePhones();
    List<Product> displayOneplusPhones();
    List<Product> displayAccessories();
    void saveProduct(Product product);
    void deleteProduct(int productId);
}
