package Test;

import org.testng.annotations.Test;

import java.io.IOException;

//@Test (enabled = false)
public class AnnotationsDemo {

    @Test(enabled = false)
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test(dependsOnMethods = { "test2" })
    public void dependendTest() {
        System.out.println("This test depends on test2");
    }

    @Test(invocationCount = 3)
    public void repeatTest() {
        System.out.println("This test will run 3 times");
    }

    @Test(timeOut = 1000, priority = 1)     // Default priority is 0. Lower priority will be executed first
    public void timeOutTest() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("This test has a timeout of 1000 milliseconds");
    }

    @Test(expectedExceptions = { IOException.class })
    public void testIOException() throws IOException {
        throw new IOException("This will pass as this exception is expected");
    }

    @Test(groups = { "Group A" })
    public void testGroupA() {
        System.out.println("This test belongs to Group A");
    }

    @Test(description = "Title verification")
    public void verifyTitle() {
        System.out.println("Verifying title");
    }


}


