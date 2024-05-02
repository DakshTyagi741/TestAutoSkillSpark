package SkillSpark;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Signup {
	public static String website_url="https://new-skill-spark.vercel.app";
  @Test(dataProvider = "dp")
  public void main_function(String firstName, String lastName,String email,String password,String confirmPassword) {
	  ChromeDriver driver = new ChromeDriver();
	  driver.get(website_url+"/signup");
	  driver.findElement(By.name("firstName")).sendKeys(firstName);
	  driver.findElement(By.name("lastName")).sendKeys(lastName);
	  driver.findElement(By.name("email")).sendKeys(email);
	  driver.findElement(By.name("password")).sendKeys(password);
	  driver.findElement(By.name("confirmPassword")).sendKeys(confirmPassword);
	  driver.findElement(By.cssSelector("button[type='submit']")).click();

	  try {
		  Thread.sleep(7000);
		  String currentUrl = driver.getCurrentUrl();
		  String regex = "^(?=.*[0-9])"
	              + "(?=.*[a-z])(?=.*[A-Z])"
	              + "(?=.*[@#$%^&+=])"
	              + "(?=\\S+$).{8,15}$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(password);
		  if(password!=confirmPassword || !m.matches())
		  {
			  Assert.assertEquals(currentUrl, website_url+"/signup");
			  System.out.println("Case 1");
		  }
		  else
		  {
			  Assert.assertEquals(currentUrl,website_url+"/verify-email");
			  System.out.println("Case 2");
		  }
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
	        // Password less than 8 characters
	        {"John", "Doe", "john.doe@example.com", "passwor", "passwor"},
	        // Password more than 15 characters
	        {"Jane", "Doe", "jane.doe@example.com", "veryLongPassword1234567890", "veryLongPassword1234567890"},
	         //Password missing lowercase letter
	        {"Michael", "Scott", "michael.scott@dundermifflin.com", "PASSWORD123!", "PASSWORD123!"},
	        // Password missing uppercase letter
	        {"Dwight", "Schrute", "dwight.schrute@dundermifflin.com", "password123$", "password123$"},
	        // Password missing special character
	        {"Pam", "Beesly", "pam.beesly@dundermifflin.com", "password1234", "password1234"},
	        // Password and confirm password don't match
	        {"Jim", "Halpert", "jim.halpert@dundermifflin.com", "password123$", "differentPassword"},
	        // Password and confirm password match (valid password)
	        {"Angela", "Martin", "angela.martin@dundermifflin.com", "Password123$", "Password123$"},
	        // Password and confirm password don't match
	        {"Kevin", "Malone", "kevin.malone@dundermifflin.com", "654321@Abc", "65432@Ab"},
	        // Valid password
	        {"Oscar", "Martinez", "oscar.martinez@dundermifflin.com", "12345678@ADc", "12345678@ADc"}
	    };
  }
 

}

