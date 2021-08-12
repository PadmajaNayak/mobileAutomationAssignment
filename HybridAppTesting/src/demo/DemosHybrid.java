package demo;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class DemosHybrid extends Capability {
AndroidDriver<AndroidElement> driver;
	
	@BeforeTest
	public void setUp() throws MalformedURLException {
		driver=capabilities();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test(enabled=false)
	public void testCase1() {
		driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Padmaja");
		driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();
		driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"India\"))").click();;
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		
//		driver.findElementByAndroidUIAutomator("text(\"PG 3\")").click();
	}
	
	@Test(enabled=false)
	public void testCase2() {
//		driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Padmaja");
		driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();
		driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))").click();;
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		String text =driver.findElement(By.xpath("//android.widget.Toast[1]")).getText();
		System.out.println(text);
		Assert.assertEquals(text, "Please enter your name");
//		driver.findElementByAndroidUIAutomator("text(\"PG 3\")").click();
	}
	
	@Test(enabled=false)
	public void testCase3() throws InterruptedException {
		driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Padmaja");
		driver.findElementById("com.androidsample.generalstore:id/radioFemale").click();
		driver.findElementById("com.androidsample.generalstore:id/spinnerCountry").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))").click();;
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(0).click();
		driver.findElementsById("com.androidsample.generalstore:id/productAddCart").get(1).click();
		driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
		Thread.sleep(5000);
		
		float item1 = Float.parseFloat(driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(0).getText().replace("$", ""));
		float item2 = Float.parseFloat(driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(1).getText().replace("$", ""));
		
		float item3 = Float.parseFloat(driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText().replace("$ ", ""));
		
		float sum = item1+item2;
		Assert.assertEquals(item3, sum);
	}
	
	@Test(enabled=true)
	public void testCase4() throws InterruptedException {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Padmaja");
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        //i have to work with drop down
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))").click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(3000);
        
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Converse All Star\"))");
        
        int count = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        
        for(int i=0;i<count;i++)
        {
            String name = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if(name.equals("Converse All Star"))
            {
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                break;
            }
        }
        driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
		Thread.sleep(5000);
		
		WebElement chkbox = driver.findElementByClassName("android.widget.CheckBox");
		TouchAction t = new TouchAction(driver);
		t.tap(tapOptions().withElement(element(chkbox))).perform();
		
		WebElement terms = driver.findElementById("com.androidsample.generalstore:id/termsButton");
		t.longPress(longPressOptions().withElement(element(terms)).withDuration(ofSeconds(3))).release().perform();
		
		driver.findElementById("android:id/button1").click();
		
		driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();
		Thread.sleep(9000);
		
		Set<String> contextNames = driver.getContextHandles();
		for (String contextName : contextNames) {
		    System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
		}
		
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("IBM");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));

		
    }
}
