package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.Comment;
import entity.Post;

public class CommentDAO extends GenericDAO {

	private String sqlInsert(){
		return "INSERT INTO comment (author_name, comment, created_date, id_post) values (?,?,?,?) ";		
	}

	private String sqlUpdate(){
		return "UPDATE comment set  author_name = ?, comment = ?  WHERE ID = ? ";
	}

	private String sqlDelete(){
		return "DELETE FROM comment  WHERE id = ? ";
	}

	private String sqlSelect(){
		return "SELECT id,author_name, comment, created_date, id_post FROM comment WHERE id_post = ?  ORDER BY created_date ";
	}

	public void insert(Comment comment)throws SQLException{
		pstm = getStatement(sqlInsert());
		pstm.setString(1, comment.getAuthorName());
		pstm.setString(2, comment.getComment());
		pstm.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		pstm.setLong(4, comment.getPost().getId());
		pstm.executeUpdate();
		close();
	}


	public void delete(Comment comment) throws SQLException {
		pstm =getStatement(sqlDelete());
		pstm.setLong(1, comment.getId());
		pstm.executeUpdate();
		close();
	}

	public List<Comment> getPostComments(long postId) throws SQLException{
		Post post = new PostDAO().searchById(postId);
		pstm = getStatement(sqlSelect());
		pstm.setLong(1, postId);

		rs = pstm.executeQuery();
		List<Comment> comments = new ArrayList<Comment>();

		while(rs.next()) {
			Comment comment = new Comment();
			comment.setAuthorName(rs.getString("author_name"));
			comment.setComment(rs.getString("comment"));
			comment.setCreatedDate(new Date(rs.getDate("created_date").getTime()));
			comment.setPost(post);
			comments.add(comment);
		}
		close();
		return comments; 
	}
}
