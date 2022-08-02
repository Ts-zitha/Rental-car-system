package com.springproject.rentalcar.service;

import com.springproject.rentalcar.entity.*;
import com.springproject.rentalcar.exception.BusinessException;
import com.springproject.rentalcar.exception.CarNotAvailableException;
import com.springproject.rentalcar.exception.EmptyTableException;
import com.springproject.rentalcar.repository.CarRepository;
import com.springproject.rentalcar.repository.ClientRepository;
import com.springproject.rentalcar.repository.RentalRepository;
import com.springproject.rentalcar.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReservationServiceImp implements ReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarRepository carRepository;

    private RentalRepository rentalRepository;

    @Override
    public List<Reservation> fetchReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();
        if(reservationList.size() == 0){
            throw new EmptyTableException("No reservations available");
        }else {
            return reservationList;
        }
    }

    @Override
    public Reservation createReservation(Reservation reservation, Long clientId, Long carId) {
        if( reservation.getReservationDate()==""|| reservation.getReservationEndDate()==""){
            throw new BusinessException(400, "set valid reservation fields");
        }else {
            Client client = clientRepository.findById(clientId).get();
            Car car = carRepository.findById(carId).get();

            if(car.getIsBookedFlag() || car.getIsReservedFlag()){
                throw new CarNotAvailableException("Car is not available for any booking or reservations ");
            }else {
                Double cost =0.0;
                car.setIsReservedFlag(true);
                car.setIsBookedFlag(false);

                reservation.setCar(car);
                reservation.setClient(client);
                cost = reservation.reservationCostCalc(car, reservation.getReservationDate(), reservation.getReservationEndDate());
                reservation.setReservationCost(cost);
                return reservationRepository.save(reservation);
            }

        }
    }

    @Override
    public Reservation getReservation(Long reservationId) {
        return reservationRepository.findById(reservationId).get();
    }

    @Override
    public Reservation updateReservation(Reservation reservation, Long reservationId ,Long clientId, Long carId) {
        if(reservation.getReservationDate()==null||reservation.getReservationEndDate()=="") {
            throw new BusinessException(400, "set valid reservation fields");
        }else {
            Reservation existingReservation = reservationRepository.findById(reservationId).get();
            Client client = clientRepository.findById(clientId).get();
            Car car = carRepository.findById(carId).get();

            existingReservation.setReservationDate(reservation.getReservationDate());
            existingReservation.setReservationEndDate(reservation.getReservationEndDate());
            Double reservationCost = reservation.reservationCostCalc(car, reservation.getReservationDate(), reservation.getReservationEndDate());
            reservation.setReservationCost(reservationCost);
            return  reservationRepository.save(existingReservation);
        }
    }

    @Override
    public Reservation deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).get();
        reservationRepository.delete(reservation);

        return reservation;
    }

    public String updateReservedToBooked(UpdateReservedToBooked updateReservedToBooked, Long reservationId){
        Reservation reservation = reservationRepository.findById(reservationId).get();
        reservationRepository.delete(reservation);
        Rental rental = new Rental();
        rental.setRentDate(updateReservedToBooked.getRentDate());
        rental.setReturnDate(updateReservedToBooked.getReturnDate());
        Double cost = rental.rentalCostCalc(reservation.getCar(), updateReservedToBooked.getRentDate(), updateReservedToBooked.getReturnDate());
        rental.setRentalCost(cost);
        rental.setCar(reservation.getCar());
        rental.setClient(reservation.getClient());

        rentalRepository.save(rental);
        return "Reservation update to Rental";

    }
}
