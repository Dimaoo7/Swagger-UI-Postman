CREATE TABLE person (
    id INTEGER NOT NULL primary key,
    name VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL,
    vehicle_owner  BOOLEAN
);

CREATE TABLE vehicle (
    id INTEGER NOT NULL,
    model VARCHAR(255) NOT NULL,
    brand VARCHAR(255) NOT NULL,
    cost money NOT NULL ,
    PRIMARY KEY (id)
);

ALTER TABLE person
Add COLUMN vehicle_id INTEGER;

ALTER TABLE vehicle
ADD constraint vehicle_owner unique (model, brand);

select student.name, student.age, faculty.name
from student
inner join faculty on student.faculty_id = faculty.id;

SELECT student.name, student.age, avatar.id
FROM student
INNER JOIN avatar ON student.id = avatar.student_id;


