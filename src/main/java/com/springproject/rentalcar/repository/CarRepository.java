package com.springproject.rentalcar.repository;

import com.springproject.rentalcar.entity.Car;

import com.springproject.rentalcar.entity.Category;
import com.springproject.rentalcar.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository  extends JpaRepository<Car, Long> {

    public List<Car> findCarByModel(Model model);
    public List<Car> findCarByCategory(Category category);
    public List<Car> findCarByModelAndCategory( Model model, Category category);

    @Query("select s from Car s where s.isBookedFlag=false AND s.isReservedFlag=false")
    public List<Car> getAvailableCars();

    @Query("select s from Car s where  s.isReservedFlag=true")
    public List<Car> getReservedCars();

    @Query("select s from Car s where s.isBookedFlag=true")
    public List<Car> getBookedCars();

    @Query("select s from Car s where s.isBookedFlag=false AND s.isReservedFlag=false AND s.model=?1")
    public List<Car> findAvailableCarsByModel(Model model);

    @Query("select s from Car s where s.isReservedFlag=true AND s.model=?1")
    public List<Car> findReservedCarsByModel(Model model);

    @Query("select s from Car s where s.isBookedFlag=true AND s.model=?1")
    public List<Car> findBookedCarsByModel(Model model);
}
