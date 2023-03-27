package com.food.warehouse.exception;



public class ApiRequestException extends RuntimeException
{

    public ApiRequestException(String message ){
     super(message);
   }

}
