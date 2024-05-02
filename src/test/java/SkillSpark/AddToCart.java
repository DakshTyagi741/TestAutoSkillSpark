package SkillSpark;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddToCart {
	public static String website_url = "https://new-skill-spark.vercel.app";
	public static ChromeDriver driver;
  @Test(invocationCount=5)
  public void f() {
	  try {
		  Thread.sleep(2000);
		  driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/nav[1]/ul[1]/li[1]/a[1]/p[1]")).click();
		  driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[2]/div[1]/div[1]/div[17]/a[1]")).click();
		  Thread.sleep(500);
		  driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[5]/div[2]/div[1]/a[4]/div[1]/div[1]/img[1]")).click();
		 
		  //press add to cart button
		  
		  driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/button[2]")).click();
		  
		  Thread.sleep(2000);
		  
		  //open cart
		  driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/div/div/a")).click();
		  
		  
		  Thread.sleep(1000);
		  //remove from cart
		  
		  driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/div/div[1]/div/div[2]/button")).click();
		  
		  
		  
	  }
	  catch(Exception e)
	  {
		  
	  }
	  
  }
  
  
  @BeforeMethod
  public void beforeMethod() {
	  driver= new ChromeDriver();

		driver.get(website_url + "/login");
		driver.manage().window().maximize();
		driver.findElement(By.name("email")).sendKeys("tyagidaksh741@gmail.com");
		driver.findElement(By.name("password")).sendKeys("aA@VC$45");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
  }
  
  public void afterMethod() {
		//close driver
		driver.quit();
	}
}
