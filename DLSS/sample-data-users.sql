-- ============================================
-- Dữ liệu mẫu - USERS
-- Chạy sau khi đã chạy init-db-dlss.sql
-- Mật khẩu tất cả user: password (BCrypt hash)
-- ============================================

USE dlss
GO

-- BCrypt hash cho mật khẩu "123456"
-- Có thể thay đổi hash nếu app dùng cách mã hóa khác
INSERT INTO users (username, email, password_hash, role, status) VALUES
('admin', 'admin@dlss.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'admin', 'active'),
('manager1', 'manager1@dlss.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'manager', 'active'),
('annotator1', 'annotator1@dlss.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'annotator', 'active'),
('annotator2', 'annotator2@dlss.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'annotator', 'active'),
('reviewer1', 'reviewer1@dlss.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', 'reviewer', 'active');
