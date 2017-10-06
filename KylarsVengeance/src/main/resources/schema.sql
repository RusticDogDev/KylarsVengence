CREATE TABLE user (
   id		identity,
   fName	VARCHAR(255) NOT NULL,
   lName	VARCHAR(255) NOT NULL,   	
   userType VARCHAR(255) NOT NULL, 
   level	INT NOT NULL,   
   balance	BIGINT NULL,    	
   PRIMARY KEY(id)
);

CREATE TABLE itemsOwned (   
   itemId		BIGINT NULL,
   id			BIGINT NULL,
   PRIMARY KEY(id, itemId)
);

CREATE TABLE items (
   itemId		identity,
   attack		INT NOT NULL,
   defence		INT NOT NULL,
   itemLevel	INT NOT NULL, 
   itemType		VARCHAR(255) NOT NULL,
   itemName		VARCHAR(255) NOT NULL,
   itemValue	BIGINT NOT NULL,
   PRIMARY KEY(itemId)
);