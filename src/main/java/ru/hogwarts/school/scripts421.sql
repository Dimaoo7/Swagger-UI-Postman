ALTER TABLE student
ADD constraint age CHECK (age > 16);

ALTER TABLE student
ALTER COLUMN name SET NOT NULL;

ALTER TABLE student
ADD constraint name unique(name);

alter table faculty
add constraint name unique(name, color);

alter table student
alter column age set default 20;



