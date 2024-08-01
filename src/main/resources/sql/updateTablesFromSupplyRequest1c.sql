
-- TRUNCATE TABLE supply_requests RESTART IDENTITY;
--
-- DELETE FROM supply_requests
-- WHERE user_id IS NULL;



-- Запрос на обновление столбцов drawings_id и project_id
UPDATE supply_requests_1c sr
SET
    drawings_id = d.id,
    project_id = z.projects_id
FROM drawings d
         JOIN zones z ON d.zones_id = z.id
WHERE sr.drawing_string = d.code_normal;

-- Запрос для добавления отсутствующих записей
INSERT INTO supply_requests (number, drawings_id, projects_id, supply_request_date, user_id, created_date, year)
SELECT DISTINCT
    sr1c.number,
    sr1c.drawings_id,
    sr1c.project_id,
    sr1c.date AS supply_request_date,
    u.id AS user_id,
    NOW() AS created_date,
    sr1c.year
FROM supply_requests_1c sr1c
         LEFT JOIN users u ON sr1c.author = u.username
         LEFT JOIN supply_requests sr
--                    ON EXTRACT(YEAR FROM sr.supply_request_date) = sr1c.year
                   ON sr.year = sr1c.year
                       AND sr.number = sr1c.number
WHERE sr.id IS NULL
  AND sr1c.project_id IS NOT NULL
  AND sr1c.drawings_id IS NOT NULL;

-- добавляем supply_requests_1c.project_id там где мы по чертежам не нашли(оборудование и т.п.)
UPDATE supply_requests_1c
SET project_id = CASE
                     WHEN TRIM(project) = 'Анжеро-Судженск - Установка подготовки серы (УПС)' THEN 6
                     WHEN TRIM(project) = 'Анжеро-Судженск - Установка подготовки водорода (УПВ)' THEN 5
                     WHEN TRIM(project) = 'Анжеро-Судженск - Врезки техперевооружение' THEN 7
                     WHEN TRIM(project) = 'Анжеро-Судженск - Установка замедленного коксования (УЗК)' THEN 3
                     WHEN TRIM(project) = 'Анжеро-Судженск - Вакуумный блок (ВБ)' THEN 1
                     WHEN TRIM(project) = 'Анжеро-Судженск - Комплекс депарафинизации дизельного топлива (ДДТ)' THEN 4
                     ELSE project_id
    END
WHERE project_id IS NULL
  AND TRIM(project) IN (
                        'Анжеро-Судженск - Установка подготовки серы (УПС)',
                        'Анжеро-Судженск - Установка подготовки водорода (УПВ)',
                        'Анжеро-Судженск - Врезки техперевооружение',
                        'Анжеро-Судженск - Установка замедленного коксования (УЗК)',
                        'Анжеро-Судженск - Вакуумный блок (ВБ)',
                        'Анжеро-Судженск - Комплекс депарафинизации дизельного топлива (ДДТ)'
    );


-- добавить заявки не привязанные к чертежам и проектам
INSERT INTO supply_requests (number, drawings_id, projects_id, supply_request_date, user_id, created_date, comment,year)
SELECT DISTINCT
    sr1c.number,
    sr1c.drawings_id,
    sr1c.project_id,
    sr1c.date AS supply_request_date,
    u.id AS user_id,
    NOW() AS created_date,
    'drawing_id not found' as comment,
    sr1c.year
FROM supply_requests_1c sr1c
         LEFT JOIN users u ON sr1c.author = u.username
         LEFT JOIN supply_requests sr
--                    ON EXTRACT(YEAR FROM sr.supply_request_date) = sr1c.year
                        ON sr.year = sr1c.year
                       AND sr.number = sr1c.number
WHERE sr.id IS NULL
  AND sr1c.project_id IS NOT NULL
  AND sr1c.drawings_id IS NULL;



-- добавим id заявки  в supply_requests_1c
UPDATE supply_requests_1c sr1c
SET supply_requests_id = sr.id
FROM supply_requests sr
WHERE sr1c.number = sr.number
--   AND EXTRACT(YEAR FROM sr.supply_request_date) = sr1c.year;
    AND  sr.year = sr1c.year;

-- обновляем данными заявки из 1с
UPDATE supply_requests sr
SET
    supply_request_date = sr1c.date,
    comment_1c = sr1c.comment,
    condition = sr1c.condition,
    drawing_1c = sr1c.drawing_string,
    reg_date = sr1c.reg_date,
    reg_number = sr1c.reg_number,
    status = sr1c.status,
    status_sign = sr1c.status_sign
FROM supply_requests_1c sr1c
WHERE sr.number = sr1c.number
--   AND EXTRACT(YEAR FROM sr.supply_request_date) = sr1c.year;
    AND  sr.year = sr1c.year;