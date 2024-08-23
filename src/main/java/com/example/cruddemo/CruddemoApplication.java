package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {

//			createCourseAndStudent(appDAO);
//			findCourseAndStudent(appDAO);
//			findStudentAndCourse(appDAO);
//			addMoreCoursesForStudent(appDAO);
//			deleteCourseById(appDAO);
			deleteStudentById(appDAO);
		};
	}

	private void deleteStudentById(AppDAO appDAO) {
		int id = 1;

		System.out.println("deleting student with id : "+id);

		appDAO.deleteStudentById(id);
		System.out.println("BOOM!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int id = 1;

		Student student = appDAO.findStudentAndCourseByStudentId(id);

		//create more courses
		Course course1 = new Course("Algorithm");
		Course course2 = new Course("java script");

		student.addCourse(course1);
		student.addCourse(course2);

		System.out.println("the student : " + student);
		System.out.println("the courses : " + student.getCourses());
		System.out.println("BOOM!");
		appDAO.update(student);
	}

	private void findStudentAndCourse(AppDAO appDAO) {
		int id = 1;

		Student student = appDAO.findStudentAndCourseByStudentId(id);

		System.out.println("the student : " + student);
		System.out.println("the course : " + student.getCourses());
		System.out.println("BOOM!");
	}

	private void findCourseAndStudent(AppDAO appDAO) {
		int id = 10;

		Course course = appDAO.findCourseAndStudentByCourseId(id);

		System.out.println("the course : " + course);
		System.out.println("the student in this course : " + course.getStudents());
		System.out.println("BOOM!");
	}

	private void createCourseAndStudent(AppDAO appDAO) {
		Course course = new Course("DSA");
		Student student1 = new Student("Abdelrahman","Ibrahim","elsharkawyabdelrahman@gmail.com");
		course.addStudent(student1);

		System.out.println("Saving course : " + course);
		System.out.println("the student in this course : " + course.getStudents());

		appDAO.save(course);

		System.out.println("BOOM!");
	}


	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		System.out.println("delete course by id : "+id);

		appDAO.deleteCourseById(id);
		System.out.println("DONE");
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		System.out.println("getting the course with id : " +id);

		Course course = appDAO.findCoursesAndReviewsById(id);

		System.out.println("the course : " + course);
		System.out.println("the reviews about this course : "+course.getReviews());
		System.out.println("BOOM!");

	}

	private void createCourseAndReview(AppDAO appDAO) {
		//create course
		Course course = new Course("Algorithm");

		//add some review
		Review review1 = new Review("Gooooooooooood!");
		Review review2 = new Review("Very Nice");
		course.addReview(review1);
		course.addReview(review2);

		//save the course
		System.out.println("Saving course");
		System.out.println(course);
		System.out.println("the reviews about course : "+ course.getReviews());
		appDAO.save(course);
	}

	private void deleteCourseById(AppDAO appDAO) {
		int id = 13;
		System.out.println("deleting course with id : " + id );

		appDAO.deleteCourseById(id);
		System.out.println("DONE");
	}

	private void deleteInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		System.out.println("delete the instructor with id : " + id);

		appDAO.deleteInstructorById(id);
		System.out.println("DONE");
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 11;

		System.out.println("finding the course with id "+id);

		Course course = appDAO.findCourseById(id);

		System.out.println("update the course : " +course);

		course.setTitle("React");
		appDAO.courseUpdate(course);

		System.out.println("updated course : " + course);
		System.out.println("DONE");
	}

	private void updateInstructor(AppDAO appDAO) {
			int id = 1;
		System.out.println("finding the instructor with id : "+id);

		Instructor instructor = appDAO.findById(id);

		System.out.println("update the instructor : " + instructor);

		instructor.setLastName("gad");

		appDAO.update(instructor);

		System.out.println("updated instructor : " + instructor);
		System.out.println("DONE");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id =1;
		System.out.println("get the instructor with id : "+ id);

		Instructor instructor = appDAO.getInstructorByIdJoinFetch(id);

		System.out.println("the instructor : " + instructor);
		System.out.println("the courses : " + instructor.getCourses());
		System.out.println("DONE");


	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id =1;
		System.out.println("get the instructor with id : "+ id);

		Instructor instructor = appDAO.findById(id);
		System.out.println("the instructor : "+instructor);

		//find the courses using getCourses method
		List<Course> courseList = appDAO.getCourses(id);
		instructor.setCourses(courseList);
		System.out.println("courses : " + instructor.getCourses());
		System.out.println("DONE");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id =1;
		System.out.println("get the instructor with id : "+ id);

		Instructor instructor = appDAO.findById(id);
		System.out.println("the instructor : "+instructor);
		System.out.println("the instructor courses : "+ instructor.getCourses());
		System.out.println("DONE");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		Instructor instructor = new Instructor("muhammed","sameh","muhammedsameh123@gmail.com");

		InstructorDetail detail = new InstructorDetail("http://www.samohaaa.com","eating");

		instructor.setInstructorDetail(detail);

		//create some course
		Course course1 = new Course("Java");
		Course course2 = new Course("OOP");
		instructor.add(course1);
		instructor.add(course2);

		//save instructor
		//note this will also saving the courses
		//because the cascading.persist
		System.out.println("saving instructor : "+instructor);
		System.out.println("saving courses : " + instructor.getCourses());
		appDAO.sava(instructor);

	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 4 ;
		System.out.println("Deleting the Instructor Detail with id : "+id);
		appDAO.deleteInstructorDetail(id);
		System.out.println("DONE");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 3;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println("get the details of instructor with id : "+id+" Details : "+instructorDetail);
		System.out.println("the instructor : "+ instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("deleting the instructor with id : "+id);

		appDAO.deleteInstructor(id);
		System.out.println("DONE");
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 5;
		System.out.println("finding the Instructor by id : "+ id);
		Instructor instructor = appDAO.findById(id);

		System.out.println("Instructor : " +instructor);
		System.out.println("The Instructor Details : " + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

		//create instructor
		Instructor instructor = new Instructor("Abdrlarhman","Ibrahim","elsharkawyabdelrahman@gmail.com");

		//create instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("http://www.Robot_90.com","playing LOL");

		//associate the objects
		instructor.setInstructorDetail(instructorDetail);

		//save an instructor
		//
		//Note this will also save the details object
		//because of CascadeType.ALL
		//
		System.out.println("the instructor had saved : "+instructor);
		appDAO.sava(instructor);

		System.out.println("every thing is done");

	}
}
