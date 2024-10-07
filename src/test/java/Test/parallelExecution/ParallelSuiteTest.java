package Test.parallelExecution;

import org.testng.annotations.*;

public class ParallelSuiteTest {
    @DataProvider(name = "testData", parallel = true)
    public Object[][] testData() {
        return new Object[][] {
                { "Test1" },
                { "Test2" },
                { "Test3" }
        };
    }

    @BeforeTest
    public void beforeTest() {
        long id = Thread.currentThread().getId();
        System.out.println("Before test. Thread id is: " + id);
    }

    @BeforeClass
    public void beforeClass() {
        long id = Thread.currentThread().getId();
        System.out.println("Before test-class. Thread id is: " + id);
    }

    @Test(dataProvider = "testData")
    public void testMethod(String testName) {
        long id = Thread.currentThread().getId();
        System.out.println("Sample test-method " + testName + ". Thread id is: " + id);
    }

    @AfterClass
    public void afterClass() {
        long id = Thread.currentThread().getId();
        System.out.println("After test-class. Thread id is: " + id);
    }

    @AfterTest
    public void afterTest() {
        long id = Thread.currentThread().getId();
        System.out.println("After test. Thread id is: " + id);
    }
}