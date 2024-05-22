--liquibase formatted sql

--changeset admin:1
CREATE SEQUENCE teacher_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE student_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE student_teacher_mapping_seq START WITH 1 INCREMENT BY 1;


--changeset admin:2
CREATE TABLE teacher (
    ID NUMERIC(5,0) PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    address VARCHAR(255),
    principal NUMBER(1,0),
    subject VARCHAR(255),
    role VARCHAR(50));


--changeset admin:3
CREATE TABLE student (
    ID NUMERIC(5,0) PRIMARY KEY,
    name VARCHAR(255),
    section VARCHAR(255),
    roll_number VARCHAR(255));


--changeset admin:4
CREATE TABLE student_teacher_mapping (
    ID NUMERIC(5,0) PRIMARY KEY,
    student_name VARCHAR(255),
    student_id INT,
    teacher_name VARCHAR(255),
    teacher_id INT,
    section VARCHAR(255),
    subject VARCHAR(255),
    percentage VARCHAR(255))



























