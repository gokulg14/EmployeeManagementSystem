DB script 

use defaultdb;

CREATE TABLE department ( department_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255) NOT NULL, created_date DATE NOT NULL,
head_id INT );

CREATE TABLE employee ( employee_id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(255) NOT NULL, date_of_birth DATE,
salary DECIMAL(10, 2) NOT NULL,
address VARCHAR(500),
role VARCHAR(100),
joining_date DATE NOT NULL,
yearly_bonus_percentage VARCHAR(10),
department_id INT,
manager_id INT);

ADD CONSTRAINT fk_employee_department
FOREIGN KEY (department_id)
REFERENCES department(department_id)
ON DELETE SET NULL
ON UPDATE CASCADE;

ALTER TABLE employee
ADD CONSTRAINT fk_employee_manager
FOREIGN KEY (manager_id)
REFERENCES employee(employee_id)
ON DELETE SET NULL
ON UPDATE CASCADE;

ALTER TABLE department
ADD CONSTRAINT fk_department_head
FOREIGN KEY (head_id)
REFERENCES employee(employee_id)
ON DELETE SET NULL
ON UPDATE CASCADE;
