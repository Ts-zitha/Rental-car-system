package com.springproject.rentalcar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.springproject.rentalcar.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "rentalId")
public class Rental {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rentalId;

    @Column(nullable = true)
    private Double rentalCost;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    private String rentDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    private String returnDate;

//    @Column(nullable = false)
//    private Long rentDuration;

    @ManyToOne()
    @JoinColumn(
            name = "client_id"
    )
    private Client client;

    @OneToOne()
    @JoinColumn(
            name = "car_id"
    )
    private Car car;

    public Double rentalCostCalc(Car car, String startDate, String endDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            Double cost = 0.0;

            Date start_date = simpleDateFormat.parse(startDate);
            Date end_date = simpleDateFormat.parse(endDate);
            // Calculate time difference
            // in milliseconds
            long difference_In_Time
                    = end_date.getTime() - start_date.getTime();

            long difference_In_Minutes
                    = (difference_In_Time
                    / (1000 * 60))
                    % 60;
             cost = car.getRentPricePerMinute()*difference_In_Minutes;
            return cost;

        }catch (ParseException parseException){
            throw new BusinessException(-1, parseException.getMessage());
        }

    }

}
