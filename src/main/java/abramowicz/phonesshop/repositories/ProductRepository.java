package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product ORDER BY name", nativeQuery = true)
    List<Product> displayAllProducts();

    @Query(value = "SELECT * FROM product p WHERE p.name LIKE 'Samsung%' ", nativeQuery = true)
    List<Product> displaySamsungPhones();

    @Query(value = "SELECT * FROM product p WHERE p.name LIKE 'Xiaomi%' ", nativeQuery = true)
    List<Product> displayXiaomiPhones();

    @Query(value = "SELECT * FROM product p WHERE p.name LIKE 'LG%' ", nativeQuery = true)
    List<Product> displayLgPhones();

    @Query(value = "SELECT * FROM product p WHERE p.name LIKE 'iPhone%' ", nativeQuery = true)
    List<Product> displayApplePhones();

    @Query(value = "SELECT * FROM product p WHERE p.name LIKE 'OnePlus%' ", nativeQuery = true)
    List<Product> displayOneplusPhones();

    @Query(value = "SELECT * FROM product p WHERE p.is_accessory = 1", nativeQuery = true)
    List<Product> displayAccessories();

    @Modifying
    @Query(value = "DELETE FROM product WHERE product_id=:productId", nativeQuery = true)
    void deleteProduct(@Param("productId") int productId);

}
