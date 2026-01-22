package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;
import utils.ScreenshotUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);
    private static final ExtentReports extent = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> testNode = new ThreadLocal<>();
    private static final Path LOG_PATH = Path.of("logs", "test.log");

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test suite start: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test suite end: {}", context.getName());
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        logger.info("Test start: {}", testName);
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        testNode.set(test);
        test.log(Status.INFO, "Test started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}", result.getMethod().getMethodName());
        ExtentTest test = testNode.get();
        test.pass("Test passed");
        attachLogTail(test);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {}", result.getMethod().getMethodName(), result.getThrowable());
        ExtentTest test = testNode.get();
        test.fail(result.getThrowable());

        Object instance = result.getInstance();
        if (instance instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) instance;
            String path = ScreenshotUtil.captureScreenshot(baseTest.getDriver(), result.getMethod().getMethodName());
            if (path != null) {
                test.addScreenCaptureFromPath(path);
            }
        }
        attachLogTail(test);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {}", result.getMethod().getMethodName());
        ExtentTest test = testNode.get();
        test.skip(result.getThrowable());
        attachLogTail(test);
    }

    private void attachLogTail(ExtentTest test) {
        if (test == null || !Files.exists(LOG_PATH)) {
            return;
        }
        try {
            List<String> lines = Files.readAllLines(LOG_PATH);
            int fromIndex = Math.max(lines.size() - 100, 0);
            String tail = String.join("\n", lines.subList(fromIndex, lines.size()));
            if (!tail.isBlank()) {
                test.info("<pre>" + tail + "</pre>");
            }
        } catch (Exception e) {
            test.warning("Failed to attach log tail: " + e.getMessage());
        }
    }
}
