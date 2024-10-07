package Framework.Core.Listeners;

import org.testng.IAlterSuiteListener;
import org.testng.IAnnotationTransformer;
import org.testng.xml.XmlSuite;

import java.util.List;

public class SuiteListener implements IAlterSuiteListener {
    @Override
    public void alter(List<XmlSuite> suites) {
        for (XmlSuite suite : suites) {
            suite.addListener(AnnotationTransformer.class.getName());
        }
    }
}
