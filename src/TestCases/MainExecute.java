package TestCases;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import operation.ReadObject;
import operation.UIOperation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import ExcelExportAndFileIO_pkg.ReadExcelFile;

public class MainExecute
{
	public WebDriver driver;

	@Test
	public void TestCase() throws Exception 
	{
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\akshay.pote\\Documents\\chromedriver_v96_win32\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		ReadExcelFile file = new ReadExcelFile();
		ReadObject object = new ReadObject();
		Properties allObjects = object.getObjectRepository();
		UIOperation operation = new UIOperation(driver);
		
		//Read keyword sheet
		Sheet mysheet = file.readExcel(System.getProperty("user.dir") + "\\", "TestCase.xlsx", "KeywordFramework");
		
		//Find number of rows in excel file
		int rowCount = mysheet.getPhysicalNumberOfRows()-1;
//		int rowCount = mysheet.getLastRowNum() - mysheet.getFirstRowNum();
		System.out.println("Physical Number Of Rows: " + mysheet.getPhysicalNumberOfRows());
		System.out.println("Sheet Name is: " + mysheet.getSheetName());
		
		// Create a loop over all the rows of excel file to read it
		for (int i = 1; i < rowCount + 1; i++) 
//		for (int i = 1; i < rowCount-2 ; i++)
		{
			// Loop over all the rows
			Row row = mysheet.getRow(i);
			
			// Check if the first cell contain a value, if yes, That means it is the new test case name
			if (row.getCell(0).toString().length() == 0) 
			{
				// Print test case detail on console
				System.out.println(row.getCell(1).toString() + "-> " 
									+ row.getCell(2).toString() + "-> "
									+ row.getCell(3).toString() + "-> " 
									+ row.getCell(4).toString());
				
				// Call perform function to perform operation on UI
				operation.perform(allObjects, row.getCell(1).toString(), 
												row.getCell(2).toString(), 
												row.getCell(3).toString(),	
												row.getCell(4).toString());
			}
			else 
			{
				// Print the new test case name when it started
				System.out.println("\n New Testcase -> " + row.getCell(0).toString() + " -> Started");
			}
		}
	}
}