package interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.BeforeCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import entity.LoggedProfile;

@Intercepts
public class LoggedUserInterceptor {
	
	@Inject private Result result;
	@Inject private LoggedProfile loggedUser;

	@BeforeCall
	public void intercept() {
		result.include("loggedUser", loggedUser);
	}
}