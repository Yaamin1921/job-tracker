DROP SCHEMA IF EXISTS job_tracker CASCADE;
CREATE SCHEMA job_tracker;

CREATE TABLE job_tracker.jobs (
    id BIGSERIAL PRIMARY KEY,
    company_name VARCHAR(255),
    role VARCHAR(255),
    status VARCHAR(50) NOT NULL,
    applied_date DATE,
    source VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO  job_tracker.jobs
(company_name, role, status, applied_date, source, created_at, updated_at)
VALUES
('Google', 'Backend Developer', 'APPLIED', '2026-04-01', 'LinkedIn', NOW(), NOW()),
('Amazon', 'SDE', 'INTERVIEW', '2026-04-02', 'Referral', NOW(), NOW()),
('Microsoft', 'Software Engineer', 'REJECTED', '2026-03-28', 'Naukri', NOW(), NOW()),
('Flipkart', 'Java Developer', 'SAVED', NULL, 'Career Page', NOW(), NOW()),
('Adobe', 'Backend Engineer', 'OFFERED', '2026-03-25', 'LinkedIn', NOW(), NOW()),
('Infosys', 'System Engineer', 'APPLIED', '2026-04-03', 'Campus', NOW(), NOW()),
('TCS', 'Assistant System Engineer', 'REJECTED', '2026-03-20', 'Naukri', NOW(), NOW()),
('Wipro', 'Project Engineer', 'INTERVIEW', '2026-04-04', 'Referral', NOW(), NOW());

INSERT INTO job_tracker.jobs
(company_name, role, status, applied_date, source, created_at, updated_at)
VALUES
('StartupX', 'Full Stack Dev', 'APPLIED', NULL, NULL, NOW(), NOW()),
('UnknownCorp', 'Intern', 'SAVED', NULL, 'LinkedIn', NOW(), NOW());