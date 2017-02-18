DROP TABLE service_resource; 
DROP TABLE key_secret;
DROP TABLE app_info;
DROP TABLE flow_info;
DROP TABLE quantity_info;
DROP TABLE audit_trail;
DROP TABLE aliase_info;

CREATE TABLE service_resource (
	resource_url VARCHAR(255) PRIMARY KEY
);

CREATE TABLE app_info (
	app_id      VARCHAR(15) PRIMARY KEY,
	app_key     VARCHAR(50) NOT NULL,
	app_secret  VARCHAR(50) NOT NULL,
	app_name    VARCHAR(50) NOT NULL UNIQUE,
	app_address VARCHAR(100),
	description VARCHAR(200),
	
	app_admin   VARCHAR(20),
	admin_email VARCHAR(30), 
	admin_tel   VARCHAR(20)
);

CREATE TABLE aliase_info (
    original    VARCHAR(50) PRIMARY KEY,
    aliase      VARCHAR(50) NOT NULL   
);

CREATE TABLE quantity_info (
    quantity_id   VARCHAR(15) PRIMARY KEY,
	app_key       VARCHAR(50) NOT NULL,
	api_method    VARCHAR(50) NOT NULL,
	total         INTEGER,
	used          INTEGER,
	creation_date DATE
);   

CREATE TABLE audit_trail (
    audit_id    VARCHAR(15) PRIMARY KEY,
    app_key     VARCHAR(50),
    api_method  VARCHAR(50) NOT NULL,
    api_type    INTEGER,
	parameters  VARCHAR(200),
	audit_date  DATE
);      