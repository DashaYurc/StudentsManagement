package com.company;

import com.company.controllers.StudentController;
import com.company.login.Login;

public class Main {
    public static void main(String[] args) {

        //ternary operator

        //System.out.println(StudentController.addNewStudent() ? "Successfully added new student" : "Failed to add new student");

        //System.out.println("The student is: "+ StudentController.getStudentById().getName());
        //System.out.println(Login.signUp() ? "Successfully signed up" : Login.signUp());

        //System.out.println(Login.login() ? "Successfully logged in" : "Failed to login in");
        //StudentController.addStudentScore();

        //StudentController.removeStudent();
        //StudentController.removeStudentScore();
        //StudentController.editStudentScore();
        StudentController.editStudentDetails();


    }
}
