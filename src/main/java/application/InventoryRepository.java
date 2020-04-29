package application;

/*
 * author			: prateek.sharma
 * creation date	: 28-APR-2020
 * description		: this class acts as JPA repository for the model Inventory and will be used h2 database 
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
