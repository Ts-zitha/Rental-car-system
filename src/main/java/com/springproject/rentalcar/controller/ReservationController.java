package com.springproject.rentalcar.controller;

import com.springproject.rentalcar.custom.response.ResponseBody;
import com.springproject.rentalcar.entity.Rental;
import com.springproject.rentalcar.entity.Reservation;
import com.springproject.rentalcar.entity.UpdateReservedToBooked;
import com.springproject.rentalcar.service.ReservationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationServiceImp reservationServiceImp;

    @GetMapping()
    public List<Reservation> findAllReservations(){
        return reservationServiceImp.fetchReservations();
    }


    @PostMapping("/{clientId}/{carId}")
    public com.springproject.rentalcar.custom.response.ResponseBody addRental(@RequestBody Reservation reservation, @PathVariable("clientId") Long clientId, @PathVariable("carId") Long carId){
        Reservation reservation1 = reservationServiceImp.createReservation(reservation, clientId, carId);
        return new com.springproject.rentalcar.custom.response.ResponseBody(reservation1, "Rental created");
    }

    @PostMapping("/update_reservation/{reservationId}/{clientId}/{carId}")
    public ResponseBody updateReservation(@RequestBody Reservation reservation, @PathVariable("reservationId") Long reservationId, @PathVariable("clientId") Long clientId, @PathVariable("carId") Long carId){
        Reservation reservation1 = reservationServiceImp.updateReservation(reservation, reservationId, clientId, carId);
        return new ResponseBody(reservation1, "Reservation updated");
    }

    @DeleteMapping("/{reservationId}")
    public ResponseBody deleteReservation(@PathVariable("reservation") Long reservationId){
        Reservation reservation = reservationServiceImp.deleteReservation(reservationId);
        return new ResponseBody(reservation, "Reservation deleted");
    }

    @PostMapping("/updateReservedToBooked/{reservationId}")
    public String updateToBooked(@RequestBody UpdateReservedToBooked updateReservedToBooked,@PathVariable("reservationId") Long reservationId){
        return reservationServiceImp.updateReservedToBooked(updateReservedToBooked, reservationId);
    }
}
