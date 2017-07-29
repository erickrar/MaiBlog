package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import dao.filter.Filter;
import dao.filter.PostFilter;
import entity.Category;
import entity.Post;
import entity.Profile;


public class PostDAO extends GenericDAO {

	private String sqlInsert(){
		return "INSERT INTO post ( title, description,post,cover,id_category,id_author,created_date,last_update) " +
				" values (?,?,?,?,?,?,?,?) ";		
	}

	private String sqlUpdate(){
		return "UPDATE post set title = ?, description = ?, post = ? ,cover = ? ,id_category = ?,last_update= ? WHERE ID = ? ";
	}

	private String sqlDelete(){
		return "DELETE FROM post WHERE ID = ? ";
	}

	private String sqlSelect(){
		return "SELECT id, title, description,post,cover,category,author,created_date,last_update FROM post WHERE 1=1 ";
	}

	private String sqlSelectFullPost(){
		return "SELECT p.id post_id , p.title post_title , p.description post_desc ,p.post post_post ,p.cover post_cover ,"
				+ "c.name cat_name, c.id cat_id, p.created_date created_date ,p.last_update last_update, "
				+ " a.name author_name , a.photo  author_photo ,a.id author_id   "
				+ " FROM post p INNER JOIN profile a ON p.id_author = a.id "
				+ " LEFT JOIN category c ON c.id = p.id_category "
				+ " WHERE 1=1 ";
	}

	public void insert(Post post) throws SQLException {

		pstm = getStatement(sqlInsert());
		pstm.setString(1, post.getTitle());
		pstm.setString(2, post.getDescription());
		pstm.setString(3, post.getPost());
		pstm.setString(4, post.getCover());
		if(post.getCategory() != null || post.getCategory().getId() > 0)
			pstm.setLong(5, post.getCategory().getId());
		else
			pstm.setObject(5, null);
		pstm.setLong(6, post.getAuthor().getId());
		pstm.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
		pstm.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
		pstm.executeUpdate();
		close();
	}

	public void update(Post post) throws SQLException{
		pstm = getStatement(sqlUpdate());
		pstm.setString(1, post.getTitle());
		pstm.setString(2, post.getDescription());
		pstm.setString(3, post.getPost());
		pstm.setString(4, post.getCover());
		if(post.getCategory() != null || post.getCategory().getId() > 0)
			pstm.setLong(5, post.getCategory().getId());
		else
			pstm.setObject(5, null);
		pstm.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
		pstm.setLong(7, post.getId());
		pstm.executeUpdate();
		close();
	}

	public void delete(Post post) throws SQLException{
		pstm = getStatement(sqlDelete());
		pstm.setLong(1, post.getId());
		pstm.executeUpdate();
		close();
	}

	public Post searchById(long id) throws SQLException {
		PostFilter pf = new PostFilter();
		pf.byId(id);
		return search(pf).get(0);
	}


	public List<Post> search(Filter filter) throws SQLException{
		rs = search(filter, sqlSelectFullPost());
		return resolveResultSet();
	}

	public List<Post> listAll() throws SQLException {
		rs =  search( sqlSelectFullPost() + "ORDER BY created_date DESC");
		return resolveResultSet();
	}

	private List<Post> resolveResultSet() throws SQLException{
		List<Post> posts = new ArrayList<Post>();

		while(rs.next()){

			Profile author = new Profile();
			author.setId(rs.getLong("author_id"));
			author.setName(rs.getString("author_name"));
			author.setPhoto(rs.getString("author_photo")); 

			Category c = new Category();
			c.setName(rs.getString("cat_name"));

			Post p = new Post();
			p.setAuthor(author);
			p.setCategory(c);
			p.setId(rs.getLong("post_id"));
			p.setPost(rs.getString("post_post"));
			p.setDescription(rs.getString("post_desc"));
			p.setCover(rs.getString("post_cover"));
			p.setTitle(rs.getString("post_title"));
			p.setCreatedDate(new Date(rs.getDate("created_date").getTime()));
			posts.add(p);
		}

		return posts;

	}
}