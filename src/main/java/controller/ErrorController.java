package controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import util.FastI18nMessage;

@Controller
public class ErrorController {

	@Inject private Result result;
	
	public void errorpage(){
		result.include("errorPageTitle", FastI18nMessage.getMessage("error.noerror.pagetitle"));
		result.include("errorPageDescription",FastI18nMessage.getMessage("error.noerror.pagedescription"));
		result.include("errorPageMessage",FastI18nMessage.getMessage("error.noerror.pagemessage"));
	}
}
