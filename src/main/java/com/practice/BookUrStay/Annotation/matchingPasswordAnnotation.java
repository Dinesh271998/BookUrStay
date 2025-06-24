// package com.practice.BookUrStay.Annotation;

// import jakarta.validation.Constraint;
// import jakarta.validation.Payload;
// import java.lang.annotation.*;

// @Documented
// @Constraint(validatedBy = com.practice.BookUrStay.Annotation.Class.matchingPassword.class)
// @Target({ElementType.FIELD})
// @Retention(RetentionPolicy.RUNTIME)
// public @interface matchingPasswordAnnotation {
//     String message() default "Passwords do not match!";
//     Class<?>[] groups() default {};
//     Class<? extends Payload>[] payload() default {};
//     String password() default "password";
//     String confirmPassword() default "confirmPassword";
// }