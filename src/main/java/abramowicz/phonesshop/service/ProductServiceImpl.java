package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.Product;
import abramowicz.phonesshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    @Override
    public List<Product> displayAllProducts() {
        return productRepository.displayAllProducts();
    }

    @Override
    public List<Product> displaySamsungPhones(){
        return productRepository.displaySamsungPhones();
    }

    @Override
    public List<Product> displayXiaomiPhones(){
        return productRepository.displayXiaomiPhones();
    }

    @Override
    public List<Product> displayLgPhones() {
        return productRepository.displayLgPhones();
    }

    @Override
    public List<Product> displayApplePhones() {
        return productRepository.displayApplePhones();
    }

    @Override
    public List<Product> displayOneplusPhones() {
        return productRepository.displayOneplusPhones();
    }

    @Override
    public List<Product> displayAccessories() {
        return productRepository.displayAccessories();
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteProduct(productId);
    }

    @Override
    public void editProduct(String name, String description, BigDecimal price, int itemsNumber, String picture, Boolean isAccessory, int productId) {
        productRepository.editProduct(name, description, price, itemsNumber, picture, isAccessory, productId);
    }

    @Override
    public void subQuantity(int quantity, int productId){
        productRepository.subQuantity(quantity, productId);
    }

    @Override
    public void addQuantity(int quantity, int productId) {
        productRepository.addQuantity(quantity, productId);
    }
}
