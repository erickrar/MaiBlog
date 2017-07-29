package converter;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.Dependent;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

@Dependent
public class CustomDateGsonConverter implements JsonDeserializer<Date>, JsonSerializer<Date> {	

	@Override 
	public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) { 
		String dateString = getFormat().format(date);
		return new JsonPrimitive(dateString);
	}

	@Override 
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		try {
			return getFormat().parse(json.getAsString()); 
		} catch (ParseException e) { 
			throw new JsonSyntaxException(json.getAsString(), e);
		}
	}

	protected DateFormat getFormat(){ 
		return new SimpleDateFormat("dd/MM/yyy HH:mm");
	} 

}
