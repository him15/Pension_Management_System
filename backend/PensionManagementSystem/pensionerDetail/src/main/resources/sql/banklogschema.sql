CREATE SCHEMA IF NOT EXISTS pms;
USE pms;
CREATE TABLE IF NOT EXISTS banklog (
 id BIGINT PRIMARY KEY, 
 aadhaar_number BIGINT NOT NULL, 
 account_number BIGINT NOT NULL, 
 allowances DOUBLE NOT NULL, 
 bank_name VARCHAR(255)NOT NULL, 
 bank_service_charge DOUBLE NOT NULL, 
 date_of_birth DATE NOT NULL,
 name VARCHAR(255) NOT NULL, 
 pan_number VARCHAR(255) NOT NULL, 
 pension_amount DOUBLE NOT NULL, 
 salary DOUBLE NOT NULL, 
 type_of_pension VARCHAR(255) NOT NULL, 
 date_of_transaction DATE NOT NULL);
