package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.Product;
import abramowicz.phonesshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

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
}
