-- liquibase formatted sql

-- changeset Dmitry:1
DROP index search_idx_by_name;
CREATE index search_idx_by_name ON student (name);

-- changeset Dmitry:2
drop index search_idx_by_name_color;
CREATE index search_idx_by_name_color ON faculty (name, color);

