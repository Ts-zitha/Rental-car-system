package com.springproject.rentalcar.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoValueException extends RuntimeException{

    private String message;
}
