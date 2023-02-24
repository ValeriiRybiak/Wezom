package agency.wezom.evrika.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

import static agency.wezom.evrika.utils.AllureHelper.attachScreenShot;

public class FailedTestsListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenShot();
    }
}
