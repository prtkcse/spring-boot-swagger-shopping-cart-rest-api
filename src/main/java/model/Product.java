package model;

/*
 * author			: prateek.sharma
 * creation date	: 28-APR-2020
 * description		: this class models the database table PRODUCT 
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
	
	@Id	
	@Column(name = "product_code", nullable = false)
	private String productCode; 
	
	@Column(name = "brand_name", nullable = false)
	private String brandName;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "category", nullable = false)
	private String category;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	public Product(){
		
	}	

	public Product(String productCode, String brandName, String description, String category, double price) {
		super();		
		this.productCode = productCode;
		this.brandName = brandName;
		this.description = description;
		this.category = category;
		this.price = price;
	}

	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
