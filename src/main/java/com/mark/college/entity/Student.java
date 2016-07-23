package com.mark.college.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Tiffany Mark on 27/06/2016.
 */

@Entity
@Table(name = "student")
public class Student implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Grade> grades;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Absence> absences;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<StudentSubject> studentSubjects;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Notification> notifications;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public Set<Absence> getAbsences() {
        return absences;
    }

    public void setAbsences(Set<Absence> absences) {
        this.absences = absences;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<StudentSubject> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(Set<StudentSubject> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }
}
