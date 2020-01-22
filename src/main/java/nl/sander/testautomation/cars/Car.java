package nl.sander.testautomation.cars;

import java.util.Objects;

/**
 * Part of ultra simple Car application
 */
public class Car {

    private long id;

    private String brand;
    private String model;
    private String color;
    private int year;

    public static CarBuilder builder(){
        return new CarBuilder();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                year == car.year &&
                brand.equals(car.brand) &&
                model.equals(car.model) &&
                color.equals(car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, model, color, year);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                '}';
    }

    public static class CarBuilder{
        private final Car car = new Car();

        public CarBuilder withId(long id){
            car.setId(id);
            return this;
        }

        public CarBuilder withBrand(String brand){
            car.setBrand(brand);
            return this;
        }

        public CarBuilder withModel(String model){
            car.setModel(model);
            return this;
        }

        public CarBuilder withColor(String color){
            car.setColor(color);
            return this;
        }

        public CarBuilder withYear(int year){
            car.year=year;
            return this;
        }

        public Car build(){
            return car;
        }
    }
}
