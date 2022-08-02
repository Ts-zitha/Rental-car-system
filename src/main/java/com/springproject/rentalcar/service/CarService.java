package com.springproject.rentalcar.service;

import com.springproject.rentalcar.entity.Car;

import java.util.List;

public interface CarService {

    public List<Car> getAllCars();
    public Car getCar(Long carId);
    public Car addCar(Car car, Long modelId, Long categoryId);
    public Car updateCar(Car car, Long carId);
    public Car deleteCar(Long carId);

    public List<Car> getCarByModel(String modelName);
    public List<Car> getCarByCategory(String categoryName);

    public List<Car> getAvailableCars();
    public List<Car> getReservedCars();
    public List<Car> getBookedCars();

    public List<Car> getAvailableCarsByModel(String modelName);
    public List<Car> getCarByModelAndCategory(String modelMakeName, String categoryName);

    public List<Car> getReservedCarsByModel(String modelName);
    public List<Car> getBookedCarsByModel(String modelName);

}
