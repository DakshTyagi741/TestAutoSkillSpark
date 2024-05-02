package SkillSpark;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login {
	public static String website_url="https://new-skill-spark.vercel.app";
  @Test(dataProvider = "dp")
  public void loginFunction(String email, String password) {
	  ChromeDriver driver = new ChromeDriver();
	  driver.get(website_url+"/signup");
	  driver.findElement(By.name("email")).sendKeys(email);
	  driver.findElement(By.name("password")).sendKeys(password);
	  driver.findElement(By.cssSelector("button[type='submit']")).click();
	  try {
		  Thread.sleep(7000);
		  String currentUrl = driver.getCurrentUrl();
		  Assert.assertEquals(currentUrl, website_url+"/dashboard/my-profile");
	  }
//	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
	  
	  catch(Exception e) {
		  e.printStackTrace();
	  }
	  driver.quit();
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { "rvrv66692@gmail.com", "654321@Abc" },
      new Object[] { "bhjsdfjg@ggmail.com", "654321@Abc" },
    };
  }
}

