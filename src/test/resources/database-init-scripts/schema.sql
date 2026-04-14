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
CREATE TABLE job_tracker.reminders(
    id BIGSERIAL PRIMARY KEY,
    job_id BIGSERIAL,
    reminder_Time TIMESTAMP,
    type VARCHAR(255),
    status VARCHAR(255),
    email  VARCHAR(255),
    error_message varchar(555),
    retry_count  INTEGER
    );