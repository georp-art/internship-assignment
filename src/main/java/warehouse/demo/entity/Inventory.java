package warehouse.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/*
 * Inventory entity represents the stock details of a product in a specific warehouse.
 * It maps a many-to-one relationship with both Warehouse and Product entities.
 */
@Entity
@Table(name="inventory", uniqueConstraints = @UniqueConstraint(columnNames = {"warehouse_id","product_id"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Inventory extends BaseEntity{

 /*
  * Many inventories can belong to one warehouse.
  * JSON back reference is used to prevent circular serialization issues.
  */
 @ManyToOne
 @JoinColumn(name = "warehouse_id",nullable = false)
 @JsonBackReference
 private Warehouse warehouse;

 /*
 * Many inventories can reference one product.
 * JSON managed reference is used to support bidirectional serialization.
  */
 @ManyToOne
 @JoinColumn(name = "product_id",nullable = false)
 @JsonManagedReference
 private Product product;

 /*
 *Quantity of the product currently in stock
 * BigDecimal is used for precise numerical representation
  */
 @Column(nullable = false, precision = 10, scale = 2)
 private BigDecimal quantity;

 /*
 * Minimum stock level required for the product in this warehouse
 * Helps in generating low-stock alerts or automatic restocking
  */
 @Column(name = "min_stock", nullable = false, precision = 10, scale = 2)
 private BigDecimal minimumStock;

 /*
 * Maximum stock level allowed for this product in this warehouse
 * Can be used to control overstocking
  */
 @Column(name = "max_stock", nullable = false, precision = 10, scale = 2)
 private BigDecimal maximumStock;

}
