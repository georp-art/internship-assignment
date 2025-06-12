package warehouse.demo.service;

import warehouse.demo.entity.Product;

import java.util.List;
import java.util.Optional;

//Inventory service interface provide CRUD operations
public interface ProductService {
    //Get all products
    List<Product> getAllProducts();

    //Get product by id
    Optional<Product> getProductById(long id);

    //Create a new product
    Product createProduct(Product product);

    //Update an existing product by id
    Product updateProduct(Long id, Product product);

    //Delete a product by id
    void deleteProduct(Long id);

    //Search products by name
    List<Product> findProductsByName(String name);

    //Search products by category
    List<Product> findProductsByCategory(String category);
}
