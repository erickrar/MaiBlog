package controller;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;

import auth.SystemAccess;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import dao.ProfileDAO;
import entity.LoggedProfile;
import entity.Profile;
import util.FastI18nMessage;
import util.Strings;

@Controller
public class ProfileController {


	@Inject private ProfileDAO users;
	@Inject private Result result;
	@Inject private Validator validator;
	@Inject private Profile profile;
	@Inject	private SystemAccess system;
	@Inject private LoggedProfile loggedUser;

	private static final Logger LOG = Logger.getLogger(ProfileController.class);

	@Path({"cadastrar","signup"})
	public void signup(){
	}

	@Path({"entrar","signin"})
	public void signin(){
	}

	@Path({"relembrar-senha","restore"})
	public void restore(){
	}

	@Post
	public void insert(Profile user,@NotEmpty String password,@NotEmpty String confirmPassword){
		try {
			user.addPassword(password);
			users.insert(user);

			LOG.info("New user!");
			LOG.info("-------------START----------------");
			LOG.info(String.format("User id: %s", user.getId()));
			LOG.info(String.format("User name: %s", user.getName()));
			LOG.info(String.format("User email: %s", user.getEmail()));
			LOG.info(String.format("User login: %s", user.getLogin()));
			LOG.info("-------------END----------------");
			result.include("messages",FastI18nMessage.getMessage("user.success.insert"));
			result.redirectTo(IndexController.class).index();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			result.redirectTo(ErrorController.class).errorpage();
		}
	}

	@Post
	public void update(Profile user,@NotEmpty String password,String confirmPassword){
		try {
		validator.addIf(!password.equals(confirmPassword),new I18nMessage("password", "error.confirm.password"));
		user.addPassword(password);
		validator.validate(user);
	
			validator.addIf(users.containsUserWithLogin(user.getLogin())
					,new I18nMessage("login", "erro.already.exists.login"));
		users.update(user);


		LOG.info(String.format("LoggedUser #%s updated user #%s", loggedUser.getProfileId(), user.getId()));
		LOG.info("-------------START----------------");
		LOG.info(String.format("New user name: %s", user.getName()));
		LOG.info(String.format("New user login: %s", user.getLogin()));
		LOG.info(String.format("New user email: %s", user.getEmail()));
		LOG.info("-------------END----------------");

		result.include("messages", new I18nMessage("", "site.welcome"));
		result.redirectTo(IndexController.class).index();
		} catch (Exception e) {
			e.printStackTrace();
			result.redirectTo(ErrorController.class).errorpage();
		}
	}
	 
	@Post
	public void signIn(@NotEmpty String login,@NotEmpty String password, String redirectUrl){
		try {
			profile= users.signIn(login,Strings.criptography(password));
			validator.addIf(profile==null, new I18nMessage("", "error.usernotfound"));
			validator.onErrorRedirectTo(this).signin();
			system.login(profile);
			result.include("loggedUser", new LoggedProfile(profile));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (Strings.isNotBlank(redirectUrl)){
			result.redirectTo(redirectUrl);
		}else{
			result.redirectTo(IndexController.class).index();
		}
	}

	@Get
	public void signOut(String redirectUrl){
		system.logout();
		result.use(Results.referer()).redirect();
	}

}