package Framework.Core.ExtentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

// not sure if this is correct

/**
 *  For parallel execution, it is recommended to use ExtentTestManager.
 */
public class ExtentTestManager {
//    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
//    static ExtentReports extent = ExtentManager.createExtentReports();
//
//    public static synchronized ExtentTest getTest() {
//        return extentTestMap.get((int) Thread.currentThread().getId());
//    }
//
//    public static synchronized ExtentTest startTest(String testName, String desc) {
//        ExtentTest test = extent.createTest(testName, desc);
//        extentTestMap.put((int) Thread.currentThread().getId(), test);
//        return test;
//    }

    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    private static final ExtentReports extent = ExtentManager.createExtentReports();

    /**
     * Gets the current thread's ExtentTest instance.
     *
     * @return the ExtentTest instance for the current thread, or null if none exists
     */
    public static synchronized ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }

    /**
     * Starts a new test and associates it with the current thread.
     *
     * @param testName the name of the test
     * @param desc     the description of the test
     * @return the newly created ExtentTest instance
     */
    public static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.createTest(testName, desc);
        extentTestThreadLocal.set(test);
        return test;
    }
}
