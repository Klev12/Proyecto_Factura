create table if not exists CLIENT(
    id Serial PRIMARY KEY,
    nui varchar (30),
    fullname VARCHAR (200),
    address VARCHAR (100)
    );
create table if not exists INVOICE(
    id Serial PRIMARY KEY NOT NULL,
    code VARCHAR(30) NOT NULL,
    create_at date NOT NULL,
    total decimal NOT NULL,
    client_id INT,
    FOREIGN KEY (CLIENT_id) REFERENCES CLIENT(id)

    );
create table if not exists PRODUCT(
    id SERIAL PRIMARY KEY,
    description VARCHAR (200)NOT NULL,
    brand  VARCHAR (30) NOT NULL,
    price decimal NOT NULL,
    stock int NOT NULL
    );
create table if not exists DETAIL(
    id SERIAL PRIMARY KEY,
    quantity INT NOT NULL,
    price decimal NOT NULL,
    invoice_id INT,
    product_id INT,
    FOREIGN KEY (INVOICE_id) REFERENCES INVOICE(id) ,
    FOREIGN KEY (PRODUCT_id) REFERENCES PRODUCT(id)

    );