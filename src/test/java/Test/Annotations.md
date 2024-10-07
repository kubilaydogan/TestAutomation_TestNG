## What is TestNG?
* TestNG stands for `Test NextGeneration`.
* TestNG is a testing framework for Java
* It provides lots of annotations that allow us to create a flow in our testing.
* Also it has a wide range of features and functionalities
* Test configuration can be done through XML or annotations


## TestNG Annotations 

**Annotations** are a way of controlling the flow of the test. Below are some commonly used annotations with examples.

<details>
    <summary>
        <b><span style="color:darkred">Before</span></b>
        <b><span style="color:black"> and</span></b>
        <b><span style="color:darkred"> After</span></b>
        <b><span style="color:black"> annotations</span></b>
    </summary>

<h3><b><span style="color:purple">@BeforeSuite</span></b></h3>

In TestNG, the `@BeforeSuite` annotation is used to mark a method that should be executed before the suite of tests starts.
This method is executed only once before any test in the suite is run.

Some common tasks that can be performed in the `@BeforeSuite` method include:

1. Setting up the test environment: You can initialize the necessary resources or configurations required for the test suite.

2. Loading test data: You can load test data from external sources like databases or files and prepare it for use in the tests.

3. Starting external services: If your tests require any external services like databases, web servers, or message queues, you can start them in the `@BeforeSuite` method.

4. Logging or reporting: You can initialize logging or reporting frameworks to capture detailed information about the test suite execution.

<h3><b><span style="color:purple">@BeforeClass</span></b></h3>

In TestNG, the `@BeforeClass` annotation is used to mark a method that should be executed before the first test method in a test class is run.
This method is executed only once per test class.

Some common tasks that can be performed in the `@BeforeClass` method include:

1. Setting up the test class: You can initialize any necessary resources or configurations required for the test class.

2. Loading test data: You can load test data from external sources like databases or files and prepare it for use in the tests.

3. Creating test objects: You can create and configure any test objects or fixtures that are required by the test methods in the class.

4. Starting external services: If your tests require any external services like databases, web servers, or message queues, you can start them in the `@BeforeClass` method.

5. Logging or reporting: You can initialize logging or reporting frameworks to capture detailed information about the test class execution.

<h3><b><span style="color:purple">@BeforeMethod</span></b></h3>

The `@BeforeMethod` is executed before every test method.

Some common tasks that can be performed in the `@BeforeMethod` method include:

1. Setting up test data: You can initialize or reset test data that is specific to each test method.

2. Creating test objects: You can create and configure any test objects or fixtures that are required by each test method.

3. Preparing the test environment: You can perform any necessary setup or configuration specific to each test method.

4. Logging or reporting: You can initialize logging or reporting frameworks to capture detailed information about each test method execution.

5. Resetting state: If any state needs to be reset between test methods, you can do so in the `@BeforeMethod` method.


> We can do following things in before methods:
* Setting up the test environment
* Loading test data
* Creating test objects
* Initialize driver
* Set webdriver properties
* Logging detailed information about the test execution
* Resetting state
* Create connections
* Open some URL / login
  etc

<h3><b><span style="color:purple">@AfterMethod</span></b></h3>

`@AfterMethod` will always run after every test method regardless if test failed or passed.
What kind of things we can do:
* take screenshot
* generate report
* log out
* close/quit browser
* close connections (related to JDBC)

For these method we always use public void methods. No static.

</details>

<h4><b><span style="color:purple">What is the execution order of before and after annotations?</span></b></h4>

```
Before suite
  Before class
    Before method
      @Test
    After method
  After class
After suite
```

<h2><b><span style="color:darkred">@Test</span></b></h2>

`@Test` annotation turns a method into a test and we can run this method without requiring a main method. It can take several optional parameters to customize the test behavior.

#### Example: Basic Usage
```java
public class Demo {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        // ...
    }

    @Test(
            enabled = true,                                 // to disable the test, set to false
            dependsOnMethods = {"initTest"},                // this test depends on initTest
            invocationCount = 5,                            // run the test 5 times
            timeOut = 1000,                                 // test will fail if it takes more than 1 second
            expectedExceptions = {IOException.class},       // test will pass if IOException is thrown
            groups = {"Group A"},                           // test belongs to Group A
            description = "Title verification",             // test description
            priority = 1                                    // test priority (default is 0)   
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
```

<details>
    <summary>
        <b><span style="color:darkred">Annotations</span></b>
        <b><span style="color:black"> (more detailed)</span></b>
    </summary>

* **dependsOnMethods**: Specifies the methods that a test method depends on.
```java
@Test(dependsOnMethods = { "initTest" }, enabled = true)
public void testMethod() {
System.out.println("This test depends on initTest");
}
```
* **enabled**: You can disable a test method or an entire test class by setting the enabled attribute to false.
```java
// @Test(enabled = false)
public class B {

    @Test(enabled = false)
    public void btest1() {
        System.out.println("B.btest1");
    }
}
``` 
* **invocationCount**: Number of times this test method should be invoked.
```java
@Test(invocationCount = 5)
    public void repeatTest() {
    System.out.println("This test will run 5 times");
}
``` 
* **timeOut**: Maximum number of milliseconds this test method should take.
```java
@Test(timeOut = 1000)
public void timeOutTest() throws InterruptedException {
    Thread.sleep(500);
    System.out.println("This test has a timeout of 1000 milliseconds");
}
```
* **expectedExceptions**: The expected exception that this test method should throw.
```java
@Test(expectedExceptions = { IOException.class })
public void testIOException() throws IOException {
    throw new IOException("This is a test exception");
}
```
* **groups**: The groups this test method belongs to.
```java
@Test(groups = { "Group A" })
public void testGroupA() {
System.out.println("This test belongs to Group A");
}
```
* **priority**: The priority of this test method.
* **description**: The description of this test method.
```java
@Test(description = "Title verification", priority = 1)
public void verifyTitle() {
    System.out.println("Verifying title");
}
```

* **alwaysRun**: If set to true, this test method will always run even if some methods fail.
```java
@BeforeSuite(alwaysRun = true)
public void beforeSuite() {
System.out.println("Before Suite - always runs");
}

@AfterSuite(alwaysRun = true)
public void afterSuite() {
System.out.println("After Suite - always runs");
}
```

> Where to use **(alwaysRun = false)**?

It is used to avoid unnecessary execution like:

```java
@BeforeMethod(alwaysRun = false)
public void conditionalSetup() {
    System.out.println("This setup runs only if dependent methods succeed");
}
```
or
```java
@AfterTest(alwaysRun = false)
public void cleanup() {
    System.out.println("Cleanup runs only if tests succeed");
}
```
</details>

<h2><b><span style="color:purple">@Parameters</span></b></h2>

* The `@Parameters` annotation is used to pass parameters to test methods. 
* These parameters are typically defined in the TestNG XML configuration file. 
* This allows to run the same test method with different sets of data.

### How to Use `@Parameters`

1. **Define Parameters in TestNG XML**:

   ```xml
   <suite name="Suite">
       <test name="Test">
           <parameter name="username" value="test_user"/>
           <parameter name="password" value="test_password"/>
           <classes>
               <class name="Test.usingParameters.TestP"/>
           </classes>
       </test>
   </suite>
   ```

2. **Annotate Test Method with `@Parameters`**: 
* The parameters should match the names defined in the XML file.

   ```java
   @Parameters({ "username", "password" })
   @Test
   public void parametersTest(String username, String password) {
       
   }
   ```

> <span style="color:blue">The test method annotated with `@Parameters` can only run through the TestNG XML file because the parameters are defined there.</span>
> <span style="color:blue">The XML file provides the values for these parameters when the test is executed.</span>

<h2><b><span style="color:purple">dataProvider</span></b></h2>

* The @DataProvider annotation is used to provide test data to the tests.
* It allows you to separate the test data from the test logic.
* With @DataProvider, your test data can be reusable.
* Basically we have a method annotated with @DataProvider that returns a two dimensional array object containing the data.
* We specify a name for our data provider, and we it to link the dataprovider to the test.

Example of usage:

* **dataProvider**: The name of the data provider for this test method.
* **dataProviderClass**: The class where the data provider is defined.

<h4><b><span style="color:darkred">Option 1: Using @DataProvider in the same class</span></b></h4>

```java
@Test(dataProvider = "data-provider")
public void testDataProvider(String username, String password) {
    loginPage.login(username, password);
}

@DataProvider(name = "data-provider")
public Object[][] dpMethod() {
    return new Object[][]{{"standard_user", "secret_sauce"}};
}
```

```java
@DataProvider(name = "loginData")
public Object[][] provideLoginData() {
    return new Object[][] {
        { "user1", "password1" },
        { "user2", "password2" },
        { "user3", "password3" }
    };
}

@Test(dataProvider = "loginData")
public void testLogin(String username, String password) {
    // ...
}
```

<h4><b><span style="color:darkred">Option 2: Using @DataProvider from another class (BaseDataProvider)</span></b></h4>

In our framework, we have also a `BaseDataProvider` class where we store the test data.
This class is **extended** by the `BaseTest` class.

```java
@Test(dataProvider = "valid-authentication", dataProviderClass = BaseDataProvider.class)
public void loginToTheWebsite(String username, String password) {
    loginPage.login(username, password);
}
```