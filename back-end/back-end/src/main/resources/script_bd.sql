-- Criação da tabela 'area' (Área ou Centro de Custo)
CREATE TABLE area (
    id_area INT PRIMARY KEY AUTO_INCREMENT,  -- Usando INT e AUTO_INCREMENT para id_area
    name VARCHAR(255) NOT NULL,
    budget_area INT NOT NULL
);

-- Criação da tabela 'center_cost' (Centro de Custo) com referência à 'area'
CREATE TABLE center_cost (
    id_center_cost INT PRIMARY KEY AUTO_INCREMENT,  -- Usando INT e AUTO_INCREMENT para id_center_cost
    name VARCHAR(255) NOT NULL,
    description TEXT,
    area_id INT NOT NULL,  -- Tipo INT para referência de área
    FOREIGN KEY (area_id) REFERENCES area(id_area) ON DELETE CASCADE -- Relacionamento com a área
);

-- Criação da tabela 'employee' (Funcionário) com referência ao centro de custo
CREATE TABLE employee (
    id_employee INT PRIMARY KEY AUTO_INCREMENT,  -- Usando INT e AUTO_INCREMENT para id_employee
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    job_title VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    salary DECIMAL(15,2) NOT NULL,
    cost_center_id INT NOT NULL,  -- Tipo INT para referência ao centro de custo
    FOREIGN KEY (cost_center_id) REFERENCES center_cost(id_center_cost) -- Relacionamento com o centro de custo
);

-- Criação da tabela 'variable_expense' (Despesa Variável) com referência ao centro de custo e funcionário
CREATE TABLE variable_expense (
    id INT PRIMARY KEY AUTO_INCREMENT,  -- Usando INT e AUTO_INCREMENT para id
    type VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    payment_method VARCHAR(255) NOT NULL,
    approval BOOLEAN NOT NULL,
    value DECIMAL(15,2) NOT NULL,
    date DATE,
    describer TEXT,
    observation TEXT,
    cost_center_id INT NOT NULL,  -- Tipo INT para referência ao centro de custo
    responsibile_id INT,  -- Tipo INT para referência ao funcionário responsável
    FOREIGN KEY (cost_center_id) REFERENCES center_cost(id_center_cost),
    FOREIGN KEY (responsibile_id) REFERENCES employee(id_employee) -- Relacionamento com o funcionário responsável
);

-- Criação da tabela 'users' (Usuários)
CREATE TABLE users (
    id_user INT PRIMARY KEY AUTO_INCREMENT,  -- Usando INT e AUTO_INCREMENT para id_user
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    type_user VARCHAR(255) NOT NULL
);
