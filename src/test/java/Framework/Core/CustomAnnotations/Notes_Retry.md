<h1><b><span style="color:darkred">Retry Mechanism <span style="color:black">&</span> <span style="color:blue">RerunIfTestFails</span> <span style="color:black">Custom Annotation</span></span></b></h1>

## Design Description

* When running this [xml](testng.xml) file, it will be rerun the tests when they fails. 
  * If `@RerunIfTestFails` annotation is used, the test will rerun the specified number of times.
  * If the annotation is not used, the test will be rerun the number of times specified in the **configuration.properties** file (retryCount=1).
- In order to implement this, I am using 2 Retry Analyzers which are handled in **AnnotationTransformer** class.
  - **[RetryAnalyzer](../Listeners/RetryAnalyzer.java)**: Sets the retry analyzer based on the retryCount value specified in the properties file.  
  - **[CustomRetryAnalyzer](../Listeners/CustomRetryAnalyzer.java)**: Handles the retry logic when `@RerunIfTestFails` annotation is used. `AnnotationTransformer` reads the rerun value from the annotation used in the test.

* In my example, the testng.xml file is configured to run the tests in the [TestWithCustomAnnotation](TestWithCustomAnnotation.java) and [TestWithoutCustomAnnotation](TestWithoutCustomAnnotation.java) classes.
* As these classes are handled by 2 different Retry Analyzers, the behavior will be different.
  - TestWithCustomAnnotation will rerun the test 2 times more if it fails.
  - TestWithoutCustomAnnotation will rerun the test 1 time more if it fails.

## Steps

### Step 1: Create a [Custom Annotation](RerunIfTestFails.java)

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RerunIfTestFails {
    int value() default 0;
}
```

1. The annotation is declared with the **`@interface`** keyword
2. `@Target(ElementType.METHOD)` ensures that RerunIfTestFails can only be used on (test) methods
3. The **`RetentionPolicy.RUNTIME`** means that the annotation will be available to the JVM through runtime
4. The annotation has a single element named value, with a default value of 0 <br>
   The value will be used to specify the number of retries

### Step 2: Create a [RetryAnalyzer](../Listeners/RetryAnalyzer.java) class
* RetryAnalyzer class that handles the retry logic.
* It reads the `retryCount` value specified in the configuation.properties file by default. 

### Step 3: Create a [CustomRetryAnalyzer](../Listeners/CustomRetryAnalyzer.java) class
* This analyser is created to handle the retry logic when `@RerunIfTestFails` annotation is used.

### Step 4: Create an [AnnotationTransformer](../Listeners/AnnotationTransformer.java) class
* The primary purpose of this class is to set the right retry analyzer.
*  If the `@RerunIfTestFails` annotation is present, it sets the CustomRetryAnalyzer and reads the value from the annotation.
* Otherwise, it sets the RetryAnalyzer and reads the retryCount value from the configuration.properties file.

### Step 5: Create a [SuiteListener](../Listeners/SuiteListener.java) class
* SuiteListener is needed to run the tests with a custom annotation.
* The SuiteListener will dynamically add the **AnnotationTransformer** listener to the suite, enabling the retry mechanism
* So if you use `@RerunIfTestFails` annotation in test, you have to mention SuiteListener too. (Check step 6 and 7)
  * You can add it to the test class or to the test suite XML file.

### Step 6: Use the Custom Annotation in Test Methods

```java
@Listeners({SuiteListener.class})  
public class SampleTest {
    
   @RerunIfTestFails(2)
   @Test
   public void login()  {
      //  If test fails, it will be rerun 2 times more (total 3 times)
   }
}
```

### Step 7: Configure the TestNG suite XML file

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Rerun test using custom annotation" >
  <listeners>
    <listener class-name="Framework.Core.Listeners.AnnotationTransformer"/>
    <listener class-name="Framework.Core.Listeners.SuiteListener"/>
  </listeners>
  <test name="MyTest">
    <classes>
      <class name="Framework.Core.CustomAnnotations.TestWithCustomAnnotation"/>
      <class name="Framework.Core.CustomAnnotations.TestWithoutCustomAnnotation"/>
    </classes>
  </test>
</suite>
```

- TestWithCustomAnnotation will rerun the test 2 times more if it fails.
- TestWithoutCustomAnnotation will rerun the test 1 time more if it fails.

