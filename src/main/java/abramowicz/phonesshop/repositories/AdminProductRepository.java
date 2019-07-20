package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminProductRepository extends JpaRepository<Product, Integer> {

    @Modifying
    @Query(value = "DELETE FROM product WHERE product_id=:id", nativeQuery = true)
    void deleteProduct(@Param("id") int id);
}
