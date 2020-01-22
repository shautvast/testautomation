package nl.sander.testautomation.tests.cars;

import nl.sander.testautomation.UnitTest;
import nl.sander.testautomation.cars.Car;
import nl.sander.testautomation.cars.CarDao;
import nl.sander.testautomation.cars.CarService;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Example for a pure unit test
 */
public class CarServiceTest implements UnitTest {

    /* lookup mock from guice injector. This is only to set the When for this test */
    private final CarDao carDao = mock(CarDao.class); //TODO we might be able to use the @Mock annotation to do the same

    /* lookup actual instance. All dependencies with @Inject are automatically injected with mocks */
    //TODO we might be able to use the @InjectMocks annotation to do the same
    private final CarService carService = classToTest(CarService.class);

    @Test
    public void testGetCar() throws SQLException {
        when(carDao.getCar(10)).thenReturn(Car.builder().withColor("blue").build());

        Optional<Car> car = carService.getCar(10);

        assertTrue(car.isPresent());
        assertEquals("blue", car.get().getColor());
    }
}
