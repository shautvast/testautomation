package nl.sander.testautomation;

import com.google.inject.Guice;
import com.google.inject.Injector;
import nl.sander.testautomation.cars.CarModule;

import static com.google.inject.util.Modules.override;

public interface UnitTest {

    Injector injector = Guice.createInjector(
            override(new CarModule()).with(new UnitTestModule()));

    /**
     * Unittest must call this to get a real (unmocked) instance that can be tested.
     * Any dependencies that can be found in a guice module will be injected.
     *
     * For now the instance class must have a default constructor.
     * @param type the type of the class
     * @param <T> generic
     * @return an instance of the specified class
     */
    default <T> T classToTest(Class<T> type) {
        try {
            T instance = type.newInstance();
            injector.injectMembers(instance);
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e); //TODO create descriptive exception
        }
    }

    /**
     * looks up a mock from the guice module.
     *
     * @param type The type of the mock
     * @param <T> The generic type of the mock
     * @return A mocked instance of the given type.
     */
    default <T> T mock(Class<T> type) {
        return injector.getInstance(type);
    }


}
