package rahulshettyacademy.data;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//We have created this class to read data from PuchaseOrder.json
public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		// This will read the JSON file and convert its data to STRING variable.
		String jsonContent = FileUtils.readFileToString(new File(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PuchaseOrder.json"),
				StandardCharsets.UTF_8);

		// Convert String to HASHMAP by "Jackson Databind". We need to get dependency
		// from mvnrepository.
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

}
