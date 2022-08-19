package databaseTesting.databaseTesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class jdbcconnection {
	@Test(dataProvider="Data")
	public void test(String username,String password) throws InterruptedException
	{
		
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get("https://login.salesforce.com");
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);
		driver.quit();
	}
	@DataProvider
	public Object[][] Data() throws SQLException
	{
		Object[][] data=new Object[5][2];
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Moluramg123.");//mysql connector dependency to be added for execution
		Statement s=con.createStatement();
		ResultSet rs=s.executeQuery("SELECT * FROM credentials");
	int i=0;//rows iterator
			while(rs.next())
			{
		data[i][0]=rs.getString("username");//[0][0],[1][0],[2][0],[3][0],[4][0]
		data[i][1]=rs.getString("password");//[0][1],[1][1],[2][1],[3][1],[4][1]
			i++;
			}		
		return data;
	}
}
