CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_activity_user_username ON activity_user(username);

CREATE INDEX CONCURRENTLY IF NOT EXISTS idx_activity_user_email ON activity_user(email);
