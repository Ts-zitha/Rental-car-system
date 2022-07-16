package com.springproject.rentalcar.error;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ApiError {

    private int status;
    private String message;
    private long timestamp;

    public ApiError(int status, String message){
        this.timestamp = new Date().getTime();
        this.message = message;
    }

}
