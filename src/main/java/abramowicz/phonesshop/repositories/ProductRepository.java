package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product getProductByProductId(int productId);

    List<Product> getProductsByBrand(String brand);

    @Query(value = "SELECT p FROM Product p ORDER BY name")
    List<Product> displayAllProducts();

    @Query(value = "SELECT p FROM Product p WHERE p.isAccessory = 1")
    List<Product> displayAccessories();

    void deleteProductByProductId(int productId);

    @Modifying
    @Query(value = "UPDATE Product p SET p.name=:name, p.description=:description, p.price=:price, p.itemsNumber=:itemsNumber, p.picture=:picture, p.isAccessory=:isAccessory WHERE p.productId=:productId")
    void editProduct(@Param("name") String name, @Param("description") String description, @Param("price") BigDecimal price, @Param("itemsNumber") int itemsNumber, @Param("picture") String picture, @Param("isAccessory") Boolean isAccessory, @Param("productId") int productId);

    @Modifying
    @Query(value = "UPDATE Product p SET p.itemsNumber=p.itemsNumber - :quantity WHERE p.productId=:productId")
    void subQuantity(@Param("quantity") int quantity, @Param("productId") int productId);

    @Modifying
    @Query(value = "UPDATE Product p SET p.itemsNumber=p.itemsNumber + :quantity WHERE p.productId=:productId")
    void addQuantity(@Param("quantity") int quantity, @Param("productId") int productId);

}
