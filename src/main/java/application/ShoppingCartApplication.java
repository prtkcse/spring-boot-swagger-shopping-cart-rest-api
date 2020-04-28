package application;

/*
 * author			: prateek.sharma
 * creation date	: 28-APR-2020
 * description		: this class is the entry point of the application containing the main()
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan({"controller"})
@SpringBootApplication
@EntityScan(basePackages = {"model"})
public class ShoppingCartApplication {
	
	public static void main(String[] args){
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

}
