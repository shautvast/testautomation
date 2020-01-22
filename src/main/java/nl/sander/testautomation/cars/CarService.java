package nl.sander.testautomation.cars;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Part of ultra simple Car application
 */
public class CarService {

    @Inject
    private CarDao carDao;

    public Optional<Car> getCar(long carId) {
        try {
            return Optional.of(carDao.getCar(carId));
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
