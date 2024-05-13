INSERT INTO drawings (code_normal, name, zones_id)
SELECT da.code_normal, da.name, z.id
FROM drawings_appius da
         JOIN zones z ON da.zones_string = z.code;


SELECT da.code_normal, da.name, da.zones_string, z.id
FROM drawings_appius da
         Left JOIN zones z ON da.zones_string = z.code;

UPDATE zones
SET code = REGEXP_REPLACE(code, E'[\\n\\r\\f\\b\\t\\v]+$', '');

UPDATE drawings_appius
SET zones_string = REGEXP_REPLACE(zones_string, E'[\\n\\r\\f\\b\\t\\v]+$', '');

SELECT code, LENGTH(code) FROM zones;

UPDATE drawings
SET code_normal = REGEXP_REPLACE(code_normal, E'[\\n\\r\\f\\b\\t\\v]+$', '');

UPDATE revisions_appius
SET drawings_id = REGEXP_REPLACE(drawings_id, E'[\\n\\r\\f\\b\\t\\v]+$', '');


SELECT da.code_normal, da.name, z.drawings_id
FROM drawings da
         Left JOIN revisions_appius z ON da.code_normal = z.drawings_id;

INSERT INTO revisions (drawings_id, name, status,date_inbox,date_outbox,inproduction_date_system,
                       rate_number,is_latest)
SELECT d.id, ra.name, ra.status, ra.date_inbox,ra.date_outbox,ra.inproduction_date_system,
       ra.rate_number,ra.is_latest
FROM revisions_appius ra
         JOIN drawings d ON d.code_normal = ra.drawings_id;

UPDATE drawings_appius
SET mark_drawing_string = REGEXP_REPLACE(mark_drawing_string, E'[\\n\\r\\f\\b\\t\\v]+$', '');

UPDATE drawings_marks
SET mark_drawing = REGEXP_REPLACE(mark_drawing, E'[\\n\\r\\f\\b\\t\\v]+$', '');

UPDATE drawings_marks
SET marks_key = REGEXP_REPLACE(marks_key, E'[\\n\\r\\f\\b\\t\\v]+$', '');

UPDATE drawings
SET mark_drawing_key = da.mark_drawing_string
FROM drawings_appius da
WHERE drawings.code_normal = da.code_normal;

UPDATE drawings
SET code_string = da.code_string
FROM drawings_appius da
WHERE drawings.code_normal = da.code_normal;
