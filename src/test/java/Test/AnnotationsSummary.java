package Test;

import org.testng.annotations.Test;
import java.io.IOException;

// some attributes to the @Test annotation in TestNG

public class AnnotationsSummary {

    @Test(
            enabled = true,                                                       // to disable the test, set to false
            dependsOnMethods = {"initTest"},                      // this test depends on initTest
            invocationCount = 5,                                             //  run the test 5 times
            timeOut = 1000,                                                     // test will fail if it takes more than 1 second
            expectedExceptions = {IOException.class},       // test will pass if IOException is thrown
            groups = {"Group A"},                                           // test belongs to Group A
            description = "Title verification",
            priority = 1
    )
    public void sampleTestMethod() throws IOException, InterruptedException {
        Thread.sleep(500);
        throw new IOException("This is a test exception");
    }

    @Test
    public void initTest() {
        System.out.println("Initialization test");
    }

}