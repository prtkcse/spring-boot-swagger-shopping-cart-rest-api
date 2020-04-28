/*
 * This file is used by Spring Boot Container and will be executed during the 
 * application startup so that we have some data in the product and inventory
 * tables in h2 database in the beginning.
 * 
 * author	: prateek.sharma
 * date		: 28-APR-2020		
 */


/*
 * The following insert statements will insert products into the
 * table product.
 */
insert into product(product_code,brand_name,description,category,price)
values('LG_WM_01','LG','LG Washing Machine 01','Washing Machine',10000);

insert into product(product_code,brand_name,description,category,price)
values('SONY_TV_01','SONY','SONY Bravia TV 01','Television',12000);

insert into product(product_code,brand_name,description,category,price)
values('KENT_RO_01','KENT','KENT RO Purifier 01','Water Purifier',6000);

insert into product(product_code,brand_name,description,category,price)
values('VC_FRIDGE_01','VIDEOCON','VIDEOCON Fridge 01','Fridge',15000);

insert into product(product_code,brand_name,description,category,price)
values('DELL_LP_01','DELL','DELL Laptop 01','Laptop',30000);

insert into product(product_code,brand_name,description,category,price)
values('LG_TV_01','LG','LG TV 01','Television',15000);

insert into product(product_code,brand_name,description,category,price)
values('SONY_LP_01','SONY','SONY Laptop 01','Laptop',25000);

insert into product(product_code,brand_name,description,category,price)
values('KENT_AQ_01','KENT','KENT Aquaguard 01','Water Purifier',8000);

insert into product(product_code,brand_name,description,category,price)
values('VC_MOBILE_01','VIDEOCON','VIDEOCON Mobile 01','Mobile',13500);

insert into product(product_code,brand_name,description,category,price)
values('DELL_LP_02','DELL','DELL Laptop 02','Laptop',35000);

insert into product(product_code,brand_name,description,category,price)
values('SS_MOBILE_01','SAMSUNG','SAMSUNG Mobile 01','Mobile',15000);

insert into product(product_code,brand_name,description,category,price)
values('SS_MOBILE_02','SAMSUNG','SAMSUNG Mobile 02','Mobile',17000);

insert into product(product_code,brand_name,description,category,price)
values('SS_MOBILE_03','SAMSUNG','SAMSUNG Mobile 03','Mobile',19000);

insert into product(product_code,brand_name,description,category,price)
values('VIVO_MOBILE_01','VIVO','VIVO Mobile 01','Mobile',11000);

insert into product(product_code,brand_name,description,category,price)
values('VIVO_MOBILE_02','VIVO','VIVO Mobile 02','Mobile',12000);

insert into product(product_code,brand_name,description,category,price)
values('VC_TV_01','VIDEOCON','VIDEOCON TV 01','Television',20000);



/*
 * The following insert statements will insert entries into the
 * table inventory.
 */
insert into inventory(product_code,available_quantity,last_updated)
values('LG_WM_01',20,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('SONY_TV_01',30,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('KENT_RO_01',10,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('VC_FRIDGE_01',24,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('DELL_LP_01',33,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('LG_TV_01',46,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('SONY_LP_01',45,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('KENT_AQ_01',66,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('VC_MOBILE_01',100,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('DELL_LP_02',44,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('SS_MOBILE_01',77,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('SS_MOBILE_02',56,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('SS_MOBILE_03',16,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('VIVO_MOBILE_01',86,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('VIVO_MOBILE_02',58,CURRENT_TIMESTAMP());

insert into inventory(product_code,available_quantity,last_updated)
values('VC_TV_01',0,CURRENT_TIMESTAMP());
