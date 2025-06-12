package warehouse.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warehouse.demo.entity.Warehouse;
import warehouse.demo.service.WarehouseService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@CrossOrigin(origins = "*")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {

        this.warehouseService =warehouseService;
    }

    //Get all warehouses
    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

  //Get warehouse by id
    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) {
        return warehouseService.getWarehouseById(id)
        .map(warehouse-> ResponseEntity.ok(warehouse))
                .orElse(ResponseEntity.notFound().build());
    }

 //Create a new warehouse
    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse created = warehouseService.createWarehouse(warehouse);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //Update warehouse by id
    @PutMapping("/{id}")
     public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse warehouse) {
         try {
             Warehouse updated = warehouseService.updateWarehouse(id, warehouse);
             return ResponseEntity.ok(updated);
         }  catch (RuntimeException e) {
             return ResponseEntity.notFound().build();
         }
      }

 //Delete warehouse by id
@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        try{
            warehouseService.deleteWarehouse(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

   //Search warehouses by name
   @GetMapping("/search")
   public ResponseEntity<List<Warehouse>> searchByName(@RequestParam String name) {
       List<Warehouse> warehouses = warehouseService.findWarehousesByName(name);
       return ResponseEntity.ok(warehouses);
   }

   //Search warehouse by capacity
   @GetMapping("/capacity")
   public ResponseEntity<List<Warehouse>> searchByCapacity(@RequestParam  BigDecimal capacity) {
        List<Warehouse> warehouses = warehouseService.findWarehousesByCapacity(capacity);
        return ResponseEntity.ok(warehouses);
   }

}
