package SkillSpark;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class CourseAdd {
	public static String website_url = "https://new-skill-spark.vercel.app";
	public static ChromeDriver driver;

	class Lecture {
		public String videopath;
		public String title;
		public String desc;

		public Lecture(String a, String b, String c) {
			videopath = a;
			title = b;
			desc = c;
		}
	};

	public void part2(String[] sectionName, Lecture[] lectures, boolean publish) {
		for (String section : sectionName) {
			driver.findElement(By.name("sectionName")).sendKeys(section);
			// click add section
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/form[1]/div[2]/button[1]/span[1]"))
					.click();
		}
		// add lectures to section
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		for (Lecture l : lectures) {
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/details[1]/div[1]/button[1]"))
					.click();
			driver.findElement(By.cssSelector("input[type='file']")).sendKeys(l.videopath);
			driver.findElement(By.name("lectureTitle")).sendKeys(l.title);
			driver.findElement(By.name("lectureDesc")).sendKeys(l.desc);
			// click save
			driver.findElement(By.xpath(
					"/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/form[1]/div[4]/button[1]"))
					.click();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
		// move to part 3
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[2]/button[2]"))
				.click();
		// publish course
		driver.findElement(By.cssSelector("input[type='checkbox']")).click();
		// save changes
		driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/form[1]/div[2]/button[2]"))
				.click();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver= new ChromeDriver();

		driver.get(website_url + "/login");
		driver.findElement(By.name("email")).sendKeys("archanatyagi8077@gmail.com");
		driver.findElement(By.name("password")).sendKeys("654321@Abc");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test(dataProvider = "dp")
	public void courseAdd(String courseTitle, String courseShortDesc, String coursePrice, String courseCategory,
			String[] courseTags, String thumbnail_path, String courseBenefits, String[] courseRequirements,
			String[] sectionName, Lecture[] lectures, boolean publish) {
		// course title,description and price set
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/a[4]")).click();
		driver.findElement(By.name("courseTitle")).sendKeys(courseTitle);
		driver.findElement(By.name("courseShortDesc")).sendKeys(courseShortDesc);
		driver.findElement(By.name("coursePrice")).sendKeys(coursePrice);

		// select category from dropdown
		Select ctg = new Select(driver.findElement(By.name("courseCategory")));
		ctg.selectByVisibleText(courseCategory);

		// enter all the tags
		for (String tag : courseTags) {
			driver.findElement(By.name("courseTags")).sendKeys(tag + Keys.RETURN);
		}
		// upload thumbnail image
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(thumbnail_path);

		// benefits
		driver.findElement(By.name("courseBenefits")).sendKeys(courseBenefits);

		// enter all requirements
		for (String requirement : courseRequirements) {
			driver.findElement(By.id("courseRequirements")).sendKeys(requirement);
			// click add btn
			driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
		}

		// go to the next part
		driver.findElement(By.xpath(
				"//button[@class='flex items-center bg-yellow-50 cursor-pointer gap-x-2 rounded-md py-2 px-5 font-semibold text-richblack-900 undefined']//*[name()='svg']"))
				.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		part2(sectionName, lectures, publish);
	}

	@AfterMethod
	public void afterMethod() {
		//log out
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[3]/button[1]/div[1]/span[1]")).click();
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/button[1]")).click();
		driver.quit();
	}

	@DataProvider
	public Object[][] dp() {
		String[] tags = { "development", "SDE" };
		String[] req = { "No pre-requisites for this course" };
		String[] sectionName = { "section 1" };
		Lecture[] lectures = new Lecture[1];
		lectures[0] = new Lecture("E:\\Downloads\\stockVideos\\SampleVideo_1280x720_1mb.mp4", "Intoduction", "Intoduction");
//		lectures[1] = new Lecture("E:\\Downloads\\stockVideos\\Sequence_05.mp4", "React", "Starting with react");
		return new Object[][] {
		new Object[] { "MERN stack", "This is a mern stack course", "65", "Web Development", tags,
				"E:\\Downloads\\mern-stack.png", "Will make you a good web developer", req, sectionName, lectures, true },
			new Object[] { "Flutter", "This is a flutter course", "78", "App Development", tags,
				"E:\\Downloads\\flutter.jpeg", "You will be a master in web development", req, sectionName, lectures, true },
			new Object[] { "Flutter", "", "78", "App Development", tags,
					"E:\\Downloads\\flutter.jpeg", "You will be a master in web development", req, sectionName, lectures, true }
		};
	}
}
