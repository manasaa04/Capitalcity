package com.sample.test.demo.tests;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sample.test.demo.Services;

public class CountryInformationPage {
	
	WebDriver driver;
	
	//COUNTRY INFORMATION
    String xpathCountry = "//select[@id='country1Country']";
    
  //COUNTRY DETAILED INFORMATION
    String xpathCountryCapitalCity = "//input[@id='country1Capital']";
    String xpathCountryCode = "//input[@id='country1Code']";
    String xpathCountryPhoneCode = "//input[@id='country1Pcode']";
    String xpathCountryContinent = "//input[@id='country1Continent']";
    String xpathCountryCurrency = "//input[@id='country1Currency']";
    String xpathCountryCurrencySymbol = "//input[@id='country1CurrencySymbol']";
    
   //RESET BUTTON
    String idResetButton = "reset";
    
    
    //Instance Variables
    String country;
    String capitalCity;
    String countryCode;
    String phoneCode;
    String continent;
    String currency;
    
    public CountryInformationPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void showCountryDetailedInformation(String country) {
    	Services service = new Services(driver);
    	service.selectOptionByText(country,xpathCountry);
    	
    	capitalCity = service.getAttribute(xpathCountryCapitalCity, "value");
    	countryCode = service.getAttribute(xpathCountryCode, "value");
    	phoneCode = service.getAttribute(xpathCountryPhoneCode, "value");
    	continent = service.getAttribute(xpathCountryContinent, "value");
    	currency = service.getAttribute(xpathCountryCurrency, "value");
    	
//    	System.out.println(capitalCity);
//    	System.out.println(countryCode);
//    	System.out.println(phoneCode);
//    	System.out.println(continent);
//    	System.out.println(currency);
//    	
    	
    	JSONParser parser = new JSONParser();
    	 
    	  try {
    	 
    	   Object obj = parser.parse(new FileReader("src\\test\\resources\\files\\countries.json"));
    	 
    	   JSONObject jsonObject = (JSONObject) obj;
    	 
    	   
    	   HashMap<String, Object> map = new HashMap<String, Object>();
    	   
    	   JSONArray listOfStates = (JSONArray) jsonObject.get("country");
    	   Iterator iterator = listOfStates.iterator();
    	   while (iterator.hasNext()) {
    		   String countryinfo = iterator.next().toString();
    		   System.out.println(countryinfo);
    		   
    		   ObjectMapper mapper = new ObjectMapper();
    	        
    	        	map = mapper.readValue(countryinfo, new TypeReference<Map<String, Object>>(){});
    	             
    	            //Print JSON output
    	            System.out.println(map);
    	            
    	           if(map.get("countryName").equals(country)) {
    	        	   System.out.println(map.get("countryName"));
    	        	   Assert.assertEquals(capitalCity.trim(), map.get("capital"));
    	           		Assert.assertEquals(countryCode.trim(), map.get("countryCode"));
    	           		Assert.assertEquals(continent.trim(), map.get("continentName"));
    	           		Assert.assertEquals(currency.trim(), map.get("currencyCode"));
    	           		break;
    	           }
    		   
    	   }
    	 
    	  } catch (FileNotFoundException e) {
    	   e.printStackTrace();
    	  } catch (IOException e) {
    	   e.printStackTrace();
    	  } catch (Exception e) {
    	   e.printStackTrace();
    	  }
    }
    
    public void resetAllInformation() {
    	Services service = new Services(driver);
    	service.click(idResetButton);
    	
    	country =  service.getAttribute(xpathCountry, "value");
    	capitalCity = service.getAttribute(xpathCountryCapitalCity, "value");
    	countryCode = service.getAttribute(xpathCountryCode, "value");
    	continent = service.getAttribute(xpathCountryContinent, "value");
    	currency = service.getAttribute(xpathCountryCurrency, "value");
    	
//    	System.out.println(capitalCity);
//    	System.out.println(countryCode);
//    	System.out.println(country);
//    	System.out.println(continent);
//    	System.out.println(currency);
    	
    	Assert.assertEquals(country.trim(), "Select a Country");
    	Assert.assertEquals(capitalCity.trim(), "Country - Capital City");
   		Assert.assertEquals(countryCode.trim(),"Country - Country Code");
   		Assert.assertEquals(continent.trim(), "Country - Continent");
   		Assert.assertEquals(currency.trim(), "Country - Currency");
    }
    
}
