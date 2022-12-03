package com.example.formz;

import com.example.formz.FormChecker.AgeVerification;
import com.example.formz.FormChecker.Calculate;
import com.example.formz.FormChecker.Income;
import com.example.formz.FormChecker.NotNull;

@FormChecker
public class Pinjol {
    @NotNull private String name;

    @AgeVerification(max = 200) private int age;

    @Income(min = 100000) private int income;
    
    private int tax;

    public Pinjol(String name, int age, int income) {
        this.name = name;
        this.age = age;
        this.income = income;
    }

    @Calculate
    private void setTax(){
        this.tax = 12500;
    }
}
