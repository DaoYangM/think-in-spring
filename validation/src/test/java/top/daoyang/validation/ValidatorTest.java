package top.daoyang.validation;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * {@link org.springframework.validation.Validator} 测试
 */
public class ValidatorTest {

    private static final int PASSWORD_MIN_LENGTH = 6;

    @Test
    public void testPassword() {
        UserLoginValidator userLoginValidator = new UserLoginValidator();

        UserLogin userLogin = new UserLogin();
        userLogin.setPassword("12");

        Errors errors = new BeanPropertyBindingResult(userLogin, "userLogin");

        if (userLoginValidator.supports(UserLogin.class)) {
            userLoginValidator.validate(userLogin, errors);
        }

        Assertions.assertTrue(errors.hasErrors());

        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(System.out::println);
        }
    }

    @Data
    private static class UserLogin {
        private String userName;

        private String password;
    }

    private static class UserLoginValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return UserLogin.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "field.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");

            UserLogin login = (UserLogin) target;

            if (login.getPassword() != null
                && login.getPassword().trim().length() < PASSWORD_MIN_LENGTH) {
                errors.rejectValue("password", "field.min.length",
                        new Object[] {PASSWORD_MIN_LENGTH}, "The password must be at least [" + PASSWORD_MIN_LENGTH
                + "] characters in length.");
            }
        }
    }
}
