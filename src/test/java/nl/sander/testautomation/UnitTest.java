package nl.sander.testautomation;

import com.google.inject.Guice;
import com.google.inject.Injector;
import nl.sander.testautomation.cars.CarModule;

import static com.google.inject.util.Modules.override;

public interface UnitTest {

    Injector injector = Guice.createInjector(
            override(new CarModule()).with(new UnitTestModule()));

    default <T> T classToTest(Class<T> type) {
        try {
            T instance = type.newInstance();
            injector.injectMembers(instance);
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    default <T> T mock(Class<T> type) {
        return injector.getInstance(type);
    }


}
