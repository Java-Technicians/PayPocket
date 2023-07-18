CREATE SCHEMA billetera_virtual;

CREATE TABLE billetera_virtual.roles(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL,
    role_desc VARCHAR(255) NOT NULL
);

CREATE TABLE billetera_virtual.users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    passwords VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    soft_delete BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);


CREATE TABLE billetera_virtual.accounts(
	account_id INT AUTO_INCREMENT PRIMARY KEY,
    account_currency VARCHAR(255) NOT NULL,
    account_transaction_limit DOUBLE NOT NULL,
    account_balance DOUBLE NOT NULL,
    user_id INT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    soft_delete BOOLEAN DEFAULT FALSE,
	FOREIGN KEY (user_id) REFERENCES users(id)
);


CREATE TABLE billetera_virtual.transactions(
	transactions_id INT PRIMARY KEY,
    transactions_type VARCHAR(255) NOT NULL,
    transactions_description VARCHAR(255) NOT NULL,
    account_id INT NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

CREATE TABLE billetera_virtual.fixed_term_deposits (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ftd_amount DOUBLE NOT NULL,
    account_id INT NOT NULL,
    interest DOUBLE NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    closing_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

CREATE TABLE billetera_virtual.crypto (
    crypto_id INT AUTO_INCREMENT PRIMARY KEY,
    crypto_name VARCHAR(255) NOT NULL,
    crypto_amount DOUBLE NOT NULL,
    account_id INT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    closing_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    soft_delete BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);


CREATE TABLE credit_card(
    credit_card_id INT AUTO_INCREMENT PRIMARY KEY,
    credit_name VARCHAR (255) NOT NULL,
    amount_available DOUBLE NOT NULL,
    amount DOUBLE NOT NULL,
    account_id INT NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    closing_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    soft_delete BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);