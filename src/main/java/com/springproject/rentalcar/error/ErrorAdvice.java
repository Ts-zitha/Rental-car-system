package com.springproject.rentalcar.error;

import com.springproject.rentalcar.exception.BusinessException;
import com.springproject.rentalcar.exception.CarNotAvailableException;
import com.springproject.rentalcar.exception.EmptyTableException;
import com.springproject.rentalcar.exception.NoValueException;
import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(EmptyTableException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiError HandleTableEmptyException(EmptyTableException emptyTableException, HttpServletRequest httpServletRequest){
        ApiError error = new ApiError( 200 ,emptyTableException.getMessage(), httpServletRequest.getServletPath());
        return error;
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError HandleNoSuchElementException(NoSuchElementException noSuchElementException, HttpServletRequest httpServletRequest){
        ApiError error = new ApiError( 404 ,noSuchElementException.getMessage(), httpServletRequest.getServletPath());
        return error;

    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleBusinessException(BusinessException businessException, HttpServletRequest httpServletRequest){
        ApiError error = new ApiError(businessException.getErrorCode(), businessException.getErrorMessage(), httpServletRequest.getServletPath());
        return error;
    }

    @ExceptionHandler(NoValueException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiError handleNoValueException(NoValueException noValueException, HttpServletRequest httpServletRequest){
        ApiError error = new ApiError(200, noValueException.getMessage(), httpServletRequest.getServletPath());
        return error;
    }

    @ExceptionHandler(PropertyValueException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handlePropertyValueException(PropertyValueException propertyValueException, HttpServletRequest httpServletRequest){
        ApiError error = new ApiError(400, "request body properties can not be null", httpServletRequest.getServletPath());
        return error;
    }

    @ExceptionHandler(CarNotAvailableException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiError handleCarNotAvailableException(CarNotAvailableException carNotAvailableException, HttpServletRequest httpServletRequest){
        ApiError error = new ApiError(200, carNotAvailableException.getMessage(), httpServletRequest.getServletPath());
        return  error;
    }
}
