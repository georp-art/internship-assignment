package warehouse.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import warehouse.demo.entity.Inventory;
import warehouse.demo.entity.Product;
import warehouse.demo.entity.Warehouse;

/*
* Repository interface for inventory entity
* Provides methods to a query inventory records based on warehouse,product and stock levels
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    //find all inventory items by warehouse id
    List<Inventory> findByWarehouse(Warehouse warehouse);

   //find all inventory items by product id
    List<Inventory> findByProduct(Product product);

    //find inventory item for a specific warehouse and product id
    List<Inventory> findByWarehouseAndProduct(Product product,Warehouse warehouse);

    /*
    * Custom query to find inventory item where quantity is less than or equals to the minimum stock levels
    * Useful for stock alerts or restocking needs
     */
  @Query("SELECT i FROM Inventory i WHERE i.quantity<=i.minimumStock")
    List<Inventory> findLowStockItems();


}
