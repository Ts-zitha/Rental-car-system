package com.springproject.rentalcar.controller;

import com.springproject.rentalcar.custom.response.ResponseBody;
import com.springproject.rentalcar.entity.Rental;
import com.springproject.rentalcar.service.RentalServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class RentalController {

    @Autowired
    private RentalServiceImp rentalServiceImp;

    @GetMapping()
    public List<Rental> fetchAllRentals(){
        return rentalServiceImp.getRentals();
    }

    @PostMapping("/{clientId}/{carId}")
    public ResponseBody addRental(@RequestBody Rental rental,@PathVariable("clientId") Long clientId, @PathVariable("carId") Long carId){
        Rental rental1 = rentalServiceImp.createRental(rental, clientId, carId);
        return new ResponseBody(rental1, "Rental created");
    }

    @GetMapping("/{rentalId}")
    public Rental getRental(@PathVariable("rentalId") Long rentalId){
        return rentalServiceImp.getRental(rentalId);
    }

    @PostMapping("/update_rental/{rentalId}/{clientId}/{carId}/rental")
    public ResponseBody updateRental(@RequestBody Rental rental, @PathVariable("rentalId") Long rentalId,@PathVariable("clientId") Long clientId,@PathVariable("carId") Long carId){
        Rental rental1 =  rentalServiceImp.updateRental(rental, rentalId, clientId, carId);
        return new ResponseBody(rental1, "Rental updated");
    }

    @DeleteMapping("/{rentalId}")
    public ResponseBody deleteRental(@PathVariable Long rentalId){
        Rental rental = rentalServiceImp.deleteRental(rentalId);
        return new ResponseBody(rental, "Rental deleted");
    }
}
