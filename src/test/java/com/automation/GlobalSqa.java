package com.automation;

import com.microsoft.playwright.*;

import java.util.List;

public class GlobalSqa {
    public static void main(String[] args) {
        Playwright playwright=Playwright.create();
        Browser browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page=browser.newPage();
        page.navigate("https://www.globalsqa.com/demo-site/draganddrop/");
//        FrameLocator source=page.frameLocator("//ul[@id='gallery']/li");
//        page.mouse().down();
//        FrameLocator destination=page.frameLocator("//iframe[@class='demo-frame lazyloaded']");
        FrameLocator iframe = page.frameLocator("//iframe[contains(@class, 'demo-frame')]").first();
        List<Locator> images = iframe.locator("//ul[@id='gallery']/li").all();
        Locator target = iframe.locator("div#trash");
        Locator image;
        for(int i=0; i<images.size();i++){
            image = iframe.locator("//ul[@id='gallery']/li").first();
            image.dragTo(target);
            page.waitForTimeout(2000);
        }
    }

}
