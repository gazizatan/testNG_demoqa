package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextBoxTest extends BaseTest {

    @Test
    public void testTextBoxFormSubmission() {
        logger.info("Navigate to Text Box page");
        driver.get(baseUrl + "/text-box");

        String fullName = "Tanirbergen Gaziza";
        String email = "gaziza@example.com";
        String currentAddress = "aitu c1, Astana";
        String permanentAddress = "aitu c2, Astana";

        logger.info("Fill in form fields");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName"))).sendKeys(fullName);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("currentAddress")).sendKeys(currentAddress);
        driver.findElement(By.id("permanentAddress")).sendKeys(permanentAddress);

        WebElement submit = driver.findElement(By.id("submit"));
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", submit);
        logger.info("Submit the form");
        submit.click();

        WebElement output = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("output")));
        String outputText = output.getText();

        String expectedName = "Name:" + fullName;
        String expectedEmail = "Email:" + email;

        logger.info("Verify output contains name and email");
        Assert.assertTrue(outputText.contains(expectedName),
                "Expected name in output: '" + expectedName + "' but got: '" + outputText + "'");
        Assert.assertTrue(outputText.contains(expectedEmail),
                "Expected email in output: '" + expectedEmail + "' but got: '" + outputText + "'");
    }
}
