package auth;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import entity.LoggedProfile;
import entity.Profile;

@SessionScoped
public class SystemAccess implements Serializable{

	private static final long serialVersionUID = 7243963239678423858L;
	private Profile profile;
	
	public Profile login(Profile profile) {
		this.profile = profile;
		return profile;
	}
	
	public void logout(){
		this.profile =null;
	}
	
	@Produces
	public LoggedProfile getInstance() {
		 return new LoggedProfile(profile);
	}
}