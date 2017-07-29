package rule;

import java.sql.SQLException;

import javax.inject.Inject;

import br.com.caelum.brutauth.auth.annotations.HandledBy;
import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;
import dao.PostDAO;
import entity.LoggedProfile;
import handler.CanEditUserHandler;

@HandledBy(CanEditUserHandler.class)
public class UserIsthePostAuthorRule implements CustomBrutauthRule  {


		@Inject private LoggedProfile loggedUser;
		@Inject private PostDAO dao;

		public boolean isAllowed(Long id){
			try {
				return  loggedUser.isThePostAuthor(dao.searchById(id));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
}