package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Course;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import com.example.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    //define field to entity manager
    private EntityManager entityManager;

    //inject the entity manager using constructor injection
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public void sava(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findById(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructor(int id) {
        Instructor instructor = entityManager.find(Instructor.class,id);
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class,id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetail(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class,id);

        //remove the associated object reference
        //break bi-directional link
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> getCourses(int id) {

        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id=:data",Course.class);
        query.setParameter("data",id);

        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor getInstructorByIdJoinFetch(int id) {

        //create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                                                "select i from Instructor i"
                                                    +" JOIN FETCH i.courses "
                                                        +" JOIN FETCH i.instructorDetail "
                                                        +"where i.id =:data",Instructor.class
        );
        query.setParameter("data",id);

        Instructor instructor = query.getSingleResult();

        return instructor;

    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void courseUpdate(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        Course course = entityManager.find(Course.class,id);
        return course;
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class,id);

        List<Course> courses = instructor.getCourses();

        for (Course course : courses){
            course.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class,id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCoursesAndReviewsById(int id) {

        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c "
                        +" join fetch c.reviews "
                        +"where c.id=:data",Course.class);

        query.setParameter("data",id);

        //execute query
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndStudentByCourseId(int id) {

        TypedQuery<Course> query = entityManager.createQuery("select c from Course c " +
                "join fetch c.students "+
                "where c.id=:data",Course.class);

        query.setParameter("data",id);

        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentAndCourseByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s "+
                                                "join fetch s.courses "+
                                                    "where s.id=:data",Student.class);
        query.setParameter("data",id);

        Student student = query.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student = entityManager.find(Student.class,id);

        entityManager.remove(student);
    }
}
