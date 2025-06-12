package warehouse.demo.service;

import org.springframework.stereotype.Service;
import warehouse.demo.entity.Warehouse;
import warehouse.demo.repository.WarehouseRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// Implementation of WarehouseService using WarehouseRepository.
@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public Optional <Warehouse> getWarehouseById(long id) {
        return warehouseRepository.findById(id);
    }

    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {

        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse updateWarehouse(Long id,Warehouse warehouse) {
        return warehouseRepository.findById(id)
                .map(existing ->{
                 existing.setName(warehouse.getName());
                 existing.setAddress(warehouse.getAddress());
                 existing.setCapacity(warehouse.getCapacity());
                 existing.setManagerName(warehouse.getManagerName());
                 return warehouseRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Warehouse not found" +id));
    }

    @Override
    public void deleteWarehouse(long id) {
        warehouseRepository.deleteById(id);
    }

    @Override
    public List<Warehouse> findWarehousesByCapacity(BigDecimal capacity) {
        return warehouseRepository.findByCapacity(capacity);
    }

    @Override
    public List<Warehouse> findWarehousesByName(String name){
        //System.out.println(name);
        return warehouseRepository.findByName(name);
    }
}
