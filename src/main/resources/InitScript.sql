drop database pharmacy_dev;
create database pharmacy_dev;
use pharmacy_dev;

create table user
(
    id                 binary(255) primary key auto_increment,
    first_name         varchar(255) null,
    last_name          varchar(255) null,
    mobile_no          varchar(255) null,
    password           varchar(255) null,
    user_name          varchar(255) null,
    created_by         int          null,
    creation_date      datetime     null,
    last_modified_by   bigint       null,
    last_modified_date datetime     null
)
    engine = MyISAM;

create table company
(
    id                 int primary key auto_increment,
    company_address    varchar(255) null,
    company_code       varchar(255) null,
    company_details    varchar(255) null,
    company_name       varchar(255) null,
    is_active          bit          null,
    created_by         int          null,
    creation_date      datetime     null,
    last_modified_by   int          null,
    last_modified_date datetime     null
)
    engine = MyISAM;

create table drug
(
    id                 int primary key auto_increment,
    company_id         int          null,
    drug_name          varchar(255) null,
    drug_type          varchar(255) null,
    is_active          bit          null,
    section            varchar(255) null,
    created_by         int          null,
    creation_date      datetime     null,
    last_modified_by   int          null,
    last_modified_date datetime     null
)
    engine = MyISAM;


drop table if exists drug_detail;
create table drug_detail
(
    id                 int primary key auto_increment,
    drug_id            int           null,
    amount             double(10, 3) null,
    balance_quantity   int           null,
    batch_no           varchar(255)  null,
    drug_quantity      int           null,
    drug_rate          double(10, 3) null,
    expiry_date        timestamp     null,
    production_date    timestamp     null,
    purchase_date      timestamp     null,
    is_active          bit           null,
    created_by         int           null,
    creation_date      datetime      null,
    last_modified_by   int           null,
    last_modified_date datetime      null
)
    engine = MyISAM;


INSERT INTO company (company_address, company_code, company_details, company_name, is_active)
VALUES ('MUMBAI', 'CIP', 'CIPLA DETAILS', 'CIPLA', true),
       ('PUNE', 'LUPIN', 'LUPIN DETAILS', 'LUPIN', true),
       ('AURANGABAD', 'REDDY', 'DR.REDDY DETAILS', 'DR.REDDY', true);

INSERT INTO drug (company_id, drug_name, drug_type, is_active, section)
VALUES (1, 'drug1', 'tablet', true, 'A'),
       (1, 'drug2', 'syrup', true, 'B'),
       (1, 'drug3', 'tablet', true, 'B'),
       (2, 'drug4', 'syrup', true, 'D'),
       (2, 'drug5', 'tablet', true, 'A'),
       (2, 'drug6', 'syrup', true, 'C'),
       (3, 'drug7', 'tablet', true, 'C');

INSERT
INTO drug_detail (amount, balance_quantity, batch_no, drug_id, drug_quantity, drug_rate, expiry_date,
                               is_active, production_date, purchase_date)
VALUES (1000.0,20,'A001',1,50,10.0,'2020-10-01 12:51:37',true,'2020-07-02 12:51:37','2020-08-02 12:51:37'),
       (2000.0,20,'A002',1,100,10.0,'2020-10-20 12:51:37',true,'2020-07-02 12:51:37','2020-08-02 12:51:37'),
       (3000.0,20,'A003',1,100,10.0,'2020-12-20 12:51:37',true,'2020-07-02 12:51:37','2020-08-02 12:51:37'),
       (2000.0,20,'A002',2,100,10.0,'2020-10-20 12:51:37',true,'2020-07-02 12:51:37','2020-08-02 12:51:37'),
       (2000.0,20,'A002',2,100,10.0,'2020-10-20 12:51:37',true,'2020-07-02 12:51:37','2020-08-02 12:51:37'),
       (2000.0,20,'A002',2,100,10.0,'2020-10-20 12:51:37',true,'2020-07-02 12:51:37','2020-08-02 12:51:37'),
       (2000.0,20,'A004',1,100,10.0,'2020-10-20 12:51:37',true,'2020-07-02 12:51:37','2020-08-02 12:51:37'),
       (2000.0,20,'A005',1,100,10.0,'2020-10-20 12:51:37',true,'2020-07-02 12:51:37','2020-08-02 12:51:37');


drop table if exists sales_drug;
create table sales_drug
(
    id        int primary key auto_increment,
    amount    double(10, 3) null,
    drug_name varchar(255)  null,
    bill_no   varchar(255)  null,
    batch_no  varchar(255)  null,
    quantity  int           null,
    rate      double(10, 3) null,
    date      timestamp     null
);
