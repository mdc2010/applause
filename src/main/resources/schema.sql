CREATE TABLE TESTERS
(
    tester_id INT PRIMARY KEY,
    first_name VARCHAR(250),
    last_name VARCHAR(250),
    country VARCHAR(3),
    last_login TIMESTAMP

)
AS SELECT * FROM CSVREAD('classpath:testdata/testers.csv');

CREATE TABLE TESTER_DEVICE
(
    tester_id INT NOT NULL,
    device_id INT NOT NULL,
    CONSTRAINT TESTER_DEVICE_COMPOSITE_KEY PRIMARY KEY (tester_id,device_id)
)
AS SELECT * FROM CSVREAD('classpath:testdata/tester_device.csv');

CREATE TABLE DEVICES
(
    device_id INT PRIMARY KEY,
    description VARCHAR(250)
)
AS SELECT * FROM CSVREAD('classpath:testdata/devices.csv');

CREATE TABLE BUGS
(
    bug_id INT NOT NULL,
    device_id INT NOT NULL,
    tester_id INT NOT NULL,
    CONSTRAINT BUGS_COMPOSITE_KEY PRIMARY KEY (bug_id,device_id,tester_id)

)
AS SELECT * FROM CSVREAD('classpath:testdata/bugs.csv');