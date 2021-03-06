use dkb;

CREATE TABLE IF NOT EXISTS ACCOUNT
                     (
                                  IBAN  VARCHAR(255)  NOT NULL
                                , ACCOUNT_TYPE      varchar(20) NOT NULL
                                , BALANCE   LONG
                                , LOCKED BOOLEAN
                                , PRIMARY KEY (IBAN)
                     )
        ;
		
CREATE TABLE IF NOT EXISTS TRANSACTION
			 (
						  TRANSACTION_UUID  VARCHAR(255)  NOT NULL
						, SOURCE_IBAN      varchar(255) NOT NULL
						, DESTINATION_IBAN      varchar(255)
						, TRANSACTION_STATUS   VARCHAR(20)
						, TRANSACTION_TYPE   VARCHAR(20)
						, MESSAGE   VARCHAR(255)
						, AMOUNT   Long
						, CREATION_DATE DATE
						, PRIMARY KEY (TRANSACTION_UUID)
			 )
;