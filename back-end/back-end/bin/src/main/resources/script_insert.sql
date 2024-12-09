INSERT INTO area (name, budget_area)
VALUES 
('TI', 500000),
('Marketing', 300000),
('Financeiro', 200000);

INSERT INTO center_cost (name, description, area_id)
VALUES 
('Desenvolvimento', 'Centro de custo para a área de desenvolvimento de sistemas', 1),  -- Referencia 'TI'
('Publicidade', 'Centro de custo da área de marketing para campanhas', 2),  -- Referencia 'Marketing'
('Contabilidade', 'Centro de custo da área financeira', 3);  -- Referencia 'Financeiro'

INSERT INTO employee (name, email, job_title, position, salary, cost_center_id)
VALUES 
('Carlos Silva', 'carlos@empresa.com', 'Desenvolvedor', 'Programador', 4500.00, 1),  -- Referencia 'Desenvolvimento'
('Fernanda Souza', 'fernanda@empresa.com', 'Analista de Marketing', 'Analista', 3500.00, 2),  -- Referencia 'Publicidade'
('Lucas Oliveira', 'lucas@empresa.com', 'Contador', 'Contador', 5000.00, 3);  -- Referencia 'Contabilidade'

INSERT INTO variable_expense (type, category, payment_method, approval, value, date, describer, observation, cost_center_id, responsibile_id)
VALUES 
('Despesas Operacionais', 'Serviços', 'Cartão de Crédito', TRUE, 1200.00, '2024-12-01', 'Serviço de manutenção de servidores', 'Aprovado para pagamento', 1, 1),  -- Referencia 'Desenvolvimento' e 'Carlos Silva'
('Publicidade', 'Campanha de Marketing', 'Transferência Bancária', TRUE, 5000.00, '2024-11-15', 'Campanha publicitária no Instagram', 'Aprovado para pagamento', 2, 2),  -- Referencia 'Publicidade' e 'Fernanda Souza'
('Consultoria Contábil', 'Consultoria', 'Boleto Bancário', FALSE, 2000.00, '2024-12-03', 'Consultoria para reestruturação contábil', 'Aguardando aprovação', 3, 3);  -- Referencia 'Contabilidade' e 'Lucas Oliveira'

INSERT INTO users (name, email, password, type_user)
VALUES 
('Admin', 'admin@empresa.com', 'admin123', 'ADMIN'),
('Financeiro', 'financeiro@empresa.com', 'fin123', 'USER'),
('Marketing', 'marketing@empresa.com', 'mark123', 'USER');
