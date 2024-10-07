package Framework.Core.ExtentReports;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

//@Listeners({ExtentTestNGITestListener.class})
public class SampleExtentReportTest {
    @Test
    public void testMethod1() {
        try {
            assertTrue(true);
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
        }
    }

    @Test
    public void testMethod2() {
        try {
            assertTrue(false);
            // fail();
        } catch (Exception e) {
           System.out.println("Test failed: " + e.getMessage());
        }
    }
}
