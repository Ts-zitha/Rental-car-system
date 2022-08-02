package com.springproject.rentalcar.service;

import com.springproject.rentalcar.entity.Car;
import com.springproject.rentalcar.entity.Client;
import com.springproject.rentalcar.entity.Rental;
import com.springproject.rentalcar.exception.BusinessException;
import com.springproject.rentalcar.exception.CarNotAvailableException;
import com.springproject.rentalcar.exception.EmptyTableException;
import com.springproject.rentalcar.repository.CarRepository;
import com.springproject.rentalcar.repository.ClientRepository;
import com.springproject.rentalcar.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImp implements RentalService{

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public Rental createRental(Rental rental, Long clientId, Long carId) {

        if( rental.getRentDate()=="" || rental.getReturnDate()==""){
            throw new BusinessException(400, "set valid rental fields");
        }else {
            Client client = clientRepository.findById(clientId).get();
            Car car = carRepository.findById(carId).get();

            if(car.getIsReservedFlag()|| car.getIsBookedFlag()){
                throw new CarNotAvailableException("Car is not available");
            }else{
                car.setIsReservedFlag(false);
                car.setIsBookedFlag(true);
                Double rentalCost = rental.rentalCostCalc(car, rental.getRentDate(), rental.getReturnDate());

                rental.setRentalCost(rentalCost);
                rental.setCar(car);
                rental.setClient(client);

                return rentalRepository.save(rental);
            }

        }
    }



    @Override
    public List<Rental> getRentals() {
        List<Rental> rentalList = rentalRepository.findAll();

        if(rentalList.size() == 0){
            throw new EmptyTableException("No rentals available");
        }else {
            return rentalList;
        }
    }

    @Override
    public Rental getRental(Long rentalId) {
        return rentalRepository.findById(rentalId).get();
    }

    @Override
    public Rental updateRental(Rental rental, Long rentalId, Long clientId, Long carId) {

        if(rental.getRentalCost()==null||rental.getRentDate()==""||rental.getReturnDate()=="") {
            throw new BusinessException(400, "set valid rental fields");
        }else {

            Rental existingRental = rentalRepository.findById(rentalId).get();
            Client client = clientRepository.findById(clientId).get();
            Car car = carRepository.findById(carId).get();

            if( car.getIsReservedFlag() ){
                throw new CarNotAvailableException("Car not available");
            }else if(car.getIsBookedFlag() && car == existingRental.getCar()){

                existingRental.setRentDate(rental.getRentDate());
                existingRental.setReturnDate(rental.getReturnDate());
                Double rentalCost = rental.rentalCostCalc(car, rental.getRentDate(), rental.getReturnDate());
                existingRental.setRentalCost(rentalCost);
                existingRental.setClient(client);
                existingRental.setCar(car);

                return rentalRepository.save(existingRental);
            }else if(!car.getIsBookedFlag() && !car.getIsReservedFlag()){

                existingRental.setRentDate(rental.getRentDate());
                existingRental.setReturnDate(rental.getReturnDate());
                Double rentalCost = rental.rentalCostCalc(car, rental.getRentDate(), rental.getReturnDate());
                existingRental.setRentalCost(rentalCost);
                existingRental.setClient(client);
                car.setIsBookedFlag(true);
                car.setIsReservedFlag(false);
                existingRental.setCar(car);
                return  rentalRepository.save(existingRental);
            }else {
                throw new CarNotAvailableException("Car not available");
            }

        }
    }

    @Override
    public Rental deleteRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId).get();
        rentalRepository.delete(rental);

        return rental;
    }

    @Override
    public List<Rental> getRentalByCarModel() {
        return null;
    }
}
