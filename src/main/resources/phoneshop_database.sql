

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';



-- -----------------------------------------------------
-- Table `phoneshop`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `phoneshop`.`Role` (
  `idRole` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idRole`),
  UNIQUE INDEX `idRole_UNIQUE` (`idRole` ASC) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phoneshop`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `phoneshop`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phonenumber` VARCHAR(9) NOT NULL,
  `idRoleFk` INT NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) ,
  INDEX `fk_User_Role_idx` (`idRoleFk` ASC) ,
  CONSTRAINT `fk_User_Role`
    FOREIGN KEY (`idRoleFk`)
    REFERENCES `phoneshop`.`Role` (`idRole`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phoneshop`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `phoneshop`.`Product` (
  `idProduct` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `price` DOUBLE NOT NULL,
  `numberOfItems` INT NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`idProduct`),
  UNIQUE INDEX `idProduct_UNIQUE` (`idProduct` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phoneshop`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `phoneshop`.`Order` (
  `idOrder` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  `idUserFk` INT NOT NULL,
  `totalPrice` FLOAT NOT NULL,
  PRIMARY KEY (`idOrder`),
  UNIQUE INDEX `idUserProduct_UNIQUE` (`idOrder` ASC) ,
  INDEX `fk_UserProduct_User1_idx` (`idUserFk` ASC) ,
  CONSTRAINT `fk_User_Order`
    FOREIGN KEY (`idUserFk`)
    REFERENCES `phoneshop`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `phoneshop`.`OrderItemList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `phoneshop`.`OrderItemList` (
  `idOrderItemList` INT NOT NULL AUTO_INCREMENT,
  `idOrderFk` INT NOT NULL,
  `idProductFk` INT NOT NULL,
  `quantity` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`idOrderItemList`),
  UNIQUE INDEX `idOrderItemList_UNIQUE` (`idOrderItemList` ASC) ,
  INDEX `fk_Order_OrderItemList_idx` (`idOrderFk` ASC) ,
  INDEX `fk_Product_OrderItemList_idx` (`idProductFk` ASC) ,
  CONSTRAINT `fk_Order_OrderItemList`
    FOREIGN KEY (`idOrderFk`)
    REFERENCES `phoneshop`.`Order` (`idOrder`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Product_OrderItemList`
    FOREIGN KEY (`idProductFk`)
    REFERENCES `phoneshop`.`Product` (`idProduct`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
