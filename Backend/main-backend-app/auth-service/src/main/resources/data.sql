INSERT INTO authority(id, name) 
SELECT nextval('authority_seq'), 'ADMIN'
WHERE 
NOT EXISTS (
    SELECT 1 FROM authority WHERE name = 'ADMIN'
	);

INSERT INTO users(type, id, password, password_changed_date, username) 
SELECT 'admin', nextval('user_seq'), '$2a$10$QQxHVraAtUHQqf266vLzfuNLsF5XVS7W4AnJatRZR2gtQpk1LMD0K', current_timestamp, 'admin'
WHERE 
NOT EXISTS (
    SELECT 1 FROM users WHERE username = 'admin'
	);

INSERT INTO users_authorities(user_id, authorities_id) 
SELECT u.id, a.id
FROM users u, authority a
WHERE u.username = 'admin' AND a.name = 'ADMIN' AND 
	NOT EXISTS (
    	SELECT 1 FROM users_authorities WHERE user_id = u.id
	);