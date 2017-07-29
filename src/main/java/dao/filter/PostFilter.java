package dao.filter;

public class PostFilter extends Filter {


	public void byId(long id){
		addColumn(" AND p.id = ? ");
		addParameter(id);
	}

	public void byAuthor(long id){
		addColumn("AND p.id_author  = ? ");
		addParameter(id);
	}
	
	public void byCategory(String name){
		addColumn("AND UPPER(c.name) = ?");
		addParameter(name.toUpperCase());
	}
}
