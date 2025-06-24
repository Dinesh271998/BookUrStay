package com.practice.BookUrStay.Annotation;

import com.practice.BookUrStay.Annotation.Class.validPassword;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = validPassword.class)
public @interface validPasswordAnnotation {
    String message() default "Invalid password!!! Please follow the password policy.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
