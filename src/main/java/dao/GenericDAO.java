package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.enterprise.context.RequestScoped;

import dao.filter.Filter;
/**
 * 
 * @author Erick R.
 *
 */

@RequestScoped
public abstract class GenericDAO {

	private Connection connection;
	protected ResultSet rs;
	protected PreparedStatement pstm;



	protected Connection getConnection(){
		try {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/maiblog","postgres","postgres");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	protected PreparedStatement getStatement(String sql){
		try {
			if (connection == null || connection.isClosed())
				connection = getConnection();
			return connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}




	protected ResultSet search(Filter filter, String sql) throws SQLException {

		if (filter == null)
			filter = new Filter();

		StringBuilder sbSQL = new StringBuilder();
		sbSQL.append(sql);

		for (String c : filter.getColumns()){
			sbSQL.append(c);
		}
		PreparedStatement pstm = getStatement(sbSQL.toString());
		for(int i = 0; i<filter.getParameters().size();i++){
			pstm.setObject(i + 1, filter.getParameters().get(i));
		}

		return  pstm.executeQuery();
	}

	protected ResultSet search(String sql) throws SQLException {
		return search(null,sql);
	}

	protected void close(){
		try {
			if(rs!=null) rs.close();
			if(pstm!=null) pstm.close();
			if(connection!=null) connection.close();
		} catch(Exception e){}
	}
}