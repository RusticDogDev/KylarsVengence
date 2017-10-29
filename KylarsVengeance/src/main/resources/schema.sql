CREATE TABLE User (
   id		identity,
   userName	VARCHAR(255) NOT NULL,
   password	VARCHAR(255) NOT NULL,   	
   userType VARCHAR(255) NULL, 
   level	INT NOT NULL,   
   balance	BIGINT NULL,    	
   PRIMARY KEY(ID)
);

CREATE TABLE ItemsOwned (   
   itemId		BIGINT NULL,
   id			BIGINT NULL,
   PRIMARY KEY(id, itemId)
);

CREATE TABLE Items (
   itemId		identity,
   attack		INT NOT NULL,
   defence		INT NOT NULL,
   itemLevel	INT NOT NULL, 
   itemType		VARCHAR(255) NOT NULL,
   itemName		VARCHAR(255) NOT NULL,
   itemValue	BIGINT NOT NULL,
   PRIMARY KEY(itemId)
);