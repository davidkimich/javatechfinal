package com.csis231.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id", nullable = false)
    private Long id;
    @NotEmpty(message = "First name cannot be empty")
    @Column(name="employee_first_name", nullable = false)
    private String firstName;
    @Column(name="employee_last_name")
    private String lastName;
    @Column(name = "employee_email")
    private String emailId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString()
     {
        return "Employee [id=" +id+", firstName=" +firstName+ "lastName=" +lastName+ "Email=" +emailId+ "]";
    }


}
