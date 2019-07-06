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
    public List<Product> displayProductsList(){
        return productRepository.displayProductsList();
    }

}
