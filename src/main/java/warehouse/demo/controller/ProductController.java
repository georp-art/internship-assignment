package warehouse.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import warehouse.demo.entity.Product;
import warehouse.demo.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins ="*")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    //Get products by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@RequestParam Long id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(product))
                .orElse(ResponseEntity.notFound().build());
    }

    //Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);

    }

    //Update product by id
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try{
            Product updated = productService.updateProduct(id, product);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //Delete product by id
    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try{
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Search product by name(case insensitive)
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchByName(@RequestParam String name) {
        List<Product> products = productService.findProductsByName(name);
        return ResponseEntity.ok(products);
    }

    //Search products by categpry
    @GetMapping("/category")
    public ResponseEntity<List<Product>> searchByCategory(@RequestParam String category) {
        List<Product> products = productService.findProductsByCategory(category);
        return ResponseEntity.ok(products);
    }
}
