package pl.coderslab.charity.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.UserRepository;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String customerEmail, ConstraintValidatorContext constraintValidatorContext) {
      return userRepository.findAll().stream().map(User::getEmail).noneMatch(customerEmail::equals);
    }

    @Override
    public void initialize(EmailValidation constraintAnnotation) {

    }
}
