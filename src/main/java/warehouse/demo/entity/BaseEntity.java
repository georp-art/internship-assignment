package warehouse.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

/*
*BaseEntity serves as a superclass for all entities in the system,
* providing common fields such as id,createdAt,updatedAt
 */
@Getter
@MappedSuperclass //Indicates that this class is a base class for JPA entities and should not be mapped to a table itself
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt; // Timestamp when the entity is created
    private LocalDateTime updatedAt;// Timestamp when the entity is last updated

    //Automatically sets createdAt timestamp before persisting to the database
    @PrePersist
    protected void onCreate() {
     createdAt = LocalDateTime.now();
    }

    //Automatically updates updatedAt timestamp before updating the entity in the database.
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    //Getter for id
     public Long getId() {
        return id;
     }

 //Getter for createdAt
 public LocalDateTime getCreatedAt() {
        return createdAt;
 }

 //Getter for updatedAt
 public LocalDateTime getUpdatedAt() {
        return updatedAt;
 }
}
