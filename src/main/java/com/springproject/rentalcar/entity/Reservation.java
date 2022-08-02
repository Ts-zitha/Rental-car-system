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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reservationId")
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservationId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    private String reservationDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(nullable = true)
    private String reservationEndDate;

    //set after reservation End
    @Column(nullable = true)
    private Double reservationCost;

//    @Column(name = "reservation_duration_in_minutes", nullable = false)
//    private Integer reservationDuration;

    @OneToOne()
    @JoinColumn(
            referencedColumnName = "carId",
            nullable = false,
            name = "car_id"
    )
    private Car car;

    @ManyToOne()
    @JoinColumn(
            referencedColumnName = "clientId",
            nullable = false,
            name = "client_id"
    )
    private Client client;

    //Calculate reservation fee after reservation End
    public Double reservationCostCalc(Car car, String startDate,  String endDate){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
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
            Double cost = car.getStandByCostPerMinute()*difference_In_Minutes;
            return cost;
        }catch (ParseException parseException){
            throw new BusinessException(-1, parseException.getMessage());
        }
    }
}
