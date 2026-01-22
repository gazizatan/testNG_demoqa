package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public final class ExtentManager {
    private static final String REPORT_PATH = "reports/extent-report.html";
    private static ExtentReports extent;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter(REPORT_PATH);
            reporter.config().setReportName("DemoQA Test Report");
            reporter.config().setDocumentTitle("DemoQA Automation Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Project", "DemoQA TestNG");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }
}
