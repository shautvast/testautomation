package nl.sander.testautomation.tests.cars;

import nl.sander.testautomation.cars.Car;
import nl.sander.testautomation.cars.CarDao;
import nl.sander.testautomation.cars.CarService;
import nl.sander.testautomation.UnitTest;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CarServiceTest implements UnitTest {

    private final CarDao carDao = mock(CarDao.class);
    private final CarService carService = classToTest(CarService.class);

    @Test
    public void testGetCar() throws SQLException {
        when(carDao.getCar(10)).thenReturn(Car.builder().withColor("blue").build());

        Optional<Car> car = carService.getCar(10);

        assertTrue(car.isPresent());
        assertEquals("blue", car.get().getColor());
    }
}
