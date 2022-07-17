package payroll;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;


/** 
* 22 Sep 2021 22:24:44
* @Javadoc TODO 
*
* @author Sebastian Vergara Losada  **/

public class Utilities {
	
	public static final String JSON_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	public static ObjectMapper mapper = new ObjectMapper().registerModule(new JodaModule());//JsonUtils.getObjectMapper;
	public static <T> T createObjectFromFile(TypeReference<T> type, String filePath) {
		T value = null;
		
		try {
			//String responseString = new String(Files.readAllBytes(Paths.get(filePath)), Charset.defaultCharset());
			String responseString = readFile(filePath, Charset.defaultCharset());
			value = mapper.readValue(responseString, type);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return value;
	}
	
	public static String readFile(String path, Charset encoding) {		
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			return new String(encoded, encoding);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "";
	}
}
