# Test Automation with TestNG

### `Features:`
* [Sample of Annotation Usages](src/test/java/Test/Annotations.md)
* <a href="#listeners">Listeners</a>
* <a href="#parallel">Parallel Testing</a>
* [Base Data Provider](src/test/java/Framework/Core/BaseDataProvider.java)
* [Retry Mechanism](src/test/java/Framework/Core/CustomAnnotations/Notes_Retry.md) & `RerunIfTestFails` Custom Annotation 
* [Extent Report](src/test/java/Framework/Core/ExtentReports/Notes_ExtentReport.md)
* [Singleton WebDriver](src/test/java/Framework/Core/Driver.java)
* Logs


<h2><b><span style="color:darkred">Test Execution</span></b></h2>

##### [documentation](https://maven.apache.org/surefire/maven-surefire-plugin/examples/single-test.html)

**Running a specific test class**

> mvn test -Dtest=Test/LoginTests.java

**Running a specific test case in a class**

> mvn test -Dtest=Test/LoginTests.java#loginTestWithInvalidCredentials

**Running a single xml**

> mvn clean test -DsuiteXmlFile=testng.xml
>
> mvn clean verify -Drunner=testng.xml

**Running all the xml files mentioned in the pom.xml:**

> mvn clean test -DsuiteXmlFile

All of the tests are independent from each other, UNLESS we create dependency.

<h2 id="listeners"><b><span style="color:darkred">Listeners</span></b></h2>

In TestNG, listeners are interfaces that allow you to customize the behavior of the testing framework to perform specific actions at different stages.
We use it generally for loging at stages like `onStart`, `onFinish`, `onTestStart`, `onTestSuccess`, `onTestFailure`, `onTestSkipped`

* TestNG provides several built-in listeners.
* Listeners used in this framework:  `ITestListener`, `IAnnotationTransformer`, `IRetryAnalyzer`

To use a listener, we need to configure it in the testng.xml. You can add multiple listeners.
```xml
<listeners>
    <listener class-name="Framework.Core.Listeners.TestListener"/>
    <listener class-name="com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter"/>
</listeners>
```

If you run your tests only through XML files, then you don't need to mention the listener in the test. Otherwise, you have to use the @Listeners annotation to link it to your test.

```java
@Listeners({ExtentITestListenerAdapter.class})
public class LoginTests{ } 
```


<h2 id="parallel"><b><span style="color:darkred">Parallel Testing</span></b></h2>

Parallel testing in my project is enabled by the following configurations and annotations:

1. The `@DataProvider` annotation has the `parallel` attribute set to `true`, which allows the test methods to run in parallel.

   ```java
   @DataProvider(name = "testData", parallel = true)
   public Object[][] testData() {
       return new Object[][] {
           { "Test1" },
           { "Test2" },
           { "Test3" }
       };
   }
   ```

2. The `testng-parallelsuite.xml` specifies the `parallel` attribute set to `tests` and a `thread-count` of 3, which allows multiple test classes to run in parallel.

   ```xml
   <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd" >
   <suite name="ParallelSuite" parallel="tests" thread-count="3">
       <test name="Test1">
           <classes>
               <class name="Test.parallelExecution.ParallelSuiteTest"/>
           </classes>
       </test>
       <test name="Test2">
           <classes>
               <class name="Test.parallelExecution.ParallelSuiteTest"/>
           </classes>
       </test>
       <test name="Test3">
           <classes>
               <class name="Test.parallelExecution.ParallelSuiteTest"/>
           </classes>
       </test>
   </suite>
   ```

These configurations together enable parallel execution of your test methods and test classes.

### pom.xml

Use the `maven-surefire-plugin` to run your tests through the `pom.xml` file
Here is an example configuration:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.5.0</version>
            <configuration>
                <parallel>methods</parallel>
                <threadCount>5</threadCount>
                <suiteXmlFiles>
                    <suiteXmlFile>src/test/resources/xml/testng-parallelsuite.xml</suiteXmlFile>
                </suiteXmlFiles>
            </configuration>
        </plugin>
    </plugins>
</build>
```

To run the tests:

```sh
mvn clean test
```
