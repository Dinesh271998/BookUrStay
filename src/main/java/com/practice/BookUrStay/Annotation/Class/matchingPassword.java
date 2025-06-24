// package com.practice.BookUrStay.Annotation.Class;

// import com.practice.BookUrStay.Annotation.matchingPasswordAnnotation;
// import jakarta.validation.ConstraintValidator;
// import jakarta.validation.ConstraintValidatorContext;
// import org.springframework.beans.BeanWrapperImpl;

// public class matchingPassword implements ConstraintValidator<matchingPasswordAnnotation, Object> {
//     private String passwordField;
//     private String confirmPasswordField;

//     @Override
//     public void initialize(matchingPasswordAnnotation constraintAnnotation) {
//         this.passwordField = constraintAnnotation.password();
//         this.confirmPasswordField = constraintAnnotation.confirmPassword();
//     }

//     @Override
//     public boolean isValid(Object value, ConstraintValidatorContext context) {
//         Object password = new BeanWrapperImpl(value).getPropertyValue(passwordField);
//         Object confirmPassword = new BeanWrapperImpl(value).getPropertyValue(confirmPasswordField);

//         boolean valid = password != null && password.equals(confirmPassword);

//         if (!valid) {
//             context.disableDefaultConstraintViolation();
//             context.buildConstraintViolationWithTemplate("Passwords do not match!")
//                     .addPropertyNode(confirmPasswordField)
//                     .addConstraintViolation();
//         }
//         return valid;
//     }
// }