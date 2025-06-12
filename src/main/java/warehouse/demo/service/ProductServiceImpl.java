package warehouse.demo.service;

import org.springframework.stereotype.Service;
import warehouse.demo.entity.Product;
import warehouse.demo.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

// Implementation of ProductService using ProductRepository.
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product){
       return productRepository.findById(id)
        .map(existing ->{
            existing.setName(product.getName());
            existing.setDescription(product.getDescription());
            existing.setPrice(product.getPrice());
            existing.setCategory(product.getCategory());
            existing.setWeight(product.getWeight());

            return productRepository.save(existing);
         })
               .orElseThrow (()-> new RuntimeException("Product does not found:"+ id));
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductsByName (String name){
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findProductsByCategory (String category){
        return productRepository.findByCategory(category);
    }
}
