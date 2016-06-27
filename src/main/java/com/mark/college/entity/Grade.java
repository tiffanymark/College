package com.mark.college.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tiffany Mark on 27/06/2016.
 */

@Entity
@Table(name = "grade")
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "subject_name")
    private String subject;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Column(name = "average")
    private double average;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
