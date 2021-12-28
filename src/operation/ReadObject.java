package operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* 
1. For all of our UI web elements, we need to create an object repository where we will place their element locator (like Xpath, name, CSS path, class name etc.)
2. Execute.java (our driver script) will read the entire Object Repository and store it in a variable.
3. To read this object repository, we need a ReadObject class which has a getObjectRepository method to read it.
*/

public class ReadObject 
{
	Properties p = new Properties();
	public Properties getObjectRepository() throws IOException
	{
		//Read object repository file
		InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\objects_pkg\\object.properties"));
		
		//load all objects
		p.load(stream);
		return p;
	}
}