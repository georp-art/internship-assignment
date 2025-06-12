package warehouse.demo.service;

import org.springframework.stereotype.Service;
import warehouse.demo.entity.Inventory;
import warehouse.demo.entity.Product;
import warehouse.demo.entity.Warehouse;
import warehouse.demo.repository.InventoryRepository;

import java.util.List;
import java.util.Optional;

// Implementation of InventoryService using InventoryRepository.
@Service
public class InventoryServiceImpl implements InventoryService {
    private InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
     this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Optional<Inventory> getInventoryById(int id) {
        return inventoryRepository.findById((long)id);
    }

    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Override
    public Inventory updateInventory(Long id,Inventory inventory){
        return inventoryRepository.findById(id)
        .map( existing ->{
            existing.setProduct(inventory.getProduct());
            existing.setWarehouse(inventory.getWarehouse());
            existing.setQuantity(inventory.getQuantity());
            existing.setMinimumStock(inventory.getMinimumStock());
            existing.setMaximumStock(inventory.getMaximumStock());

            return inventoryRepository.save(existing);
        })
                .orElseThrow(() -> new RuntimeException("Inventory not found: " + id));
    }

    @Override
    public void deleteInventoryById(Long id) {
        inventoryRepository.deleteById(id);
    }

    @Override
    public List<Inventory> getInventoryByWarehouse(Warehouse warehouse) {
        return inventoryRepository.findByWarehouse(warehouse);
    }

    @Override
    public List<Inventory> getInventoryByProduct(Product product) {
        return inventoryRepository.findByProduct(product);
    }

    @Override
    public List<Inventory> getLowStockInventory(){
        return inventoryRepository.findLowStockItems();
    }

    @Override
    public List<Inventory> getInventoryByProductAndWarehouse(Product product, Warehouse warehouse){
        return inventoryRepository.findByWarehouseAndProduct(product,warehouse);
    }

}
