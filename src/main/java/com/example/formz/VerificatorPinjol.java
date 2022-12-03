package com.example.formz;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

import com.example.formz.FormChecker.AgeVerification;
import com.example.formz.FormChecker.Calculate;
import com.example.formz.FormChecker.Income;
import com.example.formz.FormChecker.NotNull;

public class VerificatorPinjol {
    public String getFormInfo(Object object) throws Exception{
        this.checkIfUsingFormChecker(object);
        this.calculateValue(object);
        this.checkRestrictions(object);

        return formInfoString(object);
        
    }

    private String formInfoString(Object object) throws Exception {
        Class<?> clazz = object.getClass();
        StringBuilder sb = new StringBuilder();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String value = String.valueOf(field.get(object));
            sb.append(fieldName + ": " + value + " | ");
        }

        return sb.toString();
    }

    private void checkIfUsingFormChecker(Object object){
        if(Objects.isNull(object)){
            throw new FormInvalidException("Tidak ada form");
        }

        Class<?> clazz = object.getClass();
        if(!clazz.isAnnotationPresent(FormChecker.class)){
            throw new FormInvalidException("Form tidak menggunakan form checker");
        }
    }

    private void calculateValue(Object object) throws Exception{
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if(method.isAnnotationPresent(Calculate.class)){
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    private void checkRestrictions(Object object) throws Exception{
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if(field.isAnnotationPresent(AgeVerification.class)){
                AgeVerification ageVerification = field.getAnnotation(AgeVerification.class);
                
                int age = (int) field.get(object);
                if(age < ageVerification.min() || age > ageVerification.max()){
                    throw new FormInvalidException("Umur ga sesuai ketentuan");
                }
            }

            if(field.isAnnotationPresent(NotNull.class)){
                String word = (String) field.get(object);
                if(word.isEmpty()){
                    throw new FormInvalidException("Tidak boleh kosong");
                }
            }

            if(field.isAnnotationPresent(Income.class)){
                Income incomeAnnotation = field.getAnnotation(Income.class);

                int income = field.getInt(object);
                if(income < incomeAnnotation.min()){
                    throw new FormInvalidException("Income tidak cukup");
                }
            }
        }
    }
}
