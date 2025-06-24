package com.practice.BookUrStay.Annotation.Class;

import com.practice.BookUrStay.Annotation.validPasswordAnnotation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class validPassword implements ConstraintValidator<validPasswordAnnotation, String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        int smallCaps = 0;
        int capitalCaps = 0;
        int digits = 0;
        int specialChars = 0;
        for(char ch : password.toCharArray()) {
            if(Character.isLowerCase(ch)){
                smallCaps++;
            } else if(Character.isUpperCase(ch)) {
                capitalCaps++;
            } else if(Character.isDigit(ch)) {
                digits++;
            } else if("!@#$%^&*()-_=+[]{}|;:'\",.<>?/`~".indexOf(ch) >= 0) {
                specialChars++;
            }
        }

        return smallCaps >= 2 && capitalCaps >= 2 && digits >= 2 && specialChars >= 2 && password.length() >= 8;
    }
}
