package application;

/*
 * author			: prateek.sharma
 * creation date	: 28-APR-2020
 * description		: this class acts as JPA repository for the model CartItem
 */

import org.springframework.data.jpa.repository.JpaRepository;

import model.CartItem;;

public interface ShoppingCartRepository extends JpaRepository<CartItem, Long> {

}
