CREATE TABLE IF NOT EXISTS groups_of_objects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    fullname VARCHAR(255),
    comment VARCHAR(255),
    ordernumber INTEGER
);

CREATE TABLE  IF NOT EXISTS units(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    code VARCHAR(10),
    id_1c VARCHAR(10)
);

CREATE TABLE  IF NOT EXISTS types_of_work(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    comment VARCHAR(255),
    fullname VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS disciplines(
    id SERIAL PRIMARY KEY,
    mark_name VARCHAR(255),
    mark_code VARCHAR(255),
    discipline_name VARCHAR(255),
    type_of_work_id INTEGER REFERENCES  types_of_work (id)
--     mark_code_normal VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS objects(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    fullname VARCHAR(255),
    comment VARCHAR(255),
    ordernumber INTEGER,
    group_of_objects_id INTEGER REFERENCES groups_of_objects (id)
);

CREATE TABLE  IF NOT EXISTS projects(
        id SERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL UNIQUE,
        fullname VARCHAR(255),
        code VARCHAR(255),
--         group INTEGER,
        objects_id INTEGER REFERENCES objects (id),
            primavera_project_id VARCHAR(255),
        primavera_project_base_id VARCHAR(255),
        declaration VARCHAR(1000)
--         type INTEGER
);
CREATE TABLE zones(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) ,
    fullname VARCHAR(255),
    code VARCHAR(255) NOT NULL UNIQUE,
    ordernumber INTEGER,
    declaration VARCHAR(1000),
    comment VARCHAR(255),
    projects_id INTEGER REFERENCES projects (id)
);

CREATE TABLE drawings(
    id BIGSERIAL PRIMARY KEY,
--     code_string VARCHAR(255) NOT NULL UNIQUE,
    code_normal VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255),
    disciplines_id INTEGER references  disciplines (id),
    zones_id INTEGER references zones (id),
    state INTEGER,
    description VARCHAR(255)
);

CREATE TABLE revisions(
    id BIGSERIAL PRIMARY KEY ,
    drawings_id INTEGER REFERENCES drawings (id),
    name VARCHAR(255) NOT NULL,
    status VARCHAR(255),
--     drawing_revision INTEGER,
    date_inbox DATE,
    date_outbox DATE,
    inProduction_date_system DATE,
    inProduction_date_manual DATE,
    rate_number INTEGER,
    is_latest BOOLEAN,
--     BOM INTEGER,
    Comment1 VARCHAR(255),
    Comment2 VARCHAR(255),
--     BOM_comment VARCHAR(255),
    id_Drawing_Rev VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS scopes(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    drawings_id BIGINT REFERENCES  drawings (id) NOT NULL,
    revisions_id BIGINT REFERENCES  revisions (id),
    type_of_work_id INTEGER REFERENCES types_of_work (id),
    unit_id INTEGER REFERENCES units (id),
    quantity DOUBLE PRECISION,
    comment VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS supply_requests(
    id BIGSERIAL PRIMARY KEY,
    drawings_id BIGINT REFERENCES drawings (id),
    revision_id INTEGER,
    date DATE,
    number INTEGER,
    reg_number VARCHAR(255),
    reg_date DATE,
    status INTEGER,
    condition INTEGER,
    author INTEGER,
    comment INTEGER
);CONSTRAINT scopes_drawings_id_foreign FOREIGN KEY(drawings_id) REFERENCES drawings(id);

Create table if not exists group_disciplines (
    id serial primary key,
    name varchar(255) not null unique
);

create table if not exists marks (
    name varchar(255),
    marks_key varchar(50) primary key,
    disciplines_id int references disciplines (id)
);

create table if not exists appius (
    id serial primary key,
    object varchar(255),
    podobject varchar(255),
    nomer_poz_po_gp varchar(50),
    oboznachenie varchar (100),
    razdel_proekta varchar(50),
    izm varchar(50),
    data_documenta date,
    naimenovanie varchar(255),
    sostoyanie varchar(150),
    planovaya_data_vipuska date,
    data_vhodyashego_pisma date,
    nomer_vhodyashego_pisma varchar(50),
    data_ishodyashego_pisma date,
    nomer_ishodyashego_pisma varchar(50),
    web_ssilka varchar(255),
    stadia_proektirovaniya varchar(100),
    vid_documenta varchar(100),
    data_vpr date
)