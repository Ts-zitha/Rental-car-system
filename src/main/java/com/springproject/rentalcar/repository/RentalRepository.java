package com.springproject.rentalcar.repository;

import com.springproject.rentalcar.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

//    public List<Rental> getRental();
}
