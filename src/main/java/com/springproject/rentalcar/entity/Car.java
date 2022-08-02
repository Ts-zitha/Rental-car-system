package com.springproject.rentalcar.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "carId")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private String color;

    @Column(
            nullable = false
    )
    private Double rentPricePerMinute;

    @Column(
        nullable = true
    )
    private Double standByCostPerMinute;


    @ManyToOne()
    @JoinColumn(
            name = "model_id",nullable = false
    )
    private Model model;

    @ManyToOne()
    @JoinColumn(
            name = "category_id",nullable = false
    )
    private Category category;


    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Rental> rentals;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Reservation> reservations;



    @Column(name = "is_booked_flag")
    private Boolean isBookedFlag ;

    @Column(name = "is_reserved_flag")
    private Boolean isReservedFlag ;


}
