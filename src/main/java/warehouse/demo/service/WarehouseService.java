package warehouse.demo.service;

import warehouse.demo.entity.Warehouse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

// Interface for Warehouse CRUD operations.
public interface WarehouseService {
    //Get all warehouses
    List<Warehouse> getAllWarehouses();

    //Find warehouse by id
    Optional<Warehouse> getWarehouseById(long id);

    //Create a new warehouse
    Warehouse createWarehouse(Warehouse warehouse);

    //Update a warehouse by id
    Warehouse updateWarehouse(Long id,Warehouse warehouse);

    //Delete a warehouse by id
    void deleteWarehouse(long id);

    //Find warehouse by name
    List<Warehouse> findWarehousesByName(String name);

    //Find warehouse by capacity
    List<Warehouse> findWarehousesByCapacity(BigDecimal capacity);
}
