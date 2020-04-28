package shoppingcarttests;

/*
 * author			: prateek.sharma
 * creation date	: 28-APR-2020
 * description		: this class is the JUNIT test class for executing unit test cases
 */

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.ShoppingCartApplication;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=application.ShoppingCartApplication.class)
public class ShoppingCartTests {
	
	private static final String BASE_URL = "http://localhost:8080/shoppingcart/api/v1";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";
	private static final String PRODUCT_CODE = "productCode";
	private static final String BRAND_NAME = "brandName";
	private static final String CATEGORY = "category";
	private static final String PRICE = "price";
	private static final String DESCRIPTION = "description";
	private static final String AVAILABLE_QUANTITY = "availableQuantity";
	private static final String LAST_UPDATED = "lastUpdated";
	
	@Test
	public void testGetAllProducts(){
		get(BASE_URL+"/products")
		.then()
		.assertThat()
		.statusCode(200);		
	}
	
	
	@Test
	public void testGetAllProductsCount(){
		get(BASE_URL+"/products/count")
		.then()
		.assertThat()
		.statusCode(200);			
	}
	
	
	@Test
	public void testGetProductByProductCode(){
		RestAssured.baseURI = BASE_URL;
	    RequestSpecification httpRequest = RestAssured.given();
	    httpRequest.header(CONTENT_TYPE, APPLICATION_JSON);
	    Response response = httpRequest.get("/products/productCode?value=SS_MOBILE_02");	    
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    String productCode = jsonPathEvaluator.get(PRODUCT_CODE);
	   	Assert.assertTrue(productCode.equalsIgnoreCase("SS_MOBILE_02"));		
	}
	
	
	@Test
	public void testGetAllProductsByBrand(){
		RestAssured.baseURI = BASE_URL;
	    RequestSpecification httpRequest = RestAssured.given();
	    httpRequest.header(CONTENT_TYPE, APPLICATION_JSON);
	    Response response = httpRequest.get("/products/brand?value=LG");	    
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    List<String> brands = jsonPathEvaluator.get(BRAND_NAME);
	    for(String brand : brands)
	    	Assert.assertTrue(brand.equalsIgnoreCase("LG"));	    
	}
	
	
	@Test
	public void testGetAllProductsCountByBrand(){
		get(BASE_URL+"/products/count/brand?value=samsung")
		.then()
		.assertThat()
		.statusCode(200);		
	}
	
	
	@Test
	public void testGetAllProductsByCategory(){
		RestAssured.baseURI = BASE_URL;
	    RequestSpecification httpRequest = RestAssured.given();
	    httpRequest.header(CONTENT_TYPE, APPLICATION_JSON);
	    Response response = httpRequest.get("/products/category?value=television");	    
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    List<String> categoryList = jsonPathEvaluator.get(CATEGORY);
	    for(String category : categoryList)
	    	Assert.assertTrue(category.equalsIgnoreCase("television"));		
	}
	
	
	@Test
	public void testGetAllProductsCountByCategory(){
		get(BASE_URL+"/products/count/category?value=mobile")
		.then()
		.assertThat()
		.statusCode(200);		
	}
	
	
	@Test
	public void testGetAllProductsByCategoryAndPriceRange(){
		RestAssured.baseURI = BASE_URL;
	    RequestSpecification httpRequest = RestAssured.given();
	    httpRequest.header(CONTENT_TYPE, APPLICATION_JSON);
	    Response response = httpRequest.get("/products/category/range?category=mobile&low=5000&high=15000");	    
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    List<String> productList = jsonPathEvaluator.get(PRODUCT_CODE);
	    List<Float> priceList = jsonPathEvaluator.get(PRICE);
	    if(productList!=null && !productList.isEmpty()){
	    	for(Float price : priceList){
	    		Assert.assertTrue(price>=5000);
	    		Assert.assertTrue(price<=15000);
	    	}
	    }
	}
	
	
	@Test
	public void testGetAllProductsByText(){
		RestAssured.baseURI = BASE_URL;
	    RequestSpecification httpRequest = RestAssured.given();
	    httpRequest.header(CONTENT_TYPE, APPLICATION_JSON);
	    Response response = httpRequest.get("/products/text?value=mobile");	    
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    List<String> descriptionList = jsonPathEvaluator.get(DESCRIPTION);
	    if(descriptionList!=null && !descriptionList.isEmpty())
	    	for(String description : descriptionList)
	    		Assert.assertTrue(description.toUpperCase().contains(description.toUpperCase()));		
	}
	
	
	@Test
	public void testGetAllInventory(){
		get(BASE_URL+"/inventory")
		.then()
		.assertThat()
		.statusCode(200);		
	}
	
	
	@Test
	public void testGetInventoryByProductCode(){
		RestAssured.baseURI = BASE_URL;
	    RequestSpecification httpRequest = RestAssured.given();
	    httpRequest.header(CONTENT_TYPE, APPLICATION_JSON);
	    Response response = httpRequest.get("/inventory/productcode?value=VC_MOBILE_01");	    
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    String productCode = jsonPathEvaluator.get(PRODUCT_CODE);
	    Integer availableQuantity = jsonPathEvaluator.get(AVAILABLE_QUANTITY);
	    Assert.assertTrue(productCode.equals("VC_MOBILE_01"));
	    Assert.assertTrue(availableQuantity>=0);		
	}
	
	
	@Test
	public void testGetOutOfStockProducts(){
		RestAssured.baseURI = BASE_URL;
	    RequestSpecification httpRequest = RestAssured.given();
	    httpRequest.header(CONTENT_TYPE, APPLICATION_JSON);
	    Response response = httpRequest.get("/inventory/outofstock");	    
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    List<String> productCodeList = jsonPathEvaluator.get(PRODUCT_CODE);
	    List<Integer> availableQuantityList = jsonPathEvaluator.get(AVAILABLE_QUANTITY);
	    if(productCodeList!=null && !productCodeList.isEmpty())
	    	for(Integer availableQuantity : availableQuantityList)
	    		Assert.assertTrue(availableQuantity==0);		
	}
	
	
	@Test
	public void testGetThresholdProducts(){
		RestAssured.baseURI = BASE_URL;
	    RequestSpecification httpRequest = RestAssured.given();
	    httpRequest.header(CONTENT_TYPE, APPLICATION_JSON);
	    Response response = httpRequest.get("/inventory/threshold?value=44");	    
	    JsonPath jsonPathEvaluator = response.jsonPath();
	    List<String> productCodeList = jsonPathEvaluator.get(PRODUCT_CODE);
	    List<Integer> availableQuantityList = jsonPathEvaluator.get(AVAILABLE_QUANTITY);
	    if(productCodeList!=null && !productCodeList.isEmpty())
	    	for(Integer availableQuantity : availableQuantityList)
	    		Assert.assertTrue(availableQuantity<=44);		
	}
	
	
	@Test
	public void testCreateProduct(){
		Map<String,Object> product = new HashMap<>();
		product.put(PRODUCT_CODE, "OPPO_MOBILE_01");
		product.put(BRAND_NAME, "OPPO");
		product.put(DESCRIPTION, "OPPO Mobile 01");
		product.put(CATEGORY, "MOBILE");
		product.put(PRICE, 23000);
		
		given()
		.contentType(APPLICATION_JSON)
		.body(product)
		.when().post("/products/create").then()
		.statusCode(200);		
	}
	
	
	@Test
	public void testCreateInventory(){
		Map<String,Object> inventory = new HashMap<>();
		inventory.put(PRODUCT_CODE, "OPPO_MOBILE_01");
		inventory.put(AVAILABLE_QUANTITY, 102);
		inventory.put(LAST_UPDATED, new Date());
		
		given()
		.contentType(APPLICATION_JSON)
		.body(inventory)
		.when().post("/inventory/create").then()
		.statusCode(200);		
	}
	
	
	@Test
	public void testUpdateProduct(){
		Map<String,Object> product = new HashMap<>();
		product.put(PRODUCT_CODE, "VIVO_MOBILE_02");
		product.put(BRAND_NAME, "VIVO");
		product.put(DESCRIPTION, "VIVO Mobile 02");
		product.put(CATEGORY, "Mobile");
		product.put(PRICE, 18000);	
		
		given()
		.contentType(APPLICATION_JSON)
		.body(product)
		.when().put("/products/update/VIVO_MOBILE_02").then()
		.statusCode(200);		
	}
	
	
	@Test
	public void testUpdateInventory(){
		Map<String,Object> inventory = new HashMap<>();
		inventory.put(PRODUCT_CODE, "VIVO_MOBILE_02");
		inventory.put(AVAILABLE_QUANTITY, "79");
		inventory.put(LAST_UPDATED, new Date());
		
		given()
		.contentType(APPLICATION_JSON)
		.body(inventory)
		.when().put("/inventory/update/VIVO_MOBILE_02").then()
		.statusCode(200);			
	}
	
	
	@Test
	public void testDeleteProduct(){				
		when().delete("/products/delete?value=OPPO_MOBILE_01").then()
		.statusCode(200);		
	}
	
	
	@Test
	public void testDeleteInventoryItem(){				
		when().delete("/inventory/delete?value=OPPO_MOBILE_01").then()
		.statusCode(200);		
	}
	
	
	@Test
	public void testAddToCart(){		
		when().post("/cart/add?productCode=VIVO_MOBILE_02&quantity=1").then()
		.statusCode(200);
		
		when().post("/cart/add?productCode=VC_MOBILE_01&quantity=4").then()
		.statusCode(200);
	}
	
	
	@Test
	public void testGetCart(){
		get(BASE_URL+"/cart")
		.then()
		.assertThat()
		.statusCode(200);		
	}
	
	
	@Test
	public void testGetTotalFare(){
		get(BASE_URL+"/cart/totalfare")
		.then()
		.assertThat()
		.statusCode(200);		
	}

}
