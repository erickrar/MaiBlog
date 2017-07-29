package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.filter.Filter;
import dao.filter.ProfileFilter;
import entity.Profile;


public class ProfileDAO extends GenericDAO {

	private String sqlInsert(){
		return "INSERT INTO profile (name, email,login,photo,password, created_date) values (?,?,?,?,?,?) ";		
	}

	private String sqlUpdate(){
		return "UPDATE profile set name = ?, email = ?, login= ?, photo = ?, password=?  WHERE ID = ? ";
	}

	private String sqlDelete(){
		return "DELETE FROM profile  WHERE ID = ? ";
	}

	private String sqlSelect(){
		return "SELECT id, name,email,photo,login,created_date FROM profile WHERE 1=1 ";
	}


	public void insert(Profile profile) throws SQLException {
		pstm = getStatement(sqlInsert());
		pstm.setString(1, profile.getName());
		pstm.setString(2, profile.getEmail());
		pstm.setString(3, profile.getLogin());
		pstm.setString(4, profile.getPhoto());
		pstm.setString(5, profile.getPassword());
		pstm.setDate(6, new Date(System.currentTimeMillis()));
		pstm.executeUpdate();
		close();
	}

	public void update(Profile profile)  throws SQLException {
		pstm = getStatement(sqlUpdate());
		pstm.setString(1, profile.getName());
		pstm.setString(2, profile.getEmail());
		pstm.setString(3, profile.getLogin());
		pstm.setString(4, profile.getPhoto());
		pstm.setString(5, profile.getPassword());
		pstm.setLong(6, profile.getId());
		pstm.executeUpdate();
		close();
	}

	public void delete(Profile profile) throws SQLException{
		pstm = getStatement(sqlDelete());
		pstm.setLong(1, profile.getId());
		pstm.executeUpdate();
		close();
	}

	public Profile searchFromId(long id) throws SQLException {
		ProfileFilter pf = new ProfileFilter();
		pf.byId(id);
		return search(pf).get(0);
	}

	public Profile signIn(String user, String pass)  throws SQLException{
		ProfileFilter pf = new ProfileFilter();
		pf.login(user, pass);
		return search(pf).get(0);
	}

	public List<Profile> search(Filter filter)throws SQLException{
		rs = search(filter, sqlSelect());

		List<Profile> profiles = new  ArrayList<Profile>();
		while(rs.next()){
			Profile p = new Profile();
			p.setId(rs.getLong("id"));
			p.setLogin(rs.getString("login"));
			p.setName(rs.getString("name"));
			p.setEmail(rs.getString("email"));
			p.setPhoto(rs.getString("photo"));
			p.setCreatedDate(new Date(rs.getDate("created_date").getTime()));
			profiles.add(p);
		}
		close();
		return  profiles;
	}

	public boolean containsUserWithLogin(String login)throws SQLException{
		if (login == null)
			login = "";
		String sql = "SELECT 1 FROM profile where upper(login) = ?";
		pstm = getStatement(sql);
		pstm.setString(1, login.toUpperCase());
		rs = pstm.executeQuery();
		close();
		return rs.next();

	}
}