package com.student.library.management.exceptions;

public class StudentNotFoundException extends Exception{

    public StudentNotFoundException( ) {
        super("No such student ");
    }
}
