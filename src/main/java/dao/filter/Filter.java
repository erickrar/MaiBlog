package dao.filter;

import java.util.ArrayList;
import java.util.List;

public class Filter {

	private List<Object> parameters = new ArrayList<Object>();
	private List<String> columns = new ArrayList<String>();
	
	
	public List<Object> getParameters() {
		return parameters;
	}
	
	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}
	public List<String> getColumns() {
		return columns;
	}
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	
	
	public void addParameter(Object value){
		parameters.add(value);
	}
	
	public void addColumn(String value){
	columns.add(value);
	}
	
	
	public void byId(long id){
		addColumn(" AND id = ? ");
		addParameter(id);
	}
}
