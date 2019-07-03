package CITVerichace.MavenBuildCIT;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AppTest {
	public AndroidDriver<MobileElement> driver;
	
	public String App_package 	= "io.appium.android.apis";
	public String App_activity  = "io.appium.android.apis.ApiDemos";
	public String appiumURL 	= "127.0.0.1";
	public String appiumPORT 	= "1473";
	
	@BeforeMethod
	public void setup() throws MalformedURLException {
		String userpath = System.getProperty("user.dir");
		String appDir = userpath+"//apk//ApiDemos.apk";
		
		DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "2ac74d119805");
        caps.setCapability("automationName", "UiAutomator2"); 
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "8.1.0");
        caps.setCapability("appPackage", App_package);
        caps.setCapability("appActivity",App_activity);
        caps.setCapability("noReset","false");
        caps.setCapability("app", appDir);
        driver = new AndroidDriver<MobileElement>(new URL("http://"+ appiumURL + ":" +appiumPORT + "/wd/hub"),caps);
 
		
		}
	
	@Test
	public void f() {
		driver.findElementByXPath("//android.widget.TextView[@content-desc=\"App\"]").click();
		driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Activity\"]").click();
		driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Custom Title\"]").click();
		driver.findElementById("io.appium.android.apis:id/left_text_edit").clear();
		driver.findElementByXPath("//android.widget.Button[@content-desc=\"Change Left\"]").click();
		driver.findElementById("io.appium.android.apis:id/right_text_edit").clear();
		driver.findElementByXPath("//android.widget.Button[@content-desc=\"Change Right\"]").click();
		
		boolean lefttextstatus = driver.findElementsById("io.appium.android.apis:id/left_text").size() != 0;
		AssertJUnit.assertEquals(lefttextstatus, false);
		boolean righttextstatus = driver.findElementsById("io.appium.android.apis:id/right_text").size() != 0;
		AssertJUnit.assertEquals(righttextstatus, false);
	}
		
	@Test
	public void f2() throws InterruptedException {
		driver.findElementByXPath("//android.widget.TextView[@content-desc=\"App\"]").click();
		driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Alert Dialogs\"]").click();
		driver.findElementByXPath("//android.widget.Button[@content-desc=\"Single choice list\"]").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//android.widget.ListView[@resource-id=\"android:id/select_dialog_listview\"]//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Traffic\"]").click();
		driver.findElementById("android:id/button1").click();
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit(); 
		}
}


