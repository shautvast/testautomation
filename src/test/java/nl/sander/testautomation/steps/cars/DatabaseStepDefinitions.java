package nl.sander.testautomation.steps.cars;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java8.En;
import nl.sander.testautomation.Mode;
import nl.sander.testautomation.cars.Car;
import nl.sander.testautomation.cars.CarDao;
import nl.sander.testautomation.cars.CarModule;
import nl.sander.testautomation.database.Database;
import nl.sander.testautomation.steps.StepDefinitions;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class DatabaseStepDefinitions implements En {

    private CarDao carDao;

    public DatabaseStepDefinitions() {
        When("^a user enters a car with id (\\d+?): a (.+?) (.+?) (.+?), built in (\\d+?)$", (Long id, String brand, String model, String color, Integer year) -> {
            Car car = Car.builder()
                    .withId(id)
                    .withBrand(brand)
                    .withModel(model)
                    .withColor(color)
                    .withYear(year).build();

            if (StepDefinitions.testMode == Mode.DATABASE_INTEGRATION_TEST) {
                this.carDao.store(car);
            }
        });

        Then("^a new car is added to the inventory$", (DataTable dataTable) -> {
            Map<Long, Car> carRecordsById = this.carDao.getAllCars().stream().collect(Collectors.toMap(Car::getId, Function.identity()));
            dataTable.asMaps().stream()
                    .map(record -> Long.parseLong(record.get("id")))
                    .forEach(carId -> {
                        assertTrue(String.format("car with id %s not found", carId), carRecordsById.containsKey(carId));
                    });

        });
    }

    @Before(order = 100)
    public void initdatabase() throws SQLException {
        if (StepDefinitions.testMode == Mode.DATABASE_INTEGRATION_TEST) {
            createTable();

            Injector injector = Guice.createInjector(new CarModule());
            carDao = injector.getInstance(CarDao.class);
        }
    }

    private void createTable() throws SQLException {
        try (Connection connection = createConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("create table cars (" +
                    "id BIGINT not null primary key,  " +
                    "brand varchar(100), " +
                    "model varchar(100), " +
                    "color varchar(20), " +
                    "year integer)");
        }
    }

    @After
    public void dropDatabase() throws SQLException {
        if (StepDefinitions.testMode == Mode.DATABASE_INTEGRATION_TEST) {
            try (Connection connection = createConnection()) {
                Statement statement = connection.createStatement();
                statement.execute("drop table cars");
            }
        }
    }

    private Connection createConnection() throws SQLException {
        return new Database().getConnection();
    }
}
