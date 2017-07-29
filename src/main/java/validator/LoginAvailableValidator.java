package validator;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import dao.ProfileDAO;
import entity.Profile;
import validator.interfaces.LoginAvailable;

public class LoginAvailableValidator implements ConstraintValidator<LoginAvailable, Profile> {

	@Inject private ProfileDAO users;
			@SuppressWarnings("unused")
			private LoginAvailable loginAvaliable; 

	public boolean isValid(Profile user, ConstraintValidatorContext context) {
		try {
			return !users.containsUserWithLogin(user.getLogin());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void initialize(LoginAvailable loginAvaliable) {
		this.loginAvaliable = loginAvaliable;
	}
}