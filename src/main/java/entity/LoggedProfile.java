package entity;

public class LoggedProfile {

	private final Profile profile;
	
	public LoggedProfile(Profile profile) {
		this.profile =  profile;
	}

	public boolean isAllowed(Long id){
		return this.profile.getId()==id;
	}

	public boolean isLoggedIn() {
		return profile != null && profile.getId() > 0;
	}

	public boolean isNotLoggedIn(){
		return !isLoggedIn();
	}

	
	public Profile getProfile(){
		return profile;
	}

	public long getProfileId(){
		return profile.getId();
	}
	
	public boolean isThePostAuthor(entity.Post post){
		 if (post==null || profile == null){
			 return false;
		 }
		return (profile.getId()==post.getCreatorID());
	}
	
	@Override
	public String toString() {
		return "[profile = " + profile.getName()  + " ,  id = " + profile.getId() + "]";
	}
}