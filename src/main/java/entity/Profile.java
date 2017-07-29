package entity;
import java.util.Date;
import java.util.List;


public class Profile {

	
	private long id;
	private String name;
	private String login;
	private String email;
	private String password;
	private Date createdDate;
	private String photo;
	private List<Post> posts;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	public String getPassword() {
		return password;
	}
	private void setPassword(String password) {
		this.password = password;
	}
	
	public void addPassword(String password){
		this.setPassword(util.Strings.criptography(password));
	}	
}