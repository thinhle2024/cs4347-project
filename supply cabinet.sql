-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema supply_cabinet
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS supply_cabinet ;

-- -----------------------------------------------------
-- Schema supply_cabinet
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS supply_cabinet ;
USE supply_cabinet ;

-- -----------------------------------------------------
-- Table Office
-- -----------------------------------------------------
DROP TABLE IF EXISTS Office ;

CREATE TABLE IF NOT EXISTS Office (
  officeNum INT NOT NULL AUTO_INCREMENT,
  officeName VARCHAR(15) NOT NULL,
  address VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (officeNum));


-- -----------------------------------------------------
-- Table MainOffice
-- -----------------------------------------------------
DROP TABLE IF EXISTS MainOffice ;

CREATE TABLE IF NOT EXISTS MainOffice (
  officeNum INT NOT NULL,
  PRIMARY KEY (officeNum),
  CONSTRAINT 
    FOREIGN KEY (officeNum)
    REFERENCES Office (officeNum));


-- -----------------------------------------------------
-- Table Employee
-- -----------------------------------------------------
DROP TABLE IF EXISTS Employee ;

CREATE TABLE IF NOT EXISTS Employee (
  SSN CHAR(9) NOT NULL,
  officeNum INT NOT NULL,
  firstName VARCHAR(15) NOT NULL,
  middleInitial CHAR NOT NULL,
  lastName VARCHAR(15) NOT NULL,
  PRIMARY KEY (SSN),
  CONSTRAINT 
    FOREIGN KEY (officeNum)
    REFERENCES Office (officeNum));


-- -----------------------------------------------------
-- Table Administrator
-- -----------------------------------------------------
DROP TABLE IF EXISTS Administrator ;

CREATE TABLE IF NOT EXISTS Administrator (
  SSN CHAR(9) NOT NULL,
  PRIMARY KEY (SSN),
  CONSTRAINT 
    FOREIGN KEY (SSN)
    REFERENCES Employee (SSN));


-- -----------------------------------------------------
-- Table Vendor
-- -----------------------------------------------------
DROP TABLE IF EXISTS Vendor ;

CREATE TABLE IF NOT EXISTS Vendor (
  vendorNum INT NOT NULL AUTO_INCREMENT,
  vendorName VARCHAR(15) NOT NULL,
  phoneNum CHAR(10) NULL DEFAULT NULL,
  address VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (vendorNum));


-- -----------------------------------------------------
-- Table Product
-- -----------------------------------------------------
DROP TABLE IF EXISTS Product ;

CREATE TABLE IF NOT EXISTS Product (
  productNum INT NOT NULL AUTO_INCREMENT,
  productName VARCHAR(15) NOT NULL,
  PRIMARY KEY (productNum));


-- -----------------------------------------------------
-- Table SuppliedBy
-- -----------------------------------------------------
DROP TABLE IF EXISTS SuppliedBy ;

CREATE TABLE IF NOT EXISTS SuppliedBy (
  vendorNum INT NOT NULL,
  productNum INT NOT NULL,
  quantityAvailable INT NOT NULL,
  unitPriceAvailable DOUBLE NOT NULL,
  PRIMARY KEY (vendorNum, productNum),
  CONSTRAINT 
    FOREIGN KEY (vendorNum)
    REFERENCES Vendor (vendorNum),
  CONSTRAINT 
    FOREIGN KEY (productNum)
    REFERENCES Product (productNum));


-- -----------------------------------------------------
-- Table OrderPlacement
-- -----------------------------------------------------
DROP TABLE IF EXISTS OrderPlacement ;

CREATE TABLE IF NOT EXISTS OrderPlacement (
  orderNum INT NOT NULL AUTO_INCREMENT,
  SSN CHAR(9) NOT NULL,
  requestDate DATE NOT NULL,
  PRIMARY KEY (orderNum),
  CONSTRAINT fk_orderPlacement_employee1
    FOREIGN KEY (SSN)
    REFERENCES Employee (SSN)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table LineItems
-- -----------------------------------------------------
DROP TABLE IF EXISTS LineItems ;

CREATE TABLE IF NOT EXISTS LineItems (
  orderNum INT NOT NULL,
  vendorNum INT NOT NULL,
  productNum INT NOT NULL,
  quantity INT NOT NULL,
  unitPrice DOUBLE NOT NULL,
  arrivalDate DATE NULL,
  PRIMARY KEY (orderNum, vendorNum, productNum),
  CONSTRAINT fk_table1_orderInfo1
    FOREIGN KEY (orderNum)
    REFERENCES OrderPlacement (orderNum)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_LineItems_product1
    FOREIGN KEY (productNum)
    REFERENCES Product (productNum)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_LineItems_Vendor1
    FOREIGN KEY (vendorNum)
    REFERENCES Vendor (vendorNum)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Shipped
-- -----------------------------------------------------
DROP TABLE IF EXISTS Shipped ;

CREATE TABLE IF NOT EXISTS Shipped (
  id INT NOT NULL AUTO_INCREMENT,
  officeNum INT NOT NULL,
  productNum INT NOT NULL,
  quantityShipped INT NOT NULL,
  shippedDate DATE NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_Shipped_Office1
    FOREIGN KEY (officeNum)
    REFERENCES Office (officeNum)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Shipped_Product1
    FOREIGN KEY (productNum)
    REFERENCES Product (productNum)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table Sold
-- -----------------------------------------------------
DROP TABLE IF EXISTS Sold ;

CREATE TABLE IF NOT EXISTS Sold (
  id INT NOT NULL AUTO_INCREMENT,
  officeNum INT NOT NULL,
  productNum INT NOT NULL,
  quantitySold INT NOT NULL,
  soldDate DATE NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_Sold_Office1
    FOREIGN KEY (officeNum)
    REFERENCES Office (officeNum)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Sold_Product1
    FOREIGN KEY (productNum)
    REFERENCES Product (productNum)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE supply_cabinet ;

-- -----------------------------------------------------
-- procedure generateBranchOfficeWeeklyOrders
-- -----------------------------------------------------

USE supply_cabinet;
DROP procedure IF EXISTS generateBranchOfficeWeeklyOrders;

DELIMITER $$
USE supply_cabinet$$
CREATE DEFINER=root@localhost PROCEDURE generateBranchOfficeWeeklyOrders(
	in officeNum int
)
BEGIN


select
	if (t1.shippedDate is null, 'not yet received', t1.shippedDate) as date,
	t2.productName,
    t1.quantityShipped
from
	(select
		productNum,
		shippedDate,
        quantityShipped
	from
		shipped
	where 
		-- all records linked with a particular branch
		officeNum = officeNum and
        -- products shipped to the branch within the past 7 days, starting today
		shippedDate >= ( date_sub(curdate(), interval 7 day) ) and
        shippedDate < curdate()
	
    union
    
	select
		productNum,
		shippedDate,
        quantityShipped
	from
		shipped
	where 
		-- all records linked with a particular branch
		officeNum = officeNum and
        -- products shipped to the branch within the past 7 days, starting today
		shippedDate is null) as t1
	join
    (select * from product) as t2
    on t1.productNum = t2.productNum
order by t1.shippedDate ASC;
    

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure generateBranchOfficeInstockQuantity
-- -----------------------------------------------------

USE supply_cabinet;
DROP procedure IF EXISTS generateBranchOfficeInstockQuantity;

DELIMITER $$
USE supply_cabinet$$
CREATE DEFINER=root@localhost PROCEDURE generateBranchOfficeInstockQuantity(
	in officeNum int
)
BEGIN
select
	p.productName,
    t3.quantity
from
	(select
		t1.productNum,
		(t1.quantityInstock - t2.quantitySold) as quantity
	from
		(select
			s.productNum,
			-- find total quantity in each group
			sum(s.quantityShipped) as quantityInstock
		from shipped as s
		where
			-- only products shipped to branch #2
			s.officeNum = officeNum and 
            -- only products already shipped from main office
			s.shippedDate is not null
		group by
			-- group products based on product number
			s.productNum) as t1
            
		join

		(select
			ss.productNum,
            -- find total quantity in each group
			sum(ss.quantitySold) as quantitySold
		from
			sold as ss
		where 
			-- only products sold by branch #2
			ss.officeNum = officeNum
		group by
			-- group the products on product number
			ss.productNum) as t2
            
		on t1.productNum = t2.productNum) as t3, product as p
        
where t3.productNum = p.productNum;

    
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure generateBranchOfficeNotYetReceived
-- -----------------------------------------------------

USE supply_cabinet;
DROP procedure IF EXISTS generateBranchOfficeNotYetReceived;

DELIMITER $$
USE supply_cabinet$$
CREATE DEFINER=root@localhost PROCEDURE generateBranchOfficeNotYetReceived(
	in officeNum int
)
BEGIN
	select
		null as date,
        p.productName,
        s.quantityShipped
    from
		shipped as s, product as p
	where
		s.officeNum = officeNum and 
        s.shippedDate is null and 
        s.productNum = p.productNum;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure generateMainOfficeWeeklyOrders
-- -----------------------------------------------------

USE supply_cabinet;
DROP procedure IF EXISTS generateMainOfficeWeeklyOrders;

DELIMITER $$
USE supply_cabinet$$
CREATE DEFINER=root@localhost PROCEDURE generateMainOfficeWeeklyOrders()
BEGIN

SELECT
	op.requestDate,
    li.arrivalDate,
    concat(e.firstName, ' ', e.middleinitial, ' ', e.lastName) as fullName,
    o.officeName,
    v.vendorName,
    p.productName,
    li.quantity as quantity,
    li.unitPrice
FROM	
	OrderPlacement as op, 
	Lineitems as li,
    
    Employee as e, 
    Office as o, 
    
    vendor as v, 
    product as p
WHERE
	-- disregard others that are older than 7 days
	(op.requestDate >= ( date_sub(curdate(), interval 7 day) ) and op.requestDate < curdate() or
    op.requestDate is not null)
    
    and
    
    -- get order detail
	op.orderNum = li.orderNum and 
    
    -- get employee name
	op.SSN = e.SSN and
    
    -- get office name
    e.officeNum = o.officeNum and
    
    -- get vendor name
    li.vendorNum = v.vendorNum and 
    
    -- get product name
    li.productNum = p.productNum
order by
	op.requestDate;

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure generateMainOfficeShippedProducts
-- -----------------------------------------------------

USE supply_cabinet;
DROP procedure IF EXISTS generateMainOfficeShippedProducts;

DELIMITER $$
USE supply_cabinet$$
CREATE DEFINER=root@localhost PROCEDURE generateMainOfficeShippedProducts(
)
BEGIN
select  
	s.shippedDate, p.productName, s.quantityShipped, o.officeName
from 
	shipped as s, product as p, office as o
where 
    s.shippedDate >= ( date_sub(curdate(), interval 7 day) ) and s.shippedDate < curdate() and
    s.productNum = p.productNum and 
    s.officeNum = o.officeNum
order by s.shippedDate, s.officeNum ASC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure generateMainOfficeInstockProducts
-- -----------------------------------------------------

USE supply_cabinet;
DROP procedure IF EXISTS generateMainOfficeInstockProducts;

DELIMITER $$
USE supply_cabinet$$
CREATE DEFINER=root@localhost PROCEDURE generateMainOfficeInstockProducts()
BEGIN
select
	null as data,
	t4.productName,
    t3.quantityRemaining
from
	(select 
		t1.productNum,
		(t1.quantityInstock - t2.quantityShipped) as quantityRemaining
	from
		(select
			productNum,
			-- find the total quantity of products in each group
			sum(quantity) as quantityInstock
		from
			lineitems
		where 
			-- find all the products that the main office has received from vendors
			arrivalDate is not null
		group by
			-- group the products based on the product number
			productNum) as t1
		
		join
		
		(select 
			productNum,
			-- find the total quantity of products in each group
			sum(quantityShipped) as quantityShipped
		from
			shipped
		where 
			-- only count the products having already been shipped to a branch 
			shippedDate is not null
		group by
			-- use the product number to group the product
			productNum) as t2
			
		on t1.productNum = t2.productNum) t3

	join

	(select * from product) as t4
    
    on t3.productNum = t4.productNum;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure findAvailableVendors
-- -----------------------------------------------------

USE supply_cabinet;
DROP procedure IF EXISTS findAvailableVendors;

DELIMITER $$
USE supply_cabinet$$
CREATE DEFINER=root@localhost PROCEDURE findAvailableVendors(
	in productNum int
)
BEGIN
	select 
		s.vendorNum,
        s.productNum, 
        s.quantityAvailable,
        s.unitPriceAvailable,
        v.vendorName,
        p.productName
    from suppliedby as s, vendor as v, product as p
    where 
		s.quantityAvailable > 0 and 
        s.productNum = productNum and
		s.vendorNum = v.vendorNum and 
        s.productNum = p.productNum
	order by s.unitPriceAvailable;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure findUnApprovedOrders
-- -----------------------------------------------------

USE supply_cabinet;
DROP procedure IF EXISTS findUnApprovedOrders;

DELIMITER $$
USE supply_cabinet$$
CREATE DEFINER=root@localhost PROCEDURE findUnApprovedOrders()
BEGIN

	select
		o.officeName,
        p.productName,
        s.quantityShipped as quantityNeeded,
        s.officeNum,
        s.productNum
    from
		shipped as s, office as o, product as p
    where 
		s.shippedDate is null and
        s.officeNum = o.officeNum and 
        s.productNum = p.productNum;

END$$

DELIMITER ;
USE supply_cabinet;

DELIMITER $$

USE supply_cabinet$$
DROP TRIGGER IF EXISTS updateSuppliedByQuantityAvailable $$
USE supply_cabinet$$
CREATE DEFINER = CURRENT_USER TRIGGER supply_cabinet.updateSuppliedByQuantityAvailable AFTER INSERT ON LineItems FOR EACH ROW
BEGIN
	update suppliedby
    set quantityAvailable = quantityAvailable - NEW.quantity
    where vendorNum = NEW.vendorNum and productNum = NEW.productNum;
END$$


DELIMITER ;

-- -----------------------------------------------------
-- Data for table Office
-- -----------------------------------------------------
START TRANSACTION;
USE supply_cabinet;
INSERT INTO Office (officeNum, officeName, address) VALUES (1, 'Office 1 ', 'address 1, city, state zipcode');
INSERT INTO Office (officeNum, officeName, address) VALUES (2, 'Office 2', 'address 2, city, state zipcode');
INSERT INTO Office (officeNum, officeName, address) VALUES (3, 'Office 3', 'address 3, city, state zipcode');

COMMIT;


-- -----------------------------------------------------
-- Data for table MainOffice
-- -----------------------------------------------------
START TRANSACTION;
USE supply_cabinet;
INSERT INTO MainOffice (officeNum) VALUES (1);

COMMIT;


-- -----------------------------------------------------
-- Data for table Employee
-- -----------------------------------------------------
START TRANSACTION;
USE supply_cabinet;
INSERT INTO Employee (SSN, officeNum, firstName, middleInitial, lastName) VALUES ('100000000', 1, 'admin', 'a', 'admin');
INSERT INTO Employee (SSN, officeNum, firstName, middleInitial, lastName) VALUES ('200000000', 1, 'Employee 1', 'A', 'Employee 1');
INSERT INTO Employee (SSN, officeNum, firstName, middleInitial, lastName) VALUES ('300000000', 2, 'Employee 2', 'B', 'Employee 2');
INSERT INTO Employee (SSN, officeNum, firstName, middleInitial, lastName) VALUES ('400000000', 3, 'Employee 3', 'C', 'Employee 3');

COMMIT;


-- -----------------------------------------------------
-- Data for table Administrator
-- -----------------------------------------------------
START TRANSACTION;
USE supply_cabinet;
INSERT INTO Administrator (SSN) VALUES ('100000000');

COMMIT;


-- -----------------------------------------------------
-- Data for table Vendor
-- -----------------------------------------------------
START TRANSACTION;
USE supply_cabinet;
INSERT INTO Vendor (vendorNum, vendorName, phoneNum, address) VALUES (1, 'VENDOR 1', '123456789', 'ADDRESS 1, CITY, STATE ZIPCODE');
INSERT INTO Vendor (vendorNum, vendorName, phoneNum, address) VALUES (2, 'VENDOR 2', '987654321', 'ADDRESS 2, CITY, STATE ZIPCODE');

COMMIT;


-- -----------------------------------------------------
-- Data for table Product
-- -----------------------------------------------------
START TRANSACTION;
USE supply_cabinet;
INSERT INTO Product (productNum, productName) VALUES (1, 'PRODUCT 1');
INSERT INTO Product (productNum, productName) VALUES (2, 'PRODUCT 2');
INSERT INTO Product (productNum, productName) VALUES (3, 'PRODUCT 3');

COMMIT;


-- -----------------------------------------------------
-- Data for table SuppliedBy
-- -----------------------------------------------------
START TRANSACTION;
USE supply_cabinet;
INSERT INTO SuppliedBy (vendorNum, productNum, quantityAvailable, unitPriceAvailable) VALUES (1, 1, 200, 10);
INSERT INTO SuppliedBy (vendorNum, productNum, quantityAvailable, unitPriceAvailable) VALUES (1, 2, 200, 10);
INSERT INTO SuppliedBy (vendorNum, productNum, quantityAvailable, unitPriceAvailable) VALUES (1, 3, 200, 10);
INSERT INTO SuppliedBy (vendorNum, productNum, quantityAvailable, unitPriceAvailable) VALUES (2, 1, 250, 20);
INSERT INTO SuppliedBy (vendorNum, productNum, quantityAvailable, unitPriceAvailable) VALUES (2, 2, 250, 20);
INSERT INTO SuppliedBy (vendorNum, productNum, quantityAvailable, unitPriceAvailable) VALUES (2, 3, 250, 20);

COMMIT;


-- -----------------------------------------------------
-- Data for table OrderPlacement
-- -----------------------------------------------------
START TRANSACTION;
USE supply_cabinet;
INSERT INTO OrderPlacement (orderNum, SSN, requestDate) VALUES (1, '300000000', '2021-11-25');
INSERT INTO OrderPlacement (orderNum, SSN, requestDate) VALUES (2, '400000000', '2021-11-25');
INSERT INTO OrderPlacement (orderNum, SSN, requestDate) VALUES (3, '300000000', '2021-11-26');
INSERT INTO OrderPlacement (orderNum, SSN, requestDate) VALUES (4, '400000000', '2021-11-26');
INSERT INTO OrderPlacement (orderNum, SSN, requestDate) VALUES (5, '300000000', '2021-11-27');
INSERT INTO OrderPlacement (orderNum, SSN, requestDate) VALUES (6, '400000000', '2021-11-27');

COMMIT;


-- -----------------------------------------------------
-- Data for table LineItems
-- -----------------------------------------------------
START TRANSACTION;
USE supply_cabinet;
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (1, 1, 1, 100, 10, '2021-11-26');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (1, 1, 2, 100, 10, '2021-11-26');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (1, 1, 3, 100, 10, '2021-11-26');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (2, 1, 1, 100, 10, '2021-11-26');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (2, 1, 2, 100, 10, '2021-11-26');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (2, 1, 3, 100, 10, '2021-11-26');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (3, 2, 1, 50, 20, '2021-11-27');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (3, 2, 2, 50, 20, '2021-11-27');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (3, 2, 3, 50, 20, '2021-11-27');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (4, 2, 1, 50, 20, '2021-11-27');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (4, 2, 2, 50, 20, '2021-11-27');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (4, 2, 3, 50, 20, '2021-11-27');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (5, 2, 1, 50, 20, '2021-11-28');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (5, 2, 2, 50, 20, '2021-11-28');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (5, 2, 3, 50, 20, '2021-11-28');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (6, 2, 1, 50, 20, '2021-11-28');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (6, 2, 2, 50, 20, '2021-11-28');
INSERT INTO LineItems (orderNum, vendorNum, productNum, quantity, unitPrice, arrivalDate) VALUES (6, 2, 3, 50, 20, '2021-11-28');

COMMIT;


-- -----------------------------------------------------
-- Data for table Shipped
-- -----------------------------------------------------
START TRANSACTION;
USE supply_cabinet;
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (1, 2, 1, 100, '2021-11-24');
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (2, 2, 2, 100, '2021-11-24');
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (3, 2, 3, 100, '2021-11-24');
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (4, 3, 1, 100, '2021-11-25');
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (5, 3, 2, 100, '2021-11-25');
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (6, 3, 3, 100, '2021-11-25');
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (7, 2, 1, 50, '2021-11-26');
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (8, 2, 2, 50, '2021-11-26');
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (9, 2, 3, 50, '2021-11-26');
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (10, 3, 1, 50, '2021-11-27');
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (11, 3, 2, 50, '2021-11-27');
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (12, 3, 3, 50, '2021-11-27');
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (16, 2, 1, 50, NULL);
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (17, 2, 2, 50, NULL);
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (18, 2, 3, 50, NULL);
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (19, 3, 1, 50, NULL);
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (20, 3, 2, 50, NULL);
INSERT INTO Shipped (id, officeNum, productNum, quantityShipped, shippedDate) VALUES (21, 3, 3, 50, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table Sold
-- -----------------------------------------------------
START TRANSACTION;
USE supply_cabinet;
INSERT INTO Sold (id, officeNum, productNum, quantitySold, soldDate) VALUES (1, 2, 1, 100, '2021-11-25');
INSERT INTO Sold (id, officeNum, productNum, quantitySold, soldDate) VALUES (2, 2, 2, 100, '2021-11-25');
INSERT INTO Sold (id, officeNum, productNum, quantitySold, soldDate) VALUES (3, 2, 3, 100, '2021-11-25');
INSERT INTO Sold (id, officeNum, productNum, quantitySold, soldDate) VALUES (4, 3, 1, 100, '2021-11-26');
INSERT INTO Sold (id, officeNum, productNum, quantitySold, soldDate) VALUES (5, 3, 2, 100, '2021-11-26');
INSERT INTO Sold (id, officeNum, productNum, quantitySold, soldDate) VALUES (6, 3, 3, 100, '2021-11-26');

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
