package nl.sander.testautomation.cars;

import com.google.inject.AbstractModule;

/**
 * Part of ultra simple Car application
 */
public class CarModule extends AbstractModule {

    @Override
    protected void configure(){
        bind(CarDao.class).to(JdbcCarDao.class);
        bind(CarService.class).toInstance(new CarService());
    }
}
