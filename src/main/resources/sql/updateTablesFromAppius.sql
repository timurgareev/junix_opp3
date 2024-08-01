-- добавим проекты которых нет
INSERT INTO projects (name)
SELECT DISTINCT a.project_string
FROM appius a
WHERE NOT EXISTS (
    SELECT 1
    FROM projects p
    WHERE p.name = a.project_string
);

UPDATE appius
SET podobject = REPLACE(podobject, 'Блок ', '')
WHERE podobject LIKE 'Блок %'
  AND position('Блок ' in podobject) = 1;


-- редактируем наименование подобъекта
SELECT
    podobject,
    nomer_poz_po_gp,
    TRIM(
            REPLACE(
                    podobject,
                    nomer_poz_po_gp,
                    ''
            )
    ) AS modified_podobject
FROM appius
WHERE podobject LIKE nomer_poz_po_gp || '%';


-- посомтреть что вставляем отсуствующие данные в таблицу Зоны
SELECT Distinct
    a.podobject AS fullname,
    a.nomer_poz_po_gp AS code,
    TRIM(
            REPLACE(
                    a.podobject,
                    a.nomer_poz_po_gp,
                    ''
            )
    ) AS name,
    0 AS ordernumber,
    p.id AS projects_id
FROM appius a
         LEFT JOIN projects p
                   ON a.project_string = p.name
         LEFT JOIN zones z
                   ON
--                        z.fullname = a.podobject
--                        AND
z.code = a.nomer_poz_po_gp
                       AND z.projects_id = p.id
WHERE z.fullname IS NULL
  AND z.code IS NULL
  AND z.projects_id IS NULL
  AND a.podobject LIKE a.nomer_poz_po_gp || '%';


-- Вставка уникальных записей в таблицу zones
INSERT INTO zones (fullname, code, name, ordernumber, projects_id)
SELECT distinct
    a.podobject AS fullname,
    a.nomer_poz_po_gp AS code,
    TRIM(
            REPLACE(
                    a.podobject,
                    a.nomer_poz_po_gp,
                    ''
            )
    ) AS name,
    0 AS ordernumber,
    p.id AS projects_id
FROM appius a
         LEFT JOIN projects p
                   ON a.project_string = p.name
         LEFT JOIN zones z
                   ON  z.code = a.nomer_poz_po_gp
                       AND z.projects_id = p.id
WHERE z.fullname IS NULL
  AND z.code IS NULL
  AND z.projects_id IS NULL
  AND a.podobject LIKE a.nomer_poz_po_gp || '%';

-- Вставка новых чертежей - посмотреть
SELECT DISTINCT
    a.shifr_n AS code_normal,
    a.naimenovanie AS name,
    z.id AS zones_id,
    0 AS state,
    NULL AS description,
    a.oboznachenie AS code_string,
    a.razdel_proekta AS mark_drawing_key,
    dm.id AS drawings_marks_id
FROM appius a
         LEFT JOIN projects p
                   ON a.project_string = p.name
         LEFT JOIN zones z
                   ON a.nomer_poz_po_gp = z.code
                       AND z.projects_id = p.id
         LEFT JOIN drawings_marks dm
                   ON a.razdel_proekta = dm.mark_drawing
WHERE NOT EXISTS (
    SELECT 1
    FROM drawings d
    WHERE d.code_normal = a.shifr_n
);

-- Вставка новых чертежей
INSERT INTO drawings (code_normal, name, zones_id, state, description, code_string, mark_drawing_key, drawings_marks_id)
SELECT DISTINCT
    a.shifr_n AS code_normal,
    a.naimenovanie AS name,
    z.id AS zones_id,
    0 AS state,
    NULL AS description,
    a.oboznachenie AS code_string,
    a.razdel_proekta AS mark_drawing_key,
    dm.id AS drawings_marks_id
FROM appius a
         LEFT JOIN projects p
                   ON a.project_string = p.name
         LEFT JOIN zones z
                   ON a.nomer_poz_po_gp = z.code
                       AND z.projects_id = p.id
         LEFT JOIN drawings_marks dm
                   ON a.razdel_proekta = dm.mark_drawing
WHERE NOT EXISTS (
    SELECT 1
    FROM drawings d
    WHERE d.code_normal = a.shifr_n
)
ON CONFLICT (code_normal) DO NOTHING;


-- Ревизии
-- Шаг 1: Добавление новых записей
INSERT INTO revisions (drawings_id, name, status, date_inbox, date_outbox, inproduction_date_system, comment2)
SELECT DISTINCT
    d.id AS drawings_id,
    a.rev_n AS name,
    a.sostoyanie AS status,
    MIN(a.data_vhodyashego_pisma) OVER (PARTITION BY a.shifr_n, a.rev_n) AS date_inbox,
    MAX(a.data_ishodyashego_pisma) OVER (PARTITION BY a.shifr_n, a.rev_n) AS date_outbox,
    MAX(a.data_vpr) OVER (PARTITION BY a.shifr_n, a.rev_n) AS inproduction_date_system,
    a.web_ssilka AS comment2
FROM appius a
         JOIN drawings d ON a.shifr_n = d.code_normal
         LEFT JOIN revisions r ON d.id = r.drawings_id AND a.rev_n = r.name
WHERE r.id IS NULL;

-- Шаг 2: Обновление существующих записей
-- Шаг 1: Добавление новых записей
INSERT INTO revisions (drawings_id, name, status, date_inbox, date_outbox, inproduction_date_system, comment2)
SELECT DISTINCT
    d.id AS drawings_id,
    a.rev_n AS name,
    a.sostoyanie AS status,
    MIN(a.data_vhodyashego_pisma) OVER (PARTITION BY a.shifr_n, a.rev_n) AS date_inbox,
    MAX(a.data_ishodyashego_pisma) OVER (PARTITION BY a.shifr_n, a.rev_n) AS date_outbox,
    MAX(a.data_vpr) OVER (PARTITION BY a.shifr_n, a.rev_n) AS inproduction_date_system,
    a.web_ssilka AS comment2
FROM appius a
         JOIN drawings d ON a.shifr_n = d.code_normal
         LEFT JOIN revisions r ON d.id = r.drawings_id AND a.rev_n = r.name
WHERE r.id IS NULL;

-- Шаг 2: Обновление существующих записей
UPDATE revisions r
SET
    status = a.status,
    date_inbox = a.min_date_inbox,
    date_outbox = a.max_date_outbox,
    inproduction_date_system = a.max_inproduction_date_system,
    comment2 = a.web_ssilka
FROM (
         SELECT DISTINCT
             d.id AS drawings_id,
             a.rev_n AS name,
             a.sostoyanie AS status,
             MIN(a.data_vhodyashego_pisma) OVER (PARTITION BY a.shifr_n, a.rev_n) AS min_date_inbox,
             MAX(a.data_ishodyashego_pisma) OVER (PARTITION BY a.shifr_n, a.rev_n) AS max_date_outbox,
             MAX(a.data_vpr) OVER (PARTITION BY a.shifr_n, a.rev_n) AS max_inproduction_date_system,
             a.web_ssilka AS web_ssilka
         FROM appius a
                  JOIN drawings d ON a.shifr_n = d.code_normal
     ) a
WHERE r.drawings_id = a.drawings_id AND r.name = a.name;

-- Шаг 3: Обновление поля rate_number
WITH ranked_revisions AS (
    SELECT
        id,
        drawings_id,
        date_inbox,
        ROW_NUMBER() OVER (PARTITION BY drawings_id ORDER BY date_inbox DESC) AS rank
    FROM revisions
)
UPDATE revisions r
SET rate_number = rr.rank
FROM ranked_revisions rr
WHERE r.id = rr.id;

-- Шаг 4: Установка поля is_latest
WITH latest_revisions AS (
    SELECT
        id,
        drawings_id,
        date_inbox,
        ROW_NUMBER() OVER (PARTITION BY drawings_id ORDER BY date_inbox DESC) AS rank
    FROM revisions
)
UPDATE revisions r
SET is_latest = (rr.rank = 1)
FROM latest_revisions rr
WHERE r.id = rr.id;

-- События вх письма
INSERT INTO events (created_date, event_date, is_system_created, name, drawings_id, event_types_id, revisions_id)
SELECT
    NOW() AS created_date,
    a.data_vhodyashego_pisma AS event_date,
    TRUE AS is_system_created,
    'Вх. письмо №' || a.nomer_vhodyashego_pisma AS name,
    d.id AS drawings_id,
    3 AS event_types_id,
    r.id AS revisions_id
FROM (
         SELECT DISTINCT shifr_n, rev_n, nomer_vhodyashego_pisma, data_vhodyashego_pisma
         FROM appius
         WHERE nomer_vhodyashego_pisma IS NOT NULL
     ) a
         JOIN drawings d ON a.shifr_n = d.code_normal
         JOIN revisions r ON a.rev_n = r.name AND d.id = r.drawings_id
         LEFT JOIN events e ON e.drawings_id = d.id AND e.event_date = a.data_vhodyashego_pisma AND e.name = 'Вх. письмо №' || a.nomer_vhodyashego_pisma
WHERE e.id IS NULL
  AND NOT EXISTS (
    SELECT 1
    FROM events e2
    WHERE e2.name = 'Вх. письмо №' || a.nomer_vhodyashego_pisma
      AND e2.drawings_id = d.id
      AND e2.event_date = a.data_vhodyashego_pisma
);

-- События исх письма
INSERT INTO events (created_date, event_date, is_system_created, name, drawings_id, event_types_id, revisions_id)
SELECT
    NOW() AS created_date,
    a.data_ishodyashego_pisma AS event_date,
    TRUE AS is_system_created,
    'Исх. письмо №' || a.nomer_ishodyashego_pisma AS name,
    d.id AS drawings_id,
    4 AS event_types_id,
    r.id AS revisions_id
FROM (
         SELECT DISTINCT shifr_n, rev_n, nomer_ishodyashego_pisma, data_ishodyashego_pisma
         FROM appius
         WHERE nomer_ishodyashego_pisma IS NOT NULL
     ) a
         JOIN drawings d ON a.shifr_n = d.code_normal
         JOIN revisions r ON a.rev_n = r.name AND d.id = r.drawings_id
         LEFT JOIN events e ON e.drawings_id = d.id AND e.event_date = a.data_ishodyashego_pisma AND e.name = 'Исх. письмо №' || a.nomer_ishodyashego_pisma
WHERE e.id IS NULL
  AND NOT EXISTS (
    SELECT 1
    FROM events e2
    WHERE e2.name = 'Вх. письмо №' || a.nomer_ishodyashego_pisma
      AND e2.drawings_id = d.id
      AND e2.event_date = a.data_ishodyashego_pisma
);

-- События ВПР
INSERT INTO events (created_date, event_date, is_system_created, name, drawings_id, event_types_id, revisions_id)
SELECT
    NOW() AS created_date,
    a.data_vpr AS event_date,
    TRUE AS is_system_created,
    'Выдан ВПР рев. ' || a.rev_n AS name,
    d.id AS drawings_id,
    1 AS event_types_id,
    r.id AS revisions_id
FROM (
         SELECT DISTINCT shifr_n, rev_n, data_vpr
         FROM appius
         WHERE data_vpr IS NOT NULL
     ) a
         JOIN drawings d ON a.shifr_n = d.code_normal
         JOIN revisions r ON a.rev_n = r.name AND d.id = r.drawings_id
         LEFT JOIN events e ON e.drawings_id = d.id AND e.event_date = a.data_vpr AND e.name = 'Выдан ВПР рев. ' || a.rev_n
WHERE e.id IS NULL
  AND NOT EXISTS (
    SELECT 1
    FROM events e2
    WHERE e2.name = 'Выдан ВПР рев. ' || a.rev_n
      AND e2.drawings_id = d.id
      AND e2.event_date = a.data_vpr
);


