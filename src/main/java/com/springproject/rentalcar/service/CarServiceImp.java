package com.springproject.rentalcar.service;

import com.springproject.rentalcar.entity.Car;
import com.springproject.rentalcar.entity.Category;
import com.springproject.rentalcar.entity.Model;
import com.springproject.rentalcar.exception.BusinessException;
import com.springproject.rentalcar.exception.EmptyTableException;
import com.springproject.rentalcar.repository.CarRepository;
import com.springproject.rentalcar.repository.CategoryRepository;
import com.springproject.rentalcar.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImp implements CarService{

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Car> getAllCars() {

        List<Car> cars =  carRepository.findAll();
        if(cars.size() == 0){
            throw new EmptyTableException("There are no cars");
        }else {
            return cars;
        }
    }


    @Override
    public Car getCar(Long carId) {
        return carRepository.findById(carId).get();
    }

    @Override
    public Car addCar(Car car, Long modelId, Long categoryId) {
        if(car.getColor() == "" ){
            throw new BusinessException(400, "set valid car fields");
        }else {
            Model model = modelRepository.findById(modelId).get();
            Category category = categoryRepository.findById(categoryId).get();
            car.setModel(model);
            car.setCategory(category);
            car.setIsBookedFlag(false);
            car.setIsReservedFlag(false);
            return carRepository.save(car);
        }
    }

    @Override
    public Car updateCar(Car car, Long carId) {
        return null;
    }

    @Override
    public Car deleteCar(Long carId) {

        Car car = carRepository.findById(carId).get();
        carRepository.delete(car);
        return car;
    }

    @Override
    public List<Car> getCarByModel(String modelName) {
        Model model = modelRepository.findByModelName(modelName);
        return carRepository.findCarByModel(model);
    }

    @Override
    public List<Car> getCarByCategory(String categoryName) {
        Category category = categoryRepository.findByType(categoryName);
        return carRepository.findCarByCategory(category);
    }

    @Override
    public List<Car> getAvailableCars() {
        List<Car> carList = carRepository.getAvailableCars();

        if(carList.size()==0){
            throw new EmptyTableException("There are no available cars reservation and booking");
        }else {
            return carList;
        }
    }

    @Override
    public List<Car> getReservedCars() {
        List<Car> carList = carRepository.getReservedCars();

        if(carList.size() == 0){
            throw new EmptyTableException("There are no Reserved cars");
        }else { 
            return carList;
        }
    }

    @Override
    public List<Car> getBookedCars() {
        List<Car> carList = carRepository.getBookedCars();

        if (carList.size() == 0){
            throw new EmptyTableException("There are no Booked cars");
        }else {
            return carList;
        }
    }

    @Override
    public List<Car> getAvailableCarsByModel(String modelName) {

        Model model = modelRepository.findByModelName(modelName);

        List<Car> cars = carRepository.findAvailableCarsByModel(model);
        if (cars.size()== 0){
            throw new EmptyTableException("There are no available cars by "+modelName+" model" );
        }else {
            return cars;
        }
    }

    @Override
    public List<Car> getCarByModelAndCategory(String modelName, String categoryName) {
        Category category = categoryRepository.findByType(categoryName);
        Model model = modelRepository.findByModelName(modelName);

         List<Car> cars = carRepository.findCarByModelAndCategory(model, category);
         if(cars.size() == 0){
             throw new EmptyTableException("No cars available by "+ modelName +" and "+ categoryName);
         }else {
             return cars;
         }
    }

    @Override
    public List<Car> getReservedCarsByModel(String modelName) {
        Model model = modelRepository.findByModelName(modelName);

        List<Car> cars = carRepository.findReservedCarsByModel(model);
        if (cars.size()== 0){
            throw new EmptyTableException("There are no reserved cars by "+modelName+" model" );
        }else {
            return cars;
        }
    }

    @Override
    public List<Car> getBookedCarsByModel(String modelName) {
        Model model = modelRepository.findByModelName(modelName);

        List<Car> cars = carRepository.findBookedCarsByModel(model);
        if (cars.size()== 0){
            throw new EmptyTableException("There are no booked cars by "+modelName+" model" );
        }else {
            return cars;
        }
    }
}
