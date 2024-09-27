package com.automation;

import com.microsoft.playwright.*;

import java.util.ArrayList;
import java.util.List;

public class EbayDemo {
    public static void main(String[] args) {
        Playwright playwright=Playwright.create();
        Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page=browser.newPage();
        page.navigate("https://www.ebay.com/");
        Locator advancedSearch=page.locator("#gh-as-a");
        advancedSearch.click();
        Locator enterKeyword=page.locator("#_nkw");
        enterKeyword.fill("java");
        Locator selectCategory=page.locator("//label[text()='In this category']/parent::div//select");
        selectCategory.selectOption("Books & Magazines");
        Locator minPrice=page.locator("//input[contains(@aria-label,'Enter minimum price range value')]");
        minPrice.fill("0");
        Locator maxPrice=page.locator("//input[contains(@aria-label,'Enter maximum price range value')]");
        maxPrice.fill("1000");
        Locator selectConditionNew=page.locator("//label[text()='New']/preceding-sibling::span/input");
        selectConditionNew.click();
        Locator availableTo=page.locator("//select[@aria-label='Available to ']");
        availableTo.selectOption("India");
        Locator submit=page.locator(".adv-form__actions button");
        submit.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        List<Locator> names=page.locator("//span[@aria-level='3']").all();

        List<Locator> resultNames = page.locator("//ul/li[@data-viewport]//span[@role='heading']").all();

        resultNames.get(0).click();
        for(Locator name: resultNames){
            System.out.println(name.innerText());
        }
    }
}
