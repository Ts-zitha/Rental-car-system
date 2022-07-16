package com.springproject.rentalcar.error;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ApiError {

    private int status;
    private String message;
    private Date timestamp;
    private String path;

    public ApiError(int status, String message,String path){
        this.timestamp = new Date();
        this.message = message;
        this.status = status;
        this.path = path;
    }

}
