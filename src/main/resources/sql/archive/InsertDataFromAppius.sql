Delete from appius where 1=1;

TRUNCATE TABLE appius RESTART IDENTITY;


INSERT INTO drawings (code_normal, name, zones_id)
SELECT da.code_normal, da.name, z.id
FROM appius_drawings da
         JOIN zones z ON da.zones_string = z.code;


SELECT da.code_normal, da.name, da.zones_string, z.id
FROM appius_drawings da
         Left JOIN zones z ON da.zones_string = z.code;

UPDATE zones
SET code = REGEXP_REPLACE(code, E'[\\n\\r\\f\\b\\t\\v]+$', '');

UPDATE appius_drawings
SET zones_string = REGEXP_REPLACE(zones_string, E'[\\n\\r\\f\\b\\t\\v]+$', '');

SELECT code, LENGTH(code) FROM zones;

UPDATE drawings
SET code_normal = REGEXP_REPLACE(code_normal, E'[\\n\\r\\f\\b\\t\\v]+$', '');

UPDATE appius_revisions
SET drawings_id = REGEXP_REPLACE(drawings_id, E'[\\n\\r\\f\\b\\t\\v]+$', '');


SELECT da.code_normal, da.name, z.drawings_id
FROM drawings da
         Left JOIN appius_revisions z ON da.code_normal = z.drawings_id;

INSERT INTO revisions (drawings_id, name, status,date_inbox,date_outbox,inproduction_date_system,
                       rate_number,is_latest)
SELECT d.id, ra.name, ra.status, ra.date_inbox,ra.date_outbox,ra.inproduction_date_system,
       ra.rate_number,ra.is_latest
FROM appius_revisions ra
         JOIN drawings d ON d.code_normal = ra.drawings_id;

UPDATE appius_drawings
SET mark_drawing_string = REGEXP_REPLACE(mark_drawing_string, E'[\\n\\r\\f\\b\\t\\v]+$', '');

UPDATE drawings_marks
SET mark_drawing = REGEXP_REPLACE(mark_drawing, E'[\\n\\r\\f\\b\\t\\v]+$', '');

UPDATE drawings_marks
SET marks_key = REGEXP_REPLACE(marks_key, E'[\\n\\r\\f\\b\\t\\v]+$', '');

UPDATE drawings
SET mark_drawing_key = da.mark_drawing_string
FROM appius_drawings da
WHERE drawings.code_normal = da.code_normal;

UPDATE drawings
SET code_string = da.code_string
FROM appius_drawings da
WHERE drawings.code_normal = da.code_normal;

-- ранжирование ревизий по убыванию
WITH ranked_revisions AS (
    SELECT
        id,
        drawings_id,
        date_inbox,
        ROW_NUMBER() OVER (PARTITION BY drawings_id ORDER BY date_inbox DESC) AS rank
    FROM
        appius_revisions
)
UPDATE appius_revisions ar
SET
    rate_number = rr.rank
FROM
    ranked_revisions rr
WHERE
    ar.id = rr.id;

WITH ranked_revisions AS (
    SELECT
        id,
        drawings_id,
        date_inbox,
        ROW_NUMBER() OVER (PARTITION BY drawings_id ORDER BY date_inbox desc ) AS rank
    FROM
        revisions
)
UPDATE revisions r
SET
    rate_number = rr.rank
FROM
    ranked_revisions rr
WHERE
    r.id = rr.id;



-- ранжирование по возрастанию
WITH ranked_revisions AS (
    SELECT
        id,
        drawings_id,
        date_inbox,
        ROW_NUMBER() OVER (PARTITION BY drawings_id ORDER BY date_inbox ASC) AS rank
    FROM
        appius_revisions
)
UPDATE appius_revisions ar
SET
    rate_number = rr.rank
FROM
    ranked_revisions rr
WHERE
    ar.id = rr.id;

WITH ranked_revisions AS (
    SELECT
        id,
        drawings_id,
        date_inbox,
        ROW_NUMBER() OVER (PARTITION BY drawings_id ORDER BY date_inbox asc ) AS rank
    FROM
        revisions
)
UPDATE revisions r
SET
    rate_number = rr.rank
FROM
    ranked_revisions rr
WHERE
    r.id = rr.id;

SELECT DISTINCT a.project_string, p.name
FROM appius a
         JOIN projects p ON a.project_string = p.name;

-- Сопоставленные значения
SELECT DISTINCT a.project_string, p.name
FROM appius a
         JOIN projects p ON a.project_string = p.name

UNION ALL

-- Несопоставленные значения из appius
SELECT a.project_string, NULL AS name
FROM appius a
         LEFT JOIN projects p ON a.project_string = p.name
WHERE p.name IS NULL

UNION ALL

-- Несопоставленные значения из projects
SELECT NULL AS project_string, p.name
FROM projects p
         LEFT JOIN appius a ON a.project_string = p.name
WHERE a.project_string IS NULL
