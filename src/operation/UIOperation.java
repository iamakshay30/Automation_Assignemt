package operation;

//The driver will pass the data from Excel & Object Repository to UIOperation class.

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class UIOperation 
{
	WebDriver driver;
	JavascriptExecutor js;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("DD/MM/YYYY HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	
	public UIOperation(WebDriver driver) 
	{
		this.driver = driver;
		 js = (JavascriptExecutor) driver;
	}
	
	public void perform(Properties p, String operation, String objectName, String objectType, String value) throws Exception 
	{
		System.out.println("");
		switch (operation.toUpperCase()) 
		{
		case "GOTOURL":
			// Get URL of application
			driver.get(p.getProperty(value));
			Thread.sleep(1200);
			break;
			
			
		case "CLICK":
			// Perform click function
			driver.findElement(this.getObject(p, objectName, objectType)).click();
			break;
			
			
		case "SETTEXT":
			// Set text on control
			driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(value);
			break;
			
			
		case "VERIFY":
		// Verify the community page as per page title
			try 
			{
				if(driver.getTitle().contains(p.getProperty(value)))
				{
					System.out.println("Page Title is: " + driver.getTitle());
//					if(driver.getTitle().contains("Marathi"));
//					{
						System.out.println(p.getProperty(value) + " language is selected by default for " + p.getProperty(value) + " community website");
//					}
//					if(driver.getTitle().contains("Gujarati"));
//					{
//						System.out.println("Gujarati Language is selected");
//					}
					Thread.sleep(1000);
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception occured while verifying community page");
				System.out.println(e);
			}
			break;
			
			
		case "DROPDOWN":
			// Select value from drop down
			try 
			{
				driver.findElement(this.getObject(p, objectName, objectType)).click();
				Thread.sleep(500);
			}
			catch(Exception e)
			{
				System.out.println("Exception occured while selecting dropdown");
				System.out.println(e);
			}
			break;
			
			
		case "RADIOBTN":
			// Select value from Radio Button
			try 
			{
				driver.findElement(this.getObject(p, objectName, objectType)).click();
				Thread.sleep(500);
			}
			catch(Exception e)
			{
				System.out.println("Exception occured while selecting Radio button");
				System.out.println(e);
			}
			break;
			
			
		case "SLEEP":
			// sleep in milliseconds for 1 second
			Thread.sleep(1000);
			break;
			
			
		case "REFRESH":
			// Refresh page
			driver.navigate().refresh();
			break;
			
			
		case "CLOSE":
			// Close browser
			driver.close();
			break;
			
		default:
			break;
		}
	}

		
	/**
	 * Find element By using object type and value
	 * 
	 * @param p
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */

	private By getObject(Properties p, String objectName, String objectType) throws Exception 
	{
		// Find by xpath
		if (objectType.equalsIgnoreCase("XPATH")) 
		{
			return By.xpath(p.getProperty(objectName));
		}
		
		// find by class
		else if (objectType.equalsIgnoreCase("CLASSNAME")) 
		{
			return By.className(p.getProperty(objectName));
		}
		
		// find by id
		else if (objectType.equalsIgnoreCase("ID")) 
		{
			return By.id(p.getProperty(objectName));
		}
		
		// find by name
		else if (objectType.equalsIgnoreCase("NAME")) 
		{
			return By.name(p.getProperty(objectName));
		}
		
		// Find by CSS
		else if (objectType.equalsIgnoreCase("CSS"))
		{
			return By.cssSelector(p.getProperty(objectName));
		}
		
		// find by linkText
		else if (objectType.equalsIgnoreCase("LINKTEXT")) 
		{
			return By.linkText(p.getProperty(objectName));
		}
		
		// find by partial link
		else if (objectType.equalsIgnoreCase("PARTIALLINK")) 
		{
			return By.partialLinkText(p.getProperty(objectName));
		} 
		
		else 
		{
			throw new Exception("Wrong object type declared");
		}
	}
}
