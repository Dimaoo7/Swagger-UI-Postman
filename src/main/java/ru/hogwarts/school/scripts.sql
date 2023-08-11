SELECT * from student;

SELECT * from student
where age Between 20 and 40;

SELECT name from student;

SELECT * from student
where name like '%o%';

SELECT * from student
where age < id;


SELECT * from student
ORDER BY age;

/*
 Новая домашка
 */

SELECT COUNT(*) FROM student;

SELECT avg(age) FROM student;

SELECT * FROM student
OFFSET 5
