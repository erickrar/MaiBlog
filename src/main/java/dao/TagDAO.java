package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.filter.Filter;
import dao.filter.TagFilter;
import entity.Tag;

public class TagDAO extends GenericDAO {

	private String sqlInsert() {
		return "INSERT INTO tag (name) values (?) ";		
	}

	private String sqlUpdate(){
		return "UPDATE tag set name = ? WHERE ID = ? ";
	}

	private String sqlDelete(){
		return "DELETE FROM tag  WHERE ID = ? ";
	}

	private String sqlSelect(){
		return "SELECT id,name FROM tag WHERE 1=1 ";
	}


	public void insert(Tag tag){
		try {
			PreparedStatement pstm = getStatement(sqlInsert());
			pstm.setString(1, tag.getName());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Tag tag){
		try {
			PreparedStatement pstm =getStatement(sqlUpdate());
			pstm.setString(1, tag.getName());
			pstm.setLong(2, tag.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Tag category){
		try {
			PreparedStatement pstm = getStatement(sqlDelete());
			pstm.setLong(1, category.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Tag searchFromId(long id) throws SQLException{
		TagFilter tf = new TagFilter();
		tf.byId(id);
		return search(tf).get(0);

	}
	public List<Tag> search(Filter filter) throws SQLException {
		rs = search(filter, sqlSelect());
		List<Tag> tags = new  ArrayList<Tag>();
		while(rs.next()){
			Tag t = new Tag();
			t.setId(rs.getLong("id"));
			t.setName(rs.getString("name"));
			tags.add(t);
		}
		close();
		return tags;
	}
}