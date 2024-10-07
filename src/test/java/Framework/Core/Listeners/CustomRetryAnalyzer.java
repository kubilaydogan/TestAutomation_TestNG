package Framework.Core.Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class CustomRetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private final int maxRetryCount;

    public CustomRetryAnalyzer() {
        this.maxRetryCount = AnnotationTransformer.retryCount;
    }

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}