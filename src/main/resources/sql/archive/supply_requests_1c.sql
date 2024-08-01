Update supply_requests_1c
SET drawing_string = REGEXP_REPLACE(drawing_string, E'[\\n\\r\\f\\b\\t\\v]+$', '');

Update supply_requests_1c
Set drawings_id = dr.id
from drawings dr
where supply_requests_1c.drawing_string = dr.code_normal;

-- заполним проект в заявках 1с
UPDATE supply_requests_1c sr
SET project_id = (
    SELECT z.projects_id
    FROM drawings d
             JOIN zones z ON d.zones_id = z.id
    WHERE d.id = sr.drawings_id
)
WHERE sr.drawings_id IS NOT NULL;

-- заполним проект в заявках
UPDATE supply_requests sr
SET projects_id = (
    SELECT z.projects_id
    FROM drawings d
             JOIN zones z ON d.zones_id = z.id
    WHERE d.id = sr.drawings_id
)
WHERE sr.drawings_id IS NOT NULL;
