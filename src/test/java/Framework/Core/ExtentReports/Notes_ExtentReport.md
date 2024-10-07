# Implementing [Extent Report](https://www.extentreports.com/docs/versions/4/java/index.html)
## Steps:

### Step 1: Add Extent Reports dependency
```xml
<dependency>
    <groupId>com.aventstack</groupId>
    <artifactId>extentreports</artifactId>
    <version>${version}</version>
</dependency>
```

### Step 2: Create an [Extent Report Listener](ExtentTestNGITestListener.java) that initializes and manages the Extent Report

### Step 3: Use the listener in your xml file. For example:
```xml
<listeners>
    <listener class-name="Framework.Core.ExtentReports.ExtentTestNGITestListener"/>
</listeners>
```
or

### Step 4: Use the listener in your test class. For example:
```java
@Listeners({ExtentTestNGITestListener.class})
public class LoginTests extends BaseTest {
    // ...
}
```

### Step 5: Run the test and view the Extent Report in the `test-output` folder