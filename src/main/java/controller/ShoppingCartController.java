package controller;

/*
 * author			: prateek.sharma
 * creation date	: 28-APR-2020
 * description		: this class acts as the REST Controller providing all the mappings
 */

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.CartItem;
import model.Inventory;
import model.Product;

import application.Counter;
import application.InventoryRepository;
import application.ProductRepository;
import application.ResourceNotFoundException;
import application.ShoppingCartRepository;


@RestController
@RequestMapping("/api/v1")
public class ShoppingCartController {
	
	private static final String DELETED = "Deleted";
	private static final String RESOURCE_NOT_FOUND = "Resource not found";
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	
	/*
	 * Method : getAllProducts()
	 * This method returns all products in the PRODUCT table
	 */
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productRepository.findAll();		
	}
	
	
	/*
	 * Method : getAllProductsCount()
	 * This method returns the count of all products in PRODUCT table
	 */
	@GetMapping("/products/count")
	public long getAllProductsCount(){
		return productRepository.count();	
	}
	
	
	/*
	 * Method : getProductByProductCode()
	 * This method returns PRODUCT based on the ProductCode
	 */
	@GetMapping("/products/productCode")
	public Product getProductByProductCode(@RequestParam(value="value") String productCode){
		List<Product> allProductsList = productRepository.findAll();
		Product product;
		try{
			product = allProductsList.stream()
					.filter(item -> item.getProductCode().equalsIgnoreCase(productCode))
					.collect(Collectors.toList()).get(0);
		}catch(ResourceNotFoundException | IndexOutOfBoundsException e){
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
		}
		
		return product;		
	}
	
	
	/*
	 * Method : getAllProductsByBrand()
	 * This method returns all products of a particular brand
	 */
	@GetMapping("/products/brand")
	public List<Product> getAllProductsByBrand(@RequestParam(value="value") String brand){
		List<Product> allProductsList = productRepository.findAll();		
		
		return allProductsList.stream()
				.filter(product -> product.getBrandName().equalsIgnoreCase(brand))
				.collect(Collectors.toList());
	}
	
	
	/*
	 * Method : getAllProductsCountByBrand()
	 * This method returns the count of all products of a particular brand
	 */
	@GetMapping("/products/count/brand")
	public long getAllProductsCountByBrand(@RequestParam(value="value") String brand){
		List<Product> allProductsList = productRepository.findAll();		
		
		return allProductsList.stream()
				.filter(product -> product.getBrandName().equalsIgnoreCase(brand))
				.count();
	}
	
	
	/*
	 * Method : getAllProductsByCategory()
	 * This method returns all products of a particular category
	 */
	@GetMapping("/products/category")
	public List<Product> getAllProductsByCategory(@RequestParam(value="value") String category){
		List<Product> allProductsList = productRepository.findAll();		
		
		return allProductsList.stream()
				.filter(product -> product.getCategory().equalsIgnoreCase(category))
				.collect(Collectors.toList());
	}
	
	
	/*
	 * Method : getAllProductsCountByCategory()
	 * This method returns the count of products of a particular category
	 */
	@GetMapping("/products/count/category")
	public long getAllProductsCountByCategory(@RequestParam(value="value") String category){
		List<Product> allProductsList = productRepository.findAll();		
		
		return allProductsList.stream()
				.filter(product -> product.getCategory().equalsIgnoreCase(category))
				.count();
	}
	
	
	
	/*
	 * Method : getAllProductsByCategoryAndPriceRange()
	 * This method provides the feature to search a product of particular category
	 * and within a price range from 'low' to 'high'
	 */	
	@GetMapping("/products/category/range")
	public List<Product> getAllProductsByCategoryAndPriceRange(@RequestParam(value="category") String category,
															   @RequestParam(value="low") double low,
															   @RequestParam(value="high") double high){
		
		List<Product> allProductsList = productRepository.findAll();
		
		return allProductsList.stream()
				.filter(product -> product.getCategory().equalsIgnoreCase(category) &&
						product.getPrice()>=low && product.getPrice()<=high)
				.collect(Collectors.toList());
		
	}
	
	
	/*
	 * Method : getAllProductsByText()
	 * This method searches for the products containing the parameter 'text'
	 */
	@GetMapping("/products/text")
	public List<Product> getAllProductsByText(@RequestParam(value="value") String text){
		
		List<Product> allProductsList = productRepository.findAll();
		
		return allProductsList.stream()
				.filter(product -> product.getDescription().toUpperCase().contains(text.toUpperCase()) ||
								   product.getProductCode().toUpperCase().contains(text.toUpperCase()) ||
								   product.getBrandName().toUpperCase().contains(text.toUpperCase())   ||
								   product.getCategory().toUpperCase().contains(text.toUpperCase()))								   
				.collect(Collectors.toList());
		
	}
	
	
	
	/*
	 * Method : getAllInventory()
	 * This method returns all the entries from INVENTORY table
	 */
	@GetMapping("/inventory")
	public List<Inventory> getAllInventory(){
		return inventoryRepository.findAll();		
	}
	
	
	
	/*
	 * Method : getInventoryByProductCode()
	 * This method returns the inventory of product with a specific productCode
	 */
	@GetMapping("/inventory/productcode")
	public Inventory getInventoryByProductCode(@RequestParam(value="value") String productCode){
		List<Inventory> allInventoryList = inventoryRepository.findAll();		
		Inventory inventory;
		try{
			inventory = allInventoryList.stream()
					.filter(item -> item.getProductCode().equalsIgnoreCase(productCode))
					.collect(Collectors.toList()).get(0);
		}catch(ResourceNotFoundException | IndexOutOfBoundsException e){
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
		}
		
		return inventory;
	}
	
	
	
	/*
	 * Method : getOutOfStockProducts()
	 * This method returns the list of products which are out of stock
	 */
	@GetMapping("/inventory/outofstock")
	public List<Inventory> getOutOfStockProducts(){
		List<Inventory> allInventoryList = inventoryRepository.findAll();		
		
		return allInventoryList.stream()
				.filter(inventory -> inventory.getAvailableQuantity()==0)
				.collect(Collectors.toList());
	}
	
	
	
	/*
	 * Method : getThresholdProducts()
	 * This method returns the list of products the availability of which is 
	 * less than the parameter 'threshold'
	 */
	@GetMapping("/inventory/threshold")
	public List<Inventory> getThresholdProducts(@RequestParam(value="value") int threshold){
		List<Inventory> allInventoryList = inventoryRepository.findAll();		
		
		return allInventoryList.stream()
				.filter(inventory -> inventory.getAvailableQuantity()<=threshold)
				.collect(Collectors.toList());
	}
	
	
	
	/*
	 * Method : createProduct()
	 * This method is used to create a new Product in the PRODUCT table
	 */
	@PostMapping("/products/create")
	public Product createProduct(@Valid @RequestBody Product product){
		return productRepository.save(product);
	}
	
	
	/*
	 * Method : createInventory()
	 * This method is used to create a new Inventory in the INVENTORY table
	 */
	@PostMapping("/inventory/create")
	public Inventory createInventory(@Valid @RequestBody Inventory inventory){
		return inventoryRepository.save(inventory);
	}
	
	
	
	/*
	 * Method : updateProduct()
	 * This method is used to update a product in PRODUCT table
	 */
	@PutMapping("/products/update/{code}")
	public ResponseEntity<Product> updateProduct(@PathVariable(value="code") String productCode,
												 @Valid @RequestBody Product productDetails){
		Product product;
		try{
			product = productRepository.findAll().stream().
						  filter(item -> item.getProductCode().equalsIgnoreCase(productCode))
						  .collect(Collectors.toList()).get(0);
		
			product.setDescription(productDetails.getDescription());
			product.setCategory(productDetails.getCategory());
			product.setBrandName(productDetails.getBrandName());
			product.setPrice(productDetails.getPrice());		
		}catch(ResourceNotFoundException | IndexOutOfBoundsException e){
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
		}
		
		final Product updatedProduct = productRepository.save(product);
		
		return ResponseEntity.ok(updatedProduct);		
	}
	
	
	
	/*
	 * Method : updateInventory()
	 * This method is used to update a inventory in INVENTORY table
	 */
	@PutMapping("/inventory/update/{code}")
	public ResponseEntity<Inventory> updateInventory(@PathVariable(value="code") String productCode,
												 @Valid @RequestBody Inventory inventoryDetails){
		Inventory inventory;
		try{
			inventory = inventoryRepository.findAll().stream().
						  filter(item -> item.getProductCode().equalsIgnoreCase(productCode))
						  .collect(Collectors.toList()).get(0);
		
			inventory.setAvailableQuantity(inventoryDetails.getAvailableQuantity());
			inventory.setLastUpdated(new Date());
			
			
		}catch(ResourceNotFoundException | IndexOutOfBoundsException e){
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
		}
		
		final Inventory updatedInventory = inventoryRepository.save(inventory);
		
		return ResponseEntity.ok(updatedInventory);		
	}
	
	
	
	/*
	 * Method : deleteProduct()
	 * This method is used to delete a particular product in PRODUCT table
	 * based on 'productCode'
	 */
	@DeleteMapping("/products/delete")
	public Map<String,Boolean> deleteProduct(@RequestParam(value="value") String productCode){
		
		Map<String,Boolean> response = new HashMap<>();
		Product product;
		try{
			product = productRepository.findAll().stream().
						  filter(item -> item.getProductCode().equalsIgnoreCase(productCode))
						  .collect(Collectors.toList()).get(0);	
			productRepository.delete(product);
			response.put(DELETED, Boolean.TRUE);
		}catch(ResourceNotFoundException | IndexOutOfBoundsException e){
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
		}
		
		if(!response.containsKey(DELETED))
			response.put(DELETED, Boolean.FALSE);
		
		return response;		
	}
	
	
	
	/*
	 * Method : deleteInventoryItem()
	 * This method is used to delete a particular inventory in INVENTORY table
	 * based on 'productCode'
	 */
	@DeleteMapping("/inventory/delete")
	public Map<String,Boolean> deleteInventoryItem(@RequestParam(value="value") String productCode){
		
		Map<String,Boolean> response = new HashMap<>();
		Inventory inventory;
		try{
			inventory = inventoryRepository.findAll().stream().
						  filter(item -> item.getProductCode().equalsIgnoreCase(productCode))
						  .collect(Collectors.toList()).get(0);	
			inventoryRepository.delete(inventory);
			response.put(DELETED, Boolean.TRUE);
		}catch(ResourceNotFoundException | IndexOutOfBoundsException e){
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
		}
		
		if(!response.containsKey(DELETED))
			response.put(DELETED, Boolean.FALSE);
		
		return response;		
	}
	
	
	
	/*
	 * Method : getCart()
	 * This method is used to display the shopping cart with its items
	 */
	@GetMapping("/cart")
	public List<CartItem> getCart() {
		return shoppingCartRepository.findAll();		
	}
	
	
	
	/*
	 * Method : getTotalFare()
	 * This method returns the total fare of the items in the cart
	 */
	@GetMapping("/cart/totalfare")
	public Double getTotalFare(){
		Double total = (double) 0;
		List<CartItem> cartItems = shoppingCartRepository.findAll();
		if(!cartItems.isEmpty()){
			for(CartItem item : cartItems){
				Product product = getProductByProductCode(item.getProductCode());
				total = total + (product.getPrice()*item.getQuantity());
			}			
		}
		return total;
	}
	
	
	
	/*
	 * Method : addToCart()
	 * This method is used to add an item in the shopping cart
	 */
	@PostMapping("/cart/add")
	public CartItem addToCart(@RequestParam(value="productCode") String productCode,
							  @RequestParam(value="quantity") Integer quantity){
		
		Product product = null;
		List<Product> allProducts = productRepository.findAll();
		try{
			product = allProducts.stream()
					.filter(item -> item.getProductCode().equalsIgnoreCase(productCode))
					.collect(Collectors.toList()).get(0);
		}catch(ResourceNotFoundException | IndexOutOfBoundsException e){
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
		}
		
		Inventory inventory = getInventoryByProductCode(productCode);
		Integer availableQty = inventory.getAvailableQuantity();
		if(availableQty==0)
			throw new ResourceNotFoundException("Item is out of stock");
		else if(availableQty-quantity<0)
			throw new ResourceNotFoundException("Added quantity is more than available quantity");
		
		Inventory inventoryToUpdate = new Inventory();
		inventoryToUpdate.setProductCode(productCode);
		inventoryToUpdate.setAvailableQuantity(availableQty-quantity);
		inventoryToUpdate.setLastUpdated(new Date());		
		updateInventory(productCode,inventoryToUpdate);
		
		CartItem cartItem = new CartItem(Counter.getCounterAndIncrement(), productCode, product.getDescription(), quantity, new Date());
		
		return shoppingCartRepository.save(cartItem);
	}
	
	
	
	/*
	 * Method : updateCart()
	 * This method is used to update an item in shopping cart
	 */
	@PutMapping("/cart/update")
	public ResponseEntity<CartItem> updateCart(@RequestParam(value="productCode") String productCode,
			  								   @RequestParam(value="quantity") Integer quantity){
		List<CartItem> cart = getCart();
		CartItem cartItem = null;
		try{
			cartItem = cart.stream()
					.filter(item -> item.getProductCode().equalsIgnoreCase(productCode))
					.collect(Collectors.toList()).get(0);
		}catch(ResourceNotFoundException | IndexOutOfBoundsException e){
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
		}
		
		Integer prevQty = cartItem.getQuantity();
		
		Inventory inventory = getInventoryByProductCode(productCode);
		Integer availableQty = inventory.getAvailableQuantity();
		
		if(prevQty-quantity+availableQty<0)
			throw new ResourceNotFoundException("Not enough stock is available");
		
		Inventory inventoryToUpdate = new Inventory();
		inventoryToUpdate.setProductCode(productCode);
		inventoryToUpdate.setAvailableQuantity(prevQty-quantity+availableQty);
		inventoryToUpdate.setLastUpdated(new Date());		
		updateInventory(productCode,inventoryToUpdate);
		
		cartItem.setQuantity(quantity);
		cartItem.setDateAdded(new Date());		
		
		final CartItem updatedCartItem = shoppingCartRepository.save(cartItem);
		
		return ResponseEntity.ok(updatedCartItem);		
	}
	
	
	/*
	 * Method : deleteCartItem()
	 * This method is used to delete an item from the cart
	 */
	@DeleteMapping("/cart/delete")
	public Map<String,Boolean> deleteCartItem(@RequestParam(value="value") String productCode){
		
		Map<String,Boolean> response = new HashMap<>();		
		
		List<CartItem> cart = getCart();
		CartItem cartItem = null;
		try{
			cartItem = cart.stream()
					.filter(item -> item.getProductCode().equalsIgnoreCase(productCode))
					.collect(Collectors.toList()).get(0);
		}catch(ResourceNotFoundException | IndexOutOfBoundsException e){
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
		}
		
		Inventory inventory = getInventoryByProductCode(productCode);
		
		Inventory inventoryToUpdate = new Inventory();
		inventoryToUpdate.setProductCode(productCode);
		inventoryToUpdate.setAvailableQuantity(inventory.getAvailableQuantity()+cartItem.getQuantity());
		inventoryToUpdate.setLastUpdated(new Date());		
		updateInventory(productCode,inventoryToUpdate);		
		
		try{			
			shoppingCartRepository.delete(cartItem);
			response.put(DELETED, Boolean.TRUE);
		}catch(ResourceNotFoundException ex){
			throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
		}
		
		if(!response.containsKey(DELETED))
			response.put(DELETED, Boolean.FALSE);
		
		return response;		
	}
	
	
	
	/*
	 * Method : deleteCartItem()
	 * This method is used to clear the shopping cart
	 */
	@DeleteMapping("/cart/deleteAll")
	public Map<String,Boolean> deleteAllCartItems(){
		Map<String,Boolean> allResponse = new HashMap<>();
		allResponse.put(DELETED, Boolean.TRUE);
		List<CartItem> cart = getCart();
		if(cart!=null && !cart.isEmpty()){
			for(CartItem item : cart){
				Map<String,Boolean> response = deleteCartItem(item.getProductCode());
				Boolean newResponse = allResponse.get(DELETED) && response.get(DELETED);
				allResponse.put(DELETED, newResponse);
			}			
		}
		
		return allResponse;		
	}
	
}
