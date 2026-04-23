package com.campus.mapper;

import com.campus.exception.RoomNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RoomNotFoundMapper implements ExceptionMapper<RoomNotFoundException> {

    @Override
    public Response toResponse(RoomNotFoundException ex) {

        return Response.status(Response.Status.NOT_FOUND)
                .entity(ex.getMessage())
                .type("text/plain")
                .build();
    }
}