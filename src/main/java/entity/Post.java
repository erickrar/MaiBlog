package entity;
import java.util.Date;
import java.util.List;

import util.Strings;


public class Post {


	private long id;
	private String title;
	private String description;
	private String post;
	private String cover;
	private Category category;
	private Profile author;
	private List<Tag> tag;
	private List<Comment> comment;
	private Date createdDate;
	private Date lastUpdate;
	private String sluggedTitle;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
		this.setSluggedTitle(Strings.toSlug(title));
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public Profile getAuthor() {
		return author;
	}
	public void setAuthor(Profile author) {
		this.author = author;
	}
	public List<Tag> getTag() {
		return tag;
	}
	public void setTag(List<Tag> tag) {
		this.tag = tag;
	}
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public String getSluggedTitle() {
		return sluggedTitle;
	}
	public void setSluggedTitle(String sluggedTitle) {
		this.sluggedTitle = sluggedTitle;
	}

	public long getCreatorID(){
		if(author == null){
			return 0;
		}
		return author.getId();
	}

}