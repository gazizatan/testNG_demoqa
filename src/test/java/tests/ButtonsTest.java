package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ButtonsTest extends BaseTest {

    @Test
    public void testDoubleClickButton() {
        logger.info("Navigate to Buttons page");
        driver.get(baseUrl + "/buttons");

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("doubleClickBtn")));
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", button);

        logger.info("Perform double click");
        new Actions(driver).doubleClick(button).perform();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("doubleClickMessage")));
        String actual = message.getText();
        String expected = "You have done a double click";

        logger.info("Verify double click message");
        Assert.assertEquals(actual, expected,
                "Expected message: '" + expected + "' but got: '" + actual + "'");
    }
}
