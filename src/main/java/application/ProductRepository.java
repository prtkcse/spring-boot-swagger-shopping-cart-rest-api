package application;

/*
 * author			: prateek.sharma
 * creation date	: 28-APR-2020
 * description		: this class acts as JPA repository for the model Product and will be used h2 database 
 */

import org.springframework.data.jpa.repository.JpaRepository;

import model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
