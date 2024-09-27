package com.automation;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class expediaPlaywright {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.expedia.co.in/");
        Locator flightSearch = page.locator("//a[@aria-controls='search_form_product_selector_flights']");
        flightSearch.click();
        Locator clickFromInputField = page.locator("//button[@aria-label='Leaving from']");
        clickFromInputField.click();
        Locator fromInputField = page.locator("#origin_select");
        fromInputField.fill("Hyderabad");
        Locator selectFromDestination = page.locator("//button[contains(@aria-label,'Hyderabad (HYD - Rajiv Gandhi')]");
        selectFromDestination.click();
        Locator clickToInputField = page.locator("//button[@aria-label='Going to']");
        clickToInputField.click();
        Locator toInputField = page.locator("#destination_select");
        toInputField.fill("Delhi");
        Locator selectToDestination = page.locator("//button[contains(@aria-label,'Delhi (DEL - Indira Gandhi')]");
        selectToDestination.click();
        Locator noOfPersons = page.locator("//button[@data-stid='open-room-picker']");
        noOfPersons.click();
        int adults = 2;
        int n = 1;
        while (n != adults) {
            Locator increaseNoOfAdults = page.locator("section > div:nth-child(1) > div > div > button:nth-child(3)");
            increaseNoOfAdults.click();
            n++;
        }
        int children=2;
        int childCount=0;
        String[] childAge1={"10","16"};
        while (childCount!=children){
            Locator noOfChildren = page.locator("//input[@id='traveler_selector_children_step_input']/../../div/button/following-sibling::button");
            noOfChildren.click();
            Locator childAge = page.locator("#age-traveler_selector_children_age_selector-"+childCount);
            childAge.selectOption(childAge1[childCount]);
            childCount++;
        }
        Locator doneButton = page.locator("#travelers_selector_done_button");
        doneButton.click();
        Locator searchButton = page.locator("#search_button");
        searchButton.click();
        Locator menuPriceSelected = page.locator("//div[@data-test-id='flexible-search-container']//button[@aria-selected='true']/span[2]");
        String selectedMenuPrice=menuPriceSelected.textContent();
        Locator displayedPrice = page.locator("//*[@id=\"AQq1AgqfAnY1LXNvcy00MGIwNTQ0MDkwYmI0MjhlYjBlNmM2OTk5NjI5MjE2Yy0zLTEzMi0xfjIuU35BUW9DQ0JzU0J3aW9HeEFKR0Jnb0FsZ0NjQUI2QTJKbWM0Z0J5b2VXUmd-QVFvMUNqTUl3WklCRWdRMk5UUTBHSWNlSUlOWktOcTkxd0l3MTc3WEFqaFRRQUJZQVdvSFJVTlBRMDlOUm5JSVUxVXhXVmd3U1VrS05Bb3lDTUdTQVJJRE5UWXdHSU5aSUljZUtJVEoxd0l3aHNyWEFqaFRRQUJZQVdvSFJVTlBRMDlOUm5JSVUxVXhXVmhVU1VrU0NnZ0JFQUVZQVNvQ1FVa1lCQ0lFQ0FFUUF5SUVDQUlRQVNnQ0tBTW9CREFCEQAAAABAYONAIgEBKgUSAwoBMRJRChoKCjIwMjQtMDktMTMSA0hZRBoDREVMMAE4AQoaCgoyMDI0LTA5LTE0EgNERUwaA0hZRDABOAESBxIFQ09BQ0gaAhACGggIARACGgIKECACGggIARIEGgAiAA==\"]/div/div/button/span");
        String displayedPriceText=displayedPrice.textContent();
        assertThat(displayedPrice).hasText(menuPriceSelected.textContent());
        page.screenshot(new Page.ScreenshotOptions().setFullPage(true).setPath(Paths.get("./target/screenshot.png")));

        Page newPage=page.waitForPopup(()->{
            page.locator("").first().click();
        });
        System.out.println(newPage.locator("").textContent());

        newPage.close();

        page.bringToFront();
        page.locator("").clear();

    }
}
