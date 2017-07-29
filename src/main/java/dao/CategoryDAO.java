package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.filter.CategoryFilter;
import dao.filter.Filter;
import entity.Category;

public class CategoryDAO extends GenericDAO {

	private String sqlInsert(){
		return "INSERT INTO category (name) values (?) ";		
	}

	private String sqlUpdate(){
		return "UPDATE category set name = ? WHERE ID = ? ";
	}

	private String sqlDelete(){
		return "DELETE FROM category  WHERE ID = ? ";
	}

	private String sqlSelect(){
		return "SELECT id, name FROM category WHERE 1=1 ";
	}


	public void insert(Category category) throws SQLException{
		pstm = getStatement(sqlInsert());
		pstm.setString(1, category.getName());

		if (pstm.executeUpdate() == 0)
			getConnection().rollback();

		close();
	}

	public void update(Category category) throws SQLException{
		pstm = getStatement(sqlUpdate());
		pstm.setString(1, category.getName());
		pstm.setLong(2, category.getId());
		pstm.executeUpdate();
		close();
	}

	public void delete(Category category) throws SQLException{
		pstm =getStatement(sqlDelete());
		pstm.setLong(1, category.getId());
		pstm.executeUpdate();
		close();
	}

	public Category searchFromId(long id) throws SQLException{
		CategoryFilter cf = new CategoryFilter();
		cf.byId(id);
		return search(cf).get(0);
	}

	public List<Category> search(Filter filter) throws SQLException {
		rs = search(filter, sqlSelect());
		List<Category> categories = new  ArrayList<Category>();
		while(rs.next()){
			Category c = new Category();
			c.setId(rs.getLong("id"));
			c.setName(rs.getString("name"));
			categories.add(c);
		}
		close();
		return categories;
	}
	
	public List<Category> listAll() throws SQLException {
		rs = search(sqlSelect());
		List<Category> categories = new  ArrayList<Category>();
		while(rs.next()){
			Category c = new Category();
			c.setId(rs.getLong("id"));
			c.setName(rs.getString("name"));
			categories.add(c);
		}
		close();
		return categories;
	}
}