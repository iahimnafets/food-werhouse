package com.food.warehouse.exception;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ApiExceptionHandler implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException exception) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(exception.getMessage())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
   }
}
