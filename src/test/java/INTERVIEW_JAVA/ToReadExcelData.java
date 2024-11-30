package INTERVIEW_JAVA;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Base64;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ToReadExcelData {
	
	static Logger logger;
	static WebDriver driver;


	@Test(enabled=false)
	public static void readExcelData() throws IOException
	{
		String path="C:\\Users\\AKILA RAJESH\\OneDrive\\Desktop\\read.xlsx";
		FileInputStream fi=new FileInputStream(path);

		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sheet=wb.getSheet("Data");
		int rowCount=sheet.getLastRowNum();
		int cellCount=sheet.getRow(0).getLastCellNum();

		/*
		 * using for loop to get the data
		 */

		for(int row=1;row<=rowCount;row++)
		{
			XSSFRow currentRow=sheet.getRow(row);

			for(int cell=0;cell<cellCount;cell++)
			{
				XSSFCell currentCell=currentRow.getCell(cell);

				switch(currentCell.getCellType())
				{
				case STRING:
					System.out.println(currentCell.getStringCellValue());
					break;

				case BOOLEAN:
					System.out.println(currentCell.getBooleanCellValue());
					break;

				case NUMERIC:
					System.out.println(currentCell.getNumericCellValue());
					break;

				}
			}
		}

		fi.close();

	}



	@Test(enabled=false)
	public static void readExcelData1() throws IOException
	{
		String path="C:\\Users\\AKILA RAJESH\\OneDrive\\Desktop\\read.xlsx";
		FileInputStream fi=new FileInputStream(path);

		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sheet=wb.getSheet("Data");
		int rowCount=sheet.getLastRowNum();
		int cellCount=sheet.getRow(0).getLastCellNum();

		/*
		 * using Iterator to get the data
		 */

		Iterator rows=sheet.iterator();
		while(rows.hasNext())
		{
			XSSFRow currentRow=(XSSFRow)rows.next();

			Iterator cells=currentRow.iterator();
			while(cells.hasNext())
			{
				XSSFCell currentCell=(XSSFCell)cells.next();

				switch(currentCell.getCellType())
				{
				case STRING:
					System.out.print(currentCell.getStringCellValue()+" ");
					break;

				case BOOLEAN:
					System.out.print(currentCell.getBooleanCellValue()+" ");
					break;

				case NUMERIC:
					System.out.print(currentCell.getNumericCellValue()+" ");
					break;

				}



			}
			System.out.println();
		}



		fi.close();

	}


	@Test(enabled=false)
	public static void toWriteData() throws IOException
	{
		Object data[][]= {{"S.No","Name"},{1,"hh"},{2,"bb"}};
		String path="C:\\Users\\AKILA RAJESH\\OneDrive\\Desktop\\read.xlsx";
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("Data1");
		for(int row=0;row<data.length;row++)
		{
			XSSFRow currentRow=sheet.createRow(row);

			for(int cell=0;cell<2;cell++)
			{
				XSSFCell currentCell=currentRow.createCell(cell);
				Object data1=data[row][cell];
				if(data1 instanceof String)
					currentCell.setCellValue((String)data1);
				if(data1 instanceof Integer)
					currentCell.setCellValue((Integer)data1);
				if(data1 instanceof Boolean)
					currentCell.setCellValue((Boolean)data1);
			}
		}

		FileOutputStream fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fo.close();
	}

	@Test(enabled=false)
	public static void toWriteDataUsingForEachLoop() throws IOException
	{
		Object data[][]= {{"S.No","Name"},{1,"hh"},{2,"bb"}};
		String path="C:\\Users\\AKILA RAJESH\\OneDrive\\Desktop\\read.xlsx";
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet("Data12");
		int row=0;
		for(Object t[]:data)
		{
			XSSFRow currentRow=sheet.createRow(row);
			row++;
			int cell=0;

			for(Object g:t)
			{

				XSSFCell currentCell=currentRow.createCell(cell);
				if(g instanceof String)
					currentCell.setCellValue((String)g);
				if(g instanceof Integer)
					currentCell.setCellValue((Integer)g);
				if(g instanceof Boolean)
					currentCell.setCellValue((Boolean)g);

				cell++;
			}
		}
		FileOutputStream fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fo.close();
	}

	@Test(enabled=false)
	public static void InvokeMethod() throws IllegalAccessException, InvocationTargetException
	{
		ToReadExcelData v=new ToReadExcelData();
		Class s=v.getClass();
		Method f[]=s.getMethods();
		for(Method c:f)
		{
			System.out.println(c.getName());
			if(c.getName().equalsIgnoreCase("A"))
			{
				c.invoke(s);
			}
		}

	}
	public static void A()
	{
		System.out.println("am a");
	}
	public static void B()
	{
		System.out.println("am b");
	}
	public static void C()
	{
		System.out.println("am c");
	}

	@Test(enabled=false,dataProvider="rr")
	public static void enterNames(String name,String pwd)
	{
		System.out.println(name+" "+pwd);
	}

	@DataProvider(name="rr")

	public static Object dataProvider() throws IOException
	{
		String path="C:\\Users\\AKILA RAJESH\\OneDrive\\Desktop\\DataProvider.xlsx";
		Object data[][]=null;
		String sheetName="Login";
		int totalRows=Generic_Excel_Read_And_Write.getTotalRowCount(path,sheetName);
		int totalcells=Generic_Excel_Read_And_Write.getTotalCellCount(path,sheetName,0);

		data=new Object[totalRows][totalcells];

		for(int row=1;row<=totalRows;row++) { for(int cell=0;cell<totalcells;cell++) {
			data[row-1][cell]=Generic_Excel_Read_And_Write.getCellData(path,sheetName,row,
					cell); } }

		return data;
	}
	
	@BeforeTest(enabled=true)
	public static void beforeTest()
	{
		driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://www.google.com");
		
		
		  logger=Logger.getLogger(ToReadExcelData.class.getName());
		PropertyConfigurator.configure("Log4j.properties");
		logger.info("logger started");

	}
	
	@Test(enabled=false)
	public void getScreenShotAsFile() throws IOException
	{
		TakesScreenshot shot=(TakesScreenshot)driver;
		File srcFile=shot.getScreenshotAs(OutputType.FILE);
		logger.info("screen shot taken ");
		FileUtils.copyFile(srcFile, new File("D://JAVA PROJECTS//COM.INTERVIEW.PRACTICE//screenshot1//1000.jpg"));
 
 		
	}
	
	@Test(enabled=false)
	public void getScreenShotAsBase64() throws IOException
	{
		TakesScreenshot shot=(TakesScreenshot)driver;
		String srcFile=shot.getScreenshotAs(OutputType.BASE64);
		logger.info("screen shot taken ");
		 byte[] g=Base64.getDecoder().decode(srcFile);
		 FileOutputStream fo=new FileOutputStream("D://JAVA PROJECTS//COM.INTERVIEW.PRACTICE//screenshot1//google10.jpg");
		 fo.write(g);
		 fo.close();

 
  
 		
	}
	
	@AfterTest(enabled=false)
	public static void afterTest()
	{
		//driver.close();
		logger.info("driver closed");
	}
	
	@Test(enabled=false)
	public static void webdriverDemo()
	{
		driver.findElement(By.xpath("//button[@id='btn1']")).click();
		driver.findElement(By.id("txt1")).sendKeys("raj");

		 
	}
	
	 
	
	@Test(enabled=true)
	public static void MouseKeyboardActionsDemo()
	{
		//perform the right click
		
		WebElement t=driver.findElement(By.xpath("//a[text()='Gmail']"));
		Actions a=new Actions(driver);
		a.contextClick(t).build().perform();
		//perform to send capital letter by shift keys
		
		WebElement t1=driver.findElement(By.xpath("//textarea[@class='gLFyf']"));
		a.keyDown(t1,Keys.SHIFT).sendKeys("raj").keyUp(Keys.SHIFT).build().perform();
        
		//Action a1==a.contextClick(t).build();
		
	}
	
	


}


