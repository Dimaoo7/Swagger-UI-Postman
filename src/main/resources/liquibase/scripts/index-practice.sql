-- liquibase formatted sql

-- changeset Dmitry:1
CREATE index search_idx_by_name ON faculty (name);
--drop index search_idx_by_name;

-- changeset Dmitry:2
CREATE index search_idx_by_name_color ON faculty (name, color);
--drop index search_idx_by_name_color;