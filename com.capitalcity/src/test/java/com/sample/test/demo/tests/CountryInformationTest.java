package com.sample.test.demo.tests;

import org.testng.annotations.Test;
import com.sample.test.demo.TestBase;

public class CountryInformationTest extends TestBase {

    @Test(description = "Validate that when the user selects a country the correct information related to the country gets displayed.")
    public void getCountryInfo() {
        CountryInformationPage country = new CountryInformationPage(driver);
        country.showCountryDetailedInformation("Swaziland");
    }

    @Test(description = "Validate that when the user clicks reset button the entire webpage data gets reset")
    public void resetPage() {
        CountryInformationPage country = new CountryInformationPage(driver);
        country.resetAllInformation();
    }
}
