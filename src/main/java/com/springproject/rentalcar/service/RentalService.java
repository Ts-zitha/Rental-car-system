package com.springproject.rentalcar.service;

import com.springproject.rentalcar.entity.Rental;

import java.util.List;

public interface RentalService {


    public Rental createRental(Rental rental, Long clientId, Long carId);
    public List<Rental> getRentals();
    public Rental getRental(Long rentalId);
    public Rental updateRental(Rental rental, Long rentalId, Long clientId, Long carId);
    public Rental deleteRental(Long rentalId);

    //Join
    public List<Rental> getRentalByCarModel();
}
