-- Create the database
CREATE DATABASE IF NOT EXISTS company_db;
USE company_db;

-- Create the employee table
CREATE TABLE IF NOT EXISTS employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary DOUBLE NOT NULL
);

-- Insert some sample data
INSERT INTO employee (name, department, salary) VALUES
('Alice', 'IT', 60000),
('Bob', 'HR', 50000),
('Charlie', 'Finance', 70000);
