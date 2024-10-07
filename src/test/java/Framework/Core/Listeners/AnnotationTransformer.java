package Framework.Core.Listeners;

import Framework.Core.CustomAnnotations.RerunIfTestFails;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {
    public static int retryCount = 0;

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        RerunIfTestFails rerun = testMethod.getAnnotation(RerunIfTestFails.class);
        if (rerun != null) {
            retryCount = rerun.value();
            annotation.setRetryAnalyzer(CustomRetryAnalyzer.class);
        } else {
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
        }
    }

}