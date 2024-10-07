package Test.groupingTests;

import org.testng.annotations.Test;

// src/test/resources/xml/testng-runningGroups.xml

public class GroupTests {
    @Test(groups = { "Group A" })
    public void testMethodAOne() {
        System.out.println("A-1 belongs to Group A");
    }

    @Test(groups = { "Group A" })
    public void testMethodATwo() {
        System.out.println("A-2 belongs to Group A");
    }

    @Test(groups = { "Group B"})
    public void testMethodBOne() {
        System.out.println("B-1 belongs to Group B");
    }

    @Test(groups = { "Group B", "Group C" })
    public void testMethodBTwo() {
        System.out.println("B-2 belongs to Group B and Group C");
    }

    @Test(groups = { "Group C" })
    public void testMethodC() {
        System.out.println("This test belongs to Group C");
    }

    @Test(groups = { "Group D" })
    public void testMethodD() {
        System.out.println("This test belongs to Group D");
    }
}
