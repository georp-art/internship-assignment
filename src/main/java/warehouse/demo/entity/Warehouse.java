package warehouse.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import java.math.BigDecimal;

/*
* Warehouse entity represents o physical storage location for products
* Contains attributes such as name,address,capacity,etc.
 */
@Entity
@Table(name = "warehouses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Warehouse extends BaseEntity {

    /*
    * Name of the warehouse
    * Cannot be null and has a maximum length of 100 characters
     */
 @Column(name = "name", nullable = false, length = 100)
    private String name;

 /*
 *Address of the warehouse
 * Stored as a text to allow for long, detailed addresses
  */
 @Column(name = "address", nullable = false, columnDefinition = "text")
    private String address;

 /*
 * Maximum storage capacity of the warehouse
 * BigDecimal used for precision
  */
 @Column(name= "capacity", nullable = false, precision = 10,scale =2)
  private BigDecimal capacity;

 //Name of the manager that is responsible fpr the warehouse
 @Column(name = "manager_name", nullable = false, length = 100)
    private String managerName;

/*
* One-to-many relationship with the Inventory entity.
* One warehouse can have many inventory records.
* CascadeType.ALL propagates all operations (persist, delete, etc.) to child inventories.
* JsonManagedReference supports JSON serialization of bidirectional relationships.
*/
    @OneToMany(mappedBy="warehouse",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Inventory> inventories;



}
