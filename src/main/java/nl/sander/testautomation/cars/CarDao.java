package nl.sander.testautomation.cars;

import java.sql.SQLException;
import java.util.List;

public interface CarDao {

    Car getCar(long carId) throws SQLException;

    List<Car> getAllCars() throws SQLException;

    void store(Car car) throws SQLException;
}
