package warehouse.demo.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import warehouse.demo.entity.Warehouse;

/*
* Repository interface for warehouse entity
* Provides methods to query warehouses by name,capacity and manager
 */
@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {

    //Search warehouses by partial name match(case insensitive)
    List<Warehouse> findByNameContainingIgnoreCase(String name);

    //Custom query to find warehouses that have manager assigned
    @Query("SELECT w FROM Warehouse w WHERE w.managerName IS NOT NULL")
    List<Warehouse> findWarehousesWithManager();

    //Find warehouse by exact name
    List<Warehouse> findByName(String name);

    //Find warehouse with a specific capacity
    List<Warehouse> findByCapacity(BigDecimal capacity);
}