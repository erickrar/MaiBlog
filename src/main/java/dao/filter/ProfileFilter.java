package dao.filter;


public class ProfileFilter extends Filter {


	public void byEmail(String email){
		addColumn(" AND upper(email) = ?");
		addParameter(email.toUpperCase());
	}

	public void byName(String name){
		addColumn(" AND name = ? ");
		addParameter(name);
	}
	
	public void login(String login, String password){
		addColumn(" AND (email = ? OR login = ? )");
		addParameter(login);
		addParameter(login);
		
		addColumn(" AND password = ? ");
		addParameter(password);
	}
}
