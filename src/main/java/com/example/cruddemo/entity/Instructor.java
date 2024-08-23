package com.example.cruddemo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {


    //annotate the class as an entity and map to db table

    //define the fields

    //annotate the fields to db columns names

    //set up mapping between instructor and instructor detail

    //create constructor

    //create getter/setter

    //generate toString method


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    /* eager VS lazy

    in eager its return the all data and its default parameter in{many to one,one to one} relationships

    in lazy its return the data on demand and its default parameter in (one to many , many to many) relationships

     */
    //in this case the default parameter is lazy and when we try to get the instructor courses its give us error
    //So we have two solution
    //first : we can just change the FetchType parameter to eager
    /*second : we let the default parameter
     and create a new method in AppDAOImpl class to get All courses to the current instructor
     and update it in the main app*/
    @OneToMany(
            mappedBy = "instructor",
//            fetch = FetchType.EAGER, comment it because we will use the second solution
            cascade = {CascadeType.DETACH,CascadeType.PERSIST,
                    CascadeType.MERGE,CascadeType.REFRESH})
    private List<Course> courses;

    public Instructor(){}

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    //add convenience method for bi-directional relationship
    public void add(Course course){
        if(courses==null){
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.setInstructor(this);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }
}
