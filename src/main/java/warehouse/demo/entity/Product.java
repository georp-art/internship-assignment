package warehouse.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/*
* Product entity represents item stored in the warehouse
* It contains details such as name,price,category,etc.
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product extends BaseEntity {

    /*
    * Name of the product
    * Cannot be null and has a maximum length of 100 characters
     */
    @Column(nullable = false, length = 100)
    private String name;

    /*
     *Description of the product
     * Cannot be null and has a maximum length of 100 characters
     */
    @Column(name = "description", nullable = false, length = 100)
    private String description;

    /*
     * Price of the product.
     * BigDecimal ensures precise monetary value, with up to 10 digits in total and 2 decimal places
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    // Product categories
    @Column(name = "category", nullable = false, length = 100)
    private String category;

    //Weight of the product
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal weight;

    /*
     *One-to-many relationship with Inventory.
     * One product can exist in multiple inventories across different warehouses.
     * CascadeType.ALL ensures that changes to a product (like delete) cascade to related inventory records.
     * JsonBackReference prevents infinite recursion during JSON serialization.
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Inventory> inventories;

}


