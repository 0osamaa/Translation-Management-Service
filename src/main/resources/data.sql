INSERT INTO translation (key, locale, content, tags) VALUES
('greeting', 'en', 'Hello', 'web,mobile'),
('greeting', 'fr', 'Bonjour', 'web,mobile'),
('greeting', 'es', 'Hola', 'web,mobile'),
('farewell', 'en', 'Goodbye', 'web'),
('farewell', 'fr', 'Au revoir', 'web'),
('farewell', 'es', 'Adiós', 'web');



INSERT INTO translation (key, locale, content, tags)
SELECT
    CONCAT('key_', n),
    CASE
        WHEN n % 3 = 0 THEN 'en'
        WHEN n % 3 = 1 THEN 'fr'
        ELSE 'es'
    END,
    CONCAT('content_', n),
    CASE
        WHEN n % 2 = 0 THEN 'web'
        ELSE 'mobile'
    END
FROM generate_series(1, 100000) n;