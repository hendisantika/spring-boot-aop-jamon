-- Create account table
CREATE TABLE IF NOT EXISTS account (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    balance DECIMAL(10, 2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO account (name, email, balance) VALUES
('John Doe', 'john.doe@example.com', 1000.00),
('Jane Smith', 'jane.smith@example.com', 2500.50),
('Bob Johnson', 'bob.johnson@example.com', 500.75),
('Alice Williams', 'alice.williams@example.com', 3200.00),
('Charlie Brown', 'charlie.brown@example.com', 150.25)
ON CONFLICT (email) DO NOTHING;
