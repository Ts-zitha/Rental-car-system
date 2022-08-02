package com.springproject.rentalcar.controller;

import com.springproject.rentalcar.custom.response.ResponseBody;
import com.springproject.rentalcar.entity.Car;
import com.springproject.rentalcar.service.CarServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarServiceImp carServiceImp;


    //get all cars
    @GetMapping()
    public List<Car> fetchCars(){
        return carServiceImp.getAllCars();
    }

    //add car
    @PostMapping("/{modelId}/{categoryId}")
    public ResponseBody addCar(@RequestBody Car car, @PathVariable("modelId") Long modelId, @PathVariable("categoryId") Long categoryId){
       Car savedCar = carServiceImp.addCar(car, modelId, categoryId);
       return new ResponseBody(savedCar, "Car saved to DB");
    }

    //get car by id
    @GetMapping("/{carId}")
    public Car getCar(@PathVariable("carId") Long carId){
        return carServiceImp.getCar(carId);
    }

    //delete car
    @DeleteMapping("/{carId}")
    public ResponseBody deleteCar(@PathVariable("carId") Long carId){
        Car car = carServiceImp.deleteCar(carId);
        return new ResponseBody(car, "Car deleted");
    }

    //get Cars By Category
    @GetMapping("/category/{categoryName}/cars")
    public List<Car> getCarByCategory(@PathVariable("categoryName")String categoryName){
        return carServiceImp.getCarByCategory(categoryName);
    }

    //get Cars By Model
    @GetMapping("/model/{modelMake}/cars")
    public List<Car> getCarByModel(@PathVariable("modelMake") String modelMakeName){
        return  carServiceImp.getCarByModel(modelMakeName);
    }

    //get available cars
    @GetMapping("/available/cars")
    public List<Car> getAvailableCars(){
        return carServiceImp.getAvailableCars();
    }

    //get reserved cars
    @GetMapping("/reserved/cars")
    public List<Car> fetchReservedCars(){
        return carServiceImp.getReservedCars();
    }

    //get booked cars
    @GetMapping("/booked/cars")
    public List<Car> fetchBookedCars(){
        return carServiceImp.getBookedCars();
    }

    //get available cars by model
    @GetMapping("/availableByModel/{modelName}")
    public List<Car> fetchAvailableCarsByModel(@PathVariable("modelName") String modelName){
        return carServiceImp.getAvailableCarsByModel(modelName);
    }

    //get Cars By Model and Category
    @GetMapping("/{modelName}/{categoryName}/cars")
    public List<Car> getCarByModelAndCategory(@PathVariable("modelName") String modelName, @PathVariable("categoryName") String categoryName){
        return carServiceImp.getCarByModelAndCategory(modelName, categoryName);
    }

    //get reserved cars by model
    @GetMapping("/reserved/{modelName}")
    public List<Car> fetchReservedCarsByModel(@PathVariable("modelName") String modelName){
        return carServiceImp.getReservedCarsByModel(modelName);
    }

    //get booked cars by model
    @GetMapping("/booked/{modelName}")
    public List<Car> fetchBookedCarsByModel(@PathVariable("modelName") String modelName){
        return carServiceImp.getBookedCarsByModel(modelName);
    }
}

