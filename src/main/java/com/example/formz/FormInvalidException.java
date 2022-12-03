package com.example.formz;

public class FormInvalidException extends RuntimeException{
    FormInvalidException(String message){
        super(message);
    }
}
