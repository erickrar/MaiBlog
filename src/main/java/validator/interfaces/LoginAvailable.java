package validator.interfaces;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validator.LoginAvailableValidator;



@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { LoginAvailableValidator.class })
@Documented
public @interface LoginAvailable {

	String message() default "{erro.already.exists.login}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
