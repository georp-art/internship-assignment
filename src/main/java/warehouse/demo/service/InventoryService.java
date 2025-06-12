package warehouse.demo.service;

import warehouse.demo.entity.Inventory;
import warehouse.demo.entity.Product;
import warehouse.demo.entity.Warehouse;

import java.util.List;
import java.util.Optional;

//Inventory service interface provide CRUD operations
public interface InventoryService {

    //Retrieve all records
    List<Inventory> getAllInventory();

    //Find inventory by id
    Optional<Inventory> getInventoryById(int id);

    //Create a new inventory record
    Inventory createInventory(Inventory inventory);

    //Update an existing inventory record by id
    Inventory updateInventory(Long id,Inventory inventory);

    //Delete an existing inventory record by id
    void deleteInventoryById(Long id);

    //Get all inventory items related in a specific warehouse
    List<Inventory> getInventoryByWarehouse(Warehouse warehouse);

    //Get all inventory items related in a specific product.
    List<Inventory> getInventoryByProduct(Product product);

    //Get inventory items by product and warehouse.
    List<Inventory> getInventoryByProductAndWarehouse(Product product, Warehouse warehouse);

    //Get inventory items that are below minimum stock
    List<Inventory> getLowStockInventory();




}
