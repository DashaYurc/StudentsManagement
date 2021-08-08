package com.company.controllers;

import com.company.dbhelper.DbConnection;
import com.company.objects.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentController {

    //initioilize the scanner
    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean addNewStudent() {
        //prompt the user for data
        System.out.println("Enter the name of the student:");
        String name = scanner.next();

        System.out.println("Enter the age of the student: ");
        int age = scanner.nextInt();


        try {

            ps = DbConnection.getConnection().prepareStatement("INSERT INTO students(name, age) " +
                    " VALUES('" + name + "', " + age + ")");

            ps.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public static Student getStudentById() {

        //prompt the user to enter the id of the student they want to retrieve
        System.out.println("Enter the id of the student: ");
        int id = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM students WHERE id =" + id);
            rs = ps.executeQuery();

            Student student = new Student();
            int studentId, age;
            String name;

            while (rs.next()) {
                studentId = rs.getInt("id");
                name = rs.getString("name");
                age = rs.getInt("age");
                student.setId(studentId);
                student.setName(name);
                student.setAge(age);
            }
            return student;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void addStudentScore() {
        System.out.println("Enter the student's id: ");
        int id = scanner.nextInt();

        System.out.println("Enter the mathematics score: ");
        int mathScore = scanner.nextInt();

        System.out.println("Enter the english score: ");
        int engScore = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO scores(Mathematics, English, student_id) VALUES(" + mathScore +
                    ", " + engScore + ", " + id + ")");
            ps.execute();
            System.out.println("Successfully added score.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void removeStudent() {
        System.out.println("Enter the id of the student you want to remove: ");
        int id = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("DELETE FROM students WHERE id = " + id);
            ps.execute();
            System.out.println("The student has been removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void removeStudentScore() {
        System.out.println("Enter the id of the student you want to remove score: ");
        int id = scanner.nextInt();

        System.out.println("Press 1 to delete Mathematics score and press 2 to delete English score: ");
        int selectedSubject = scanner.nextInt();

        if (selectedSubject == 1) {
            try {
                ps = DbConnection.getConnection().prepareStatement("UPDATE scores SET Mathematics = NULL " +
                        "WHERE student_id = " + id);
                ps.execute();
                System.out.println("The Mathematics score removed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ps = DbConnection.getConnection().prepareStatement("UPDATE scores SET English = NULL " +
                        "WHERE student_id = " + id);
                ps.execute();
                System.out.println("The English score removed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void editStudentScore() {

        System.out.println("Enter the id of the student you want to edit score: ");
        int id = scanner.nextInt();

        System.out.println("Press 1 to edit Mathematics score and press 2 to edit English score: ");
        int selectedSubject = scanner.nextInt();

        System.out.println("With what score to replace previous one? ");
        int newScore = scanner.nextInt();

        if (selectedSubject == 1) {
            try {
                ps = DbConnection.getConnection().prepareStatement("UPDATE scores SET Mathematics = " + newScore +
                        " WHERE student_id = " + id);
                ps.execute();
                System.out.println("The Mathematics score has been changed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                ps = DbConnection.getConnection().prepareStatement("UPDATE scores SET English = " + newScore +
                        " WHERE student_id = " + id);
                ps.execute();
                System.out.println("The English score has been changed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void editStudentDetails() {
        System.out.println("Enter the id of the student you want to edit details: ");
        int id = scanner.nextInt();

        System.out.println("Press 1 if you want to change name or press 2 if you want to change age: ");
        int selectedEditField = scanner.nextInt();


        if (selectedEditField == 1) {
            System.out.println("Enter new student's name:");
            String newName = scanner.next();
            try {
                ps = DbConnection.getConnection().prepareStatement("UPDATE students SET name = '" + newName +
                        "' WHERE id = " + id);
                ps.execute();
                System.out.println("The name has been changed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Enter new student's age:");
            int newAge = scanner.nextInt();
            try {
                ps = DbConnection.getConnection().prepareStatement("UPDATE students SET age = " + newAge +
                        " WHERE id = " + id);
                ps.execute();
                System.out.println("The age has been changed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
