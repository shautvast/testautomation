package nl.sander.testautomation.cars;

import nl.sander.testautomation.database.Database;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Part of ultra simple Car application
 */
public class JdbcCarDao implements CarDao {

    @Inject
    private Database database;

    @Override
    public Car getCar(long carId) throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement statement = connection.prepareStatement("select brand, model, color, year from cars where id = ?");
            statement.setLong(1, carId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Car car = new Car();
                car.setId(carId);
                car.setBrand(result.getString("brand"));
                car.setModel(result.getString("model"));
                car.setColor(result.getString("color"));
                car.setYear(result.getInt("year"));
                return car;
            } else {
                throw new IllegalArgumentException("no car found with id " + carId);
            }
        }
    }

    public void store(Car car) throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into cars (id, brand, model, color, year) " +
                    "values(?,?,?,?,?)");
            statement.setLong(1, car.getId());
            statement.setString(2, car.getBrand());
            statement.setString(3, car.getModel());
            statement.setString(4, car.getColor());
            statement.setInt(5, car.getYear());
            statement.executeUpdate();
        }
    }

    @Override
    public List<Car> getAllCars() throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement statement = connection.prepareStatement("select id, brand, model, color, year from cars");
            ResultSet result = statement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while (result.next()) {
                cars.add(Car.builder()
                        .withId(result.getLong("id"))
                        .withBrand(result.getString("brand"))
                        .withModel(result.getString("model"))
                        .withColor(result.getString("color"))
                        .withYear(result.getInt("year"))
                        .build());

            }
            return cars;
        }
    }

    private Connection createConnection() throws SQLException {
        return new Database().getConnection();
    }
}
