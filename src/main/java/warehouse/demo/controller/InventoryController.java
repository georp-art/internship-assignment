package warehouse.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warehouse.demo.entity.Inventory;
import warehouse.demo.entity.Product;
import warehouse.demo.entity.Warehouse;
import warehouse.demo.service.InventoryService;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }
    //Get all inventory records
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventory = inventoryService.getAllInventory();
        return ResponseEntity.ok(inventory);
    }

    //Get inventory by id
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@RequestParam int id) {
        return inventoryService.getInventoryById(id)
                .map(inventory ->ResponseEntity.ok(inventory))
                .orElse(ResponseEntity.notFound().build());
    }

    //Create a new inventory record
    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        Inventory created = inventoryService.createInventory(inventory);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //Update inventory by id
    @PutMapping("{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable long id, @RequestBody Inventory inventory) {
        try{
            Inventory updated = inventoryService.updateInventory(id, inventory);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Delete inventory by id
    @DeleteMapping("{id}")
    public ResponseEntity<Inventory> deleteInventory(@PathVariable long id) {
        try{
            inventoryService.deleteInventoryById(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Get inventory by warehouse
    @GetMapping("/search/warehouse")
    public ResponseEntity<List<Inventory>> getInventoryByWarehouse(@RequestParam Warehouse warehouse) {
       List<Inventory> inventory = inventoryService.getInventoryByWarehouse(warehouse);
       return ResponseEntity.ok(inventory);
    }

    //Get inventory by product
    @GetMapping("/search/product")
    public ResponseEntity<List<Inventory>> getInventoryByProduct(@RequestParam Product product) {
       List<Inventory> inventory = inventoryService.getInventoryByProduct(product);
       return ResponseEntity.ok(inventory);
    }

    //Get inventory by product and warehouse
    @GetMapping("/warehouse/product")
    public ResponseEntity<List<Inventory>> getInventoryByProductAndWarehouse(@RequestParam Product product, @RequestParam Warehouse warehouse) {
        List<Inventory> inventory = inventoryService.getInventoryByProductAndWarehouse(product, warehouse);
        return ResponseEntity.ok(inventory);
    }

    //Get inventory items that are low in stock
    @GetMapping("/low/stock")
    public ResponseEntity<List<Inventory>> getLowStockInventory() {
        List<Inventory> inventory = inventoryService.getLowStockInventory();
        return ResponseEntity.ok(inventory);
    }
}



