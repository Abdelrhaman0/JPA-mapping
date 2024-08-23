package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import com.example.cruddemo.entity.Student;

import java.sql.Struct;
import java.util.List;

public interface AppDAO {

    void sava(Instructor theInstructor);

    Instructor findById(int id);

    void deleteInstructor(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetail(int id);

    List<Course> getCourses(int id);

    Instructor getInstructorByIdJoinFetch(int id);

    void update(Instructor instructor);

    void courseUpdate(Course course);

    Course findCourseById(int id);

    void deleteInstructorById(int id);

    void deleteCourseById(int id);

    void save(Course course);

    Course findCoursesAndReviewsById(int id);

    Course findCourseAndStudentByCourseId(int id);

    Student findStudentAndCourseByStudentId(int id);

    void update(Student student);

    void deleteStudentById(int id);
}
