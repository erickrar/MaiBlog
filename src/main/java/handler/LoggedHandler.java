package handler;

import javax.inject.Inject;

import br.com.caelum.brutauth.auth.handlers.RuleHandler;
import br.com.caelum.vraptor.Result;
import controller.ErrorController;
import util.FastI18nMessage;

public class LoggedHandler implements RuleHandler {

	@Inject  private Result result;

	@Override
	public void handle() {
		result.include("errorPageTitle", FastI18nMessage.getMessage("error.need.login"));
		result.include("errorPageDescription",FastI18nMessage.getMessage("error.need.login.desc"));
		result.include("errorPageMessage",FastI18nMessage.getMessage("error.need.login.desc"));
		result.of(ErrorController.class).errorpage();
	}
}