-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Lavalle
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Lavalle` ;

-- -----------------------------------------------------
-- Schema Lavalle
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Lavalle` DEFAULT CHARACTER SET utf8 ;
USE `Lavalle` ;

-- -----------------------------------------------------
-- Table `Lavalle`.`Utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lavalle`.`Utente` (
  `CodUtente` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Cognome` VARCHAR(45) NOT NULL,
  `EMail` VARCHAR(45) NOT NULL,
  `IdPermesso` INT NOT NULL,
  `Password` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`CodUtente`),
  UNIQUE INDEX `EMail_UNIQUE` (`EMail` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lavalle`.`MetodoPagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lavalle`.`MetodoPagamento` (
  `RefUtente` INT NOT NULL,
  `NumeroCarta` VARCHAR(16) NOT NULL,
  `Scadenza` VARCHAR(5) NOT NULL,
  `VCC` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`RefUtente`, `NumeroCarta`),
  CONSTRAINT `RefUtente`
    FOREIGN KEY (`RefUtente`)
    REFERENCES `Lavalle`.`Utente` (`CodUtente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lavalle`.`OrdineBar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lavalle`.`OrdineBar` (
  `CodOrdine` INT NOT NULL AUTO_INCREMENT,
  `RefUtente` INT NOT NULL,
  `MetodoPagamento` VARCHAR(45) NULL,
  `Servito` TINYINT NOT NULL,
  `Data` DATETIME NOT NULL DEFAULT NOW(),
  PRIMARY KEY (`CodOrdine`),
  CONSTRAINT `RefUtente2`
    FOREIGN KEY (`RefUtente`)
    REFERENCES `Lavalle`.`Utente` (`CodUtente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lavalle`.`ProdottoBar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lavalle`.`ProdottoBar` (
  `CodProdotto` INT NOT NULL AUTO_INCREMENT,
  `NomeProdotto` VARCHAR(45) NOT NULL,
  `Costo` FLOAT NOT NULL,
  PRIMARY KEY (`CodProdotto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lavalle`.`DettagliOrdine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lavalle`.`DettagliOrdine` (
  `RefOrdine` INT NOT NULL,
  `NomeProdotto` VARCHAR(45) NOT NULL,
  `Quantita` INT NOT NULL,
  `Costo` FLOAT NOT NULL,
  PRIMARY KEY (`RefOrdine`, `NomeProdotto`),
  CONSTRAINT `RefOrdine`
    FOREIGN KEY (`RefOrdine`)
    REFERENCES `Lavalle`.`OrdineBar` (`CodOrdine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lavalle`.`Posto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lavalle`.`Posto` (
  `CodPosto` INT NOT NULL AUTO_INCREMENT,
  `NumeroSdraio` INT NOT NULL,
  `CostoTotale` FLOAT NOT NULL,
  PRIMARY KEY (`CodPosto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lavalle`.`Turno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lavalle`.`Turno` (
  `CodTurno` INT NOT NULL AUTO_INCREMENT,
  `FasciaOraria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CodTurno`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Lavalle`.`Prenotazione`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lavalle`.`Prenotazione` (
  `CodPrenotazione` INT NOT NULL AUTO_INCREMENT,
  `RefUtente` INT NOT NULL,
  `Data` DATE NOT NULL,
  `RefPosto` INT NOT NULL,
  `RefTurno` INT NOT NULL,
  `MetodoPagamento` VARCHAR(45) NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Cognome` VARCHAR(45) NOT NULL,
  `Pagato` TINYINT NOT NULL DEFAULT 0,
  `Costo` FLOAT NOT NULL,
  PRIMARY KEY (`CodPrenotazione`),
  INDEX `RefPosto_idx` (`RefPosto` ASC) VISIBLE,
  INDEX `RefTurno_idx` (`RefTurno` ASC) VISIBLE,
  CONSTRAINT `RefUtente4`
    FOREIGN KEY (`RefUtente`)
    REFERENCES `Lavalle`.`Utente` (`CodUtente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `RefPosto`
    FOREIGN KEY (`RefPosto`)
    REFERENCES `Lavalle`.`Posto` (`CodPosto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `RefTurno`
    FOREIGN KEY (`RefTurno`)
    REFERENCES `Lavalle`.`Turno` (`CodTurno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
