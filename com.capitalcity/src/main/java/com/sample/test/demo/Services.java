package com.sample.test.demo;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.function.Function;
import static org.apache.logging.log4j.LogManager.getLogger;

public class Services {


    private static Logger logger = getLogger(Services.class.getName());
    protected WebDriver driver;

    public Services(WebDriver driver) {
        this.driver = driver;
    }

    public void click(String locator) {
    	if(locator.contains("//")) {
    		driver.findElement(By.xpath(locator)).click();
    	}else{
    		driver.findElement(By.id(locator)).click();
    	}
           
    }
    //Java8 way - by same method we can pass all types of locators.
    public void type(Function<String, By> locate, String locator, String text) {
        driver.findElement(locate.apply(locator)).sendKeys(text);
    }


    public void waitForElementVisible(String locator) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
    }

    public WebElement getWebElement(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }
    
    public List<WebElement> getWebElements(String xpath) {
        return driver.findElements(By.xpath(xpath));
    }
    
    public String getAttribute(String xpath, String attribute) {
        return driver.findElement(By.xpath(xpath)).getAttribute(attribute);
    }

	public void selectOptionByText(String optionToSelect, String xpathSelectCounterTopEdge) {
		Select sel =new Select(driver.findElement(By.xpath(xpathSelectCounterTopEdge)));
		sel.selectByVisibleText(optionToSelect);
	}

	public String getText(String xpathHeading) {
		
		return driver.findElement(By.xpath(xpathHeading)).getText();
	}
	
	public boolean isElementVisible(String locator) {
        try {
            return driver.findElement(By.xpath(locator)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
