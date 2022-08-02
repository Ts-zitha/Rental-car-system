package com.springproject.rentalcar.service;

import com.springproject.rentalcar.entity.Client;
import com.springproject.rentalcar.entity.Reservation;

import java.util.List;

public interface ReservationService {
    public List<Reservation> fetchReservations();
    public Reservation createReservation(Reservation reservation, Long clientId, Long carId);
    public Reservation getReservation(Long reservationId);
    public Reservation updateReservation(Reservation reservation, Long reservationId, Long clientId, Long carId);
    public Reservation deleteReservation(Long reservationId);
}
