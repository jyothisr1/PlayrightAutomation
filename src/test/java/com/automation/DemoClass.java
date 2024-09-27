package com.automation;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DemoClass {
    public static void main(String[] args) {
        Playwright playwright=Playwright.create();
        Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page=browser.newPage();
        page.navigate("https://www.saucedemo.com/");
//        playwright.close();
        Locator userNameInput=page.locator("#user-name");
        Locator passwordInput=page.locator("#password");
        Locator loginButton=page.locator("#login-button");
        userNameInput.fill("standard_user");
        passwordInput.fill("secret_sauce");
        loginButton.click();
        assertThat(page.locator("span.title")).hasText("Products");
        Locator addToCartButton=page.locator("#add-to-cart-sauce-labs-backpack");
        addToCartButton.click();
        Locator addToCartIcon=page.locator(".shopping_cart_link");
        addToCartIcon.click();
        Locator checkOut=page.locator("#checkout");
        checkOut.click();
        Locator firstName=page.locator("#first-name");
        firstName.fill("jy");
        Locator lastName=page.locator("#last-name");
        lastName.fill("jy");
        Locator postalCode=page.locator("#postal-code");
        postalCode.fill("jy");
        Locator continueButton=page.locator("#continue");
        continueButton.click();
        Locator finish=page.locator("#finish");
        finish.click();
        Locator successMessage=page.locator(".complete-header");
        assertThat(successMessage).hasText("Thank you for your order!");
        System.out.println("executed");
    }
}
