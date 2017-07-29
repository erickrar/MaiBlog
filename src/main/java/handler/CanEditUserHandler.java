package handler;

import javax.inject.Inject;

import util.FastI18nMessage;
import br.com.caelum.brutauth.auth.handlers.RuleHandler;
import br.com.caelum.vraptor.Result;
import controller.IndexController;

public class CanEditUserHandler implements RuleHandler {

	@Inject  private Result result;
	
	@Override
    public void handle() {
        result.include("messages",FastI18nMessage.getMessage("error.denied"));
        result.redirectTo(IndexController.class).index();
    }
}