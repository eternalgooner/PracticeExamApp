package com.apptrumps.practiceexamapp.model;

/**
 * Created by David on 03-Sep-17.
 */

public class Person {
    private String name;
    private int age;
    private String height;
    private int phoneNumber;

    public Person(String name, int age, String height, int phoneNumber) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
