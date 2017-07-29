package rule;

import javax.inject.Inject;

import br.com.caelum.brutauth.auth.annotations.HandledBy;
import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;
import entity.LoggedProfile;
import handler.LoggedHandler;

@HandledBy(LoggedHandler.class)
public class LoggedRule implements CustomBrutauthRule  {

		@Inject private LoggedProfile loggedProfile;

		public boolean isAllowed(){
			return loggedProfile.isLoggedIn();
		}
}
