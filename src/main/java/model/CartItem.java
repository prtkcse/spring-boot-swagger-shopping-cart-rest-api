package model;

/*
 * author			: prateek.sharma
 * creation date	: 28-APR-2020
 * description		: this class models the database table CART_ITEM
 */

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CART_ITEM")
public class CartItem {
	
	@Id
	@Column(name = "serial", nullable = false)
	private Integer serial;
	
	@Column(name = "product", nullable = false)
	private String productCode;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "quantity", nullable = false)
	private Integer quantity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_added", nullable = false)
	private Date dateAdded;	

	public CartItem() {
				
	}

	public CartItem(Integer serial, String productCode, String description, Integer quantity, Date dateAdded) {
		super();
		this.serial = serial;
		this.productCode = productCode;
		this.description = description;
		this.quantity = quantity;
		this.dateAdded = dateAdded;
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer serial) {
		this.serial = serial;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}	
	
	
}
