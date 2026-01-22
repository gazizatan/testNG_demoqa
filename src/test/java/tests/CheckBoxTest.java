package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBoxTest extends BaseTest {

    @Test
    public void testCheckBoxSelectHome() {
        logger.info("Navigate to Check Box page");
        driver.get(baseUrl + "/checkbox");

        WebElement homeLabel = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("label[for='tree-node-home']"))
        );
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", homeLabel);

        logger.info("Select Home checkbox");
        homeLabel.click();

        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        String resultText = result.getText().toLowerCase();

        logger.info("Verify result contains 'home'");
        Assert.assertTrue(resultText.contains("home"),
                "Expected result to contain 'home' but got: '" + resultText + "'");
    }
}
