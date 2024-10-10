-- -----------------------------------------------------
-- Schema full-stack-sportscenter
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS `sportscenter`;

USE `sportscenter` ;

-- Drop existing tables if they exist
DROP TABLE IF EXISTS Brand;
DROP TABLE IF EXISTS Type;
DROP TABLE IF EXISTS Product;

-- Create the Brand table
CREATE TABLE `Brand` (
                         `Id` INT AUTO_INCREMENT PRIMARY KEY,
                         `Name` VARCHAR(255) NOT NULL
                     );

-- Insert data into the Brand table
INSERT INTO Brand (Name) VALUES
                             ('Adidas'),
                             ('ASICS'),
                             ('Victor'),
                             ('Yonex'),
                             ('Puma'),
                             ('Nike'),
                             ('Babolat');

-- Create the Type table
CREATE TABLE `Type` (
                        `Id` INT AUTO_INCREMENT PRIMARY KEY,
                        `Name` VARCHAR(255) NOT NULL
);

-- Insert data into the Type table
INSERT INTO Type (Name) VALUES
                            ('Shoes'),
                            ('Rackets'),
                            ('Football'),
                            ('Kit Bags');

-- Create the Product table
CREATE TABLE `Product` (
                           `Id` INT AUTO_INCREMENT PRIMARY KEY,
                           `Name` VARCHAR(255) NOT NULL,
                           `Description` VARCHAR(1000),
                           `Price` DECIMAL(10, 2) NOT NULL,
                           `PictureUrl` VARCHAR(255),
                           `ProductTypeId` INT NOT NULL,
                           `ProductBrandId` INT NOT NULL,
                           FOREIGN KEY (`ProductTypeId`) REFERENCES `Type`(`Id`),
                           FOREIGN KEY (`ProductBrandId`) REFERENCES `Brand`(`Id`)
);

-- Insert data into the Product table
INSERT INTO Product (Name, Description, Price, PictureUrl, ProductTypeId, ProductBrandId) VALUES
                                                                                              ('Adidas Quick Force Indoor Badminton Shoes', 'Designed for professional as well as amateur badminton players. These indoor shoes are crafted with synthetic upper that provides natural fit, while the EVA midsole provides lightweight cushioning.', 3500, 'images/Product/adidas_shoe-1.png', 1, 1),
                                                                                              ('Adidas Quick Force Indoor Badminton Shoes', 'Designed for professional as well as amateur badminton players. These indoor shoes are crafted with synthetic upper that provides natural fit, while the EVA midsole provides lightweight cushioning.', 3375, 'images/Product/adidas_shoe-2.png', 1, 1);