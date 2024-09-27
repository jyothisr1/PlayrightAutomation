package com.automation;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

public class EbayActions {
    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.ebay.com/");
        Locator advancedSearch=page.locator("#gh-as-a");
        advancedSearch.click();

        //input
        page.getByTestId("_nkw").pressSequentially("java");

        //drag and drop
        page.locator("//span[text()='Items in stores']").first().hover();
        page.mouse().down();
        page.getByTestId("_ex_kw").hover();
        page.mouse().up();

        //focus
//        page.locator(".adv-form__actions button").focus();
//        page.locator("#_nkw").hover();
//        page.locator("//label[text()='In this category']/parent::div//select").hover();

        //radio button
        Locator selectConditionNew=page.locator("//label[text()='New']/preceding-sibling::span/input");
        selectConditionNew.check();
        System.out.println(selectConditionNew.isChecked());

        //scroll
        Locator selectCategory=page.locator("//label[text()='In this category']/parent::div//select");
        selectCategory.scrollIntoViewIfNeeded();

        //hover
        selectCategory.selectOption("Books & Magazines");
        page.locator("//a[text()='search tips']").hover();
//        selectCategory.hover();

        //select by mouse click
        page.mouse().down();
        page.mouse().up();
        page.bringToFront();

    }
}
