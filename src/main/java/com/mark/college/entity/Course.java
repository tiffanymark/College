package com.mark.college.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Tiffany Mark on 04/07/2016.
 */

@Entity
@Table(name = "course")
public class Course {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Student> students;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Subject> subjects;

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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
