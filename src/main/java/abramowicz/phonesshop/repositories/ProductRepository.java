package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> displayProductsList();
}
