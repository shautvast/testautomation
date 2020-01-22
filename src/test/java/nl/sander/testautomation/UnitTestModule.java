package nl.sander.testautomation;

import com.google.inject.AbstractModule;
import org.mockito.Mockito;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

/**
 * mocks all classes in the SUT
 *
 * Works but could be optimized by only searching for types/annotations
 */
public class UnitTestModule extends AbstractModule {

    @Override
    @SuppressWarnings("unchecked")
    protected void configure() {
        new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("nl.sander.testautomation.cars"))
                .setScanners(new SubTypesScanner(false))).getSubTypesOf(Object.class)
                .stream()
                .map(Class.class::cast)
                .forEach(c -> bind(c).toInstance(Mockito.mock(c)));

    }
}
