package Framework.Core.ExtentReports;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class SampleTest {
    @Test
    public void testMethod1() {
        ExtentTestManager.startTest("testMethod1", "Sample test method 1");
        try {
            assertTrue(true);
            ExtentTestManager.getTest().pass("Test passed");
        } catch (Exception e) {
            ExtentTestManager.getTest().fail("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testMethod2() {
        ExtentTestManager.startTest("testMethod2", "Sample test method 2");
        try {
//            assertTrue(false);
            fail();
            ExtentTestManager.getTest().pass("Test passed");
        } catch (Exception e) {
            ExtentTestManager.getTest().fail("Test failed: " + e.getMessage());
        }
    }

    @AfterSuite
    public void tearDown() {
        ExtentManager.extentReports.flush();
    }
}
