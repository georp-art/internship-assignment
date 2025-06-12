package warehouse.demo.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import warehouse.demo.entity.Product;

/*
* Repository interface for product entity
* Provides methods to query product by filters such as name, category, etc.
 */
@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {

    //Search products by partial name match (case insensitive).
    List<Product> findByNameContainingIgnoreCase(String name);

    //Search products by category(case insensitive).
    List<Product> findProductByCategory(String category);

    //Find products with price greater than or equal to a specific value
    List<Product> findByPriceGreaterThanEqual(BigDecimal price);

    //Find products with price less than or equal to a specific value
    List<Product> findByPriceLessThanEqual(BigDecimal price);


   //Custom query to get all non-null categories from products
   @Query("SELECT p.category FROM Product p WHERE p.category IS NOT NULL ORDER BY p.category")
     List<String> findAllCategories();

     //Find products by exact name
     List<Product> findByName(String name);

     //Find products by exact category
     List<Product> findByCategory(String category);


}
