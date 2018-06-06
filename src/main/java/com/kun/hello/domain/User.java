package com.kun.hello.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="USER")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "created")
    private Date created;

    public Long getId() { return this.id; }

    public void setFirstName(String fname) {
        this.firstName = fname;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lname) {
        this.lastName = lname;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setUserId(String uid) {
        this.userId = uid;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setCreated() {
        this.created = new Date();
    }

    public Date getCreated() {
        return this.created;
    }

    public User() {

    }

    public User(String fname, String lname, String uid) {
        this.firstName = fname;
        this.lastName = lname;
        this.userId = uid;
        this.setCreated();
    }

    public String toString() {
        StringBuilder builder= new StringBuilder();
        builder.append("first name: " + this.firstName).append("\n");
        builder.append("last name: " + this.lastName).append("\n");
        builder.append("user id: " + this.userId).append("\n");
        builder.append("created on: " + this.created).append("\n");
        return builder.toString();
    }

    public static User getDummyUser() {
        User foo = new User("john", "foo", "jf");
        return foo;
    }
    public static void main(String... args) {
        User dummy = getDummyUser();
        System.out.println(dummy.toString());
    }
}
