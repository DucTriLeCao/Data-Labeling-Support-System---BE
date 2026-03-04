-- ============================================
-- DLSS Database - SQL Server
-- Chạy script này trong SSMS hoặc Azure Data Studio
-- ============================================

CREATE DATABASE dlss
GO

USE dlss
GO

-- =========================
-- USERS
-- =========================
CREATE TABLE users (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(100) NOT NULL UNIQUE,
    email NVARCHAR(150) UNIQUE,
    password_hash NVARCHAR(255) NOT NULL,
    role NVARCHAR(50) NOT NULL CHECK (role IN ('admin','manager','annotator','reviewer')),
    status NVARCHAR(20) DEFAULT 'active',
    created_at DATETIME2 DEFAULT SYSDATETIME()
);

-- =========================
-- PROJECTS
-- =========================
CREATE TABLE projects (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(200) NOT NULL UNIQUE,
    description NVARCHAR(MAX),
    status NVARCHAR(20) DEFAULT 'active',
    created_at DATETIME2 DEFAULT SYSDATETIME()
);

-- =========================
-- PROJECT MEMBERS
-- =========================
CREATE TABLE project_members (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    project_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    role_in_project NVARCHAR(50) NOT NULL,
    joined_at DATETIME2 DEFAULT SYSDATETIME(),
    CONSTRAINT uq_project_member UNIQUE(project_id, user_id),
    FOREIGN KEY (project_id) REFERENCES projects(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- =========================
-- DATASETS
-- =========================
CREATE TABLE datasets (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    project_id BIGINT NOT NULL,
    name NVARCHAR(200) NOT NULL,
    description NVARCHAR(MAX),
    status NVARCHAR(20) DEFAULT 'active',
    created_at DATETIME2 DEFAULT SYSDATETIME(),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);

-- =========================
-- DATA ITEMS
-- =========================
CREATE TABLE data_items (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    dataset_id BIGINT NOT NULL,
    content NVARCHAR(MAX) NOT NULL,
    status NVARCHAR(20) DEFAULT 'NEW',
    created_at DATETIME2 DEFAULT SYSDATETIME(),
    FOREIGN KEY (dataset_id) REFERENCES datasets(id)
);

-- =========================
-- DATASET ASSIGNMENTS
-- =========================
CREATE TABLE dataset_assignments (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    dataset_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    role NVARCHAR(50) NOT NULL CHECK (role IN ('annotator','reviewer')),
    assigned_at DATETIME2 DEFAULT SYSDATETIME(),
    CONSTRAINT uq_dataset_user UNIQUE(dataset_id, user_id),
    FOREIGN KEY (dataset_id) REFERENCES datasets(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- =========================
-- DATA ITEM ASSIGNMENTS
-- =========================
CREATE TABLE data_item_assignments (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    data_item_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    status NVARCHAR(20) DEFAULT 'assigned',
    assigned_at DATETIME2 DEFAULT SYSDATETIME(),
    CONSTRAINT uq_dataitem_user UNIQUE(data_item_id, user_id),
    FOREIGN KEY (data_item_id) REFERENCES data_items(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- =========================
-- LABELS
-- =========================
CREATE TABLE labels (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    project_id BIGINT NOT NULL,
    name NVARCHAR(100) NOT NULL,
    parent_id BIGINT NULL,
    FOREIGN KEY (project_id) REFERENCES projects(id),
    FOREIGN KEY (parent_id) REFERENCES labels(id)
);

-- =========================
-- ANNOTATIONS
-- =========================
CREATE TABLE annotations (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    data_item_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    label_value NVARCHAR(MAX) NOT NULL,
    status NVARCHAR(20) DEFAULT 'submitted',
    created_at DATETIME2 DEFAULT SYSDATETIME(),
    CONSTRAINT uq_annotation UNIQUE(data_item_id, user_id),
    FOREIGN KEY (data_item_id) REFERENCES data_items(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- =========================
-- REVIEWS
-- =========================
CREATE TABLE reviews (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    annotation_id BIGINT NOT NULL,
    reviewer_id BIGINT NOT NULL,
    status NVARCHAR(20) NOT NULL CHECK (status IN ('APPROVED','REJECTED')),
    comment NVARCHAR(MAX),
    reviewed_at DATETIME2 DEFAULT SYSDATETIME(),
    FOREIGN KEY (annotation_id) REFERENCES annotations(id),
    FOREIGN KEY (reviewer_id) REFERENCES users(id)
);

-- =========================
-- FINAL RESULTS
-- =========================
CREATE TABLE final_results (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    data_item_id BIGINT NOT NULL UNIQUE,
    annotation_id BIGINT NOT NULL,
    decided_by BIGINT NOT NULL,
    decided_at DATETIME2 DEFAULT SYSDATETIME(),
    FOREIGN KEY (data_item_id) REFERENCES data_items(id),
    FOREIGN KEY (annotation_id) REFERENCES annotations(id),
    FOREIGN KEY (decided_by) REFERENCES users(id)
);

-- =========================
-- EXPORT JOBS
-- =========================
CREATE TABLE export_jobs (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    project_id BIGINT NOT NULL,
    dataset_id BIGINT NULL,
    export_format NVARCHAR(50) NOT NULL,
    created_by BIGINT NOT NULL,
    created_at DATETIME2 DEFAULT SYSDATETIME(),
    FOREIGN KEY (project_id) REFERENCES projects(id),
    FOREIGN KEY (dataset_id) REFERENCES datasets(id),
    FOREIGN KEY (created_by) REFERENCES users(id)
);

-- =========================
-- EXPORT ITEMS
-- =========================
CREATE TABLE export_items (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    export_job_id BIGINT NOT NULL,
    data_item_id BIGINT NOT NULL,
    final_result_id BIGINT NOT NULL,
    FOREIGN KEY (export_job_id) REFERENCES export_jobs(id),
    FOREIGN KEY (data_item_id) REFERENCES data_items(id),
    FOREIGN KEY (final_result_id) REFERENCES final_results(id)
);

-- =========================
-- ACTIVITY LOGS
-- =========================
CREATE TABLE activity_logs (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    user_id BIGINT NOT NULL,
    action NVARCHAR(100) NOT NULL,
    entity_type NVARCHAR(50),
    entity_id BIGINT,
    created_at DATETIME2 DEFAULT SYSDATETIME(),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
