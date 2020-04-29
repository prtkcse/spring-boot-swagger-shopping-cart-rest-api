package model;

/*
 * author			: prateek.sharma
 * creation date	: 28-APR-2020
 * description		: this class models the database table INVENTORY
 */

import java.util.Date;

/*
 * author			: prateek.sharma
 * creation date	: 28-APR-2020
 * description		: this class models the database table inventory 
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "inventory")
public class Inventory {
	
	@Id
	@Column(name = "product_code", nullable = false)
	private String productCode;
	
	@Column(name = "available_quantity", nullable = false)
	private int availableQuantity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated", nullable = false)
    private Date lastUpdated;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
}
