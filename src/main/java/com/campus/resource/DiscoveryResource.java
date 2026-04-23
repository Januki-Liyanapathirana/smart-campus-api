package com.campus.resource;

/**
 *
 * @author JANUKI
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)  
public class DiscoveryResource {

    @GET
    public Map<String, Object> getInfo() {

        Map<String, Object> data = new HashMap<>();

        data.put("name", "Smart Campus API");
        data.put("version", "v1");
        data.put("rooms", "/api/v1/rooms");
        data.put("sensors", "/api/v1/sensors");
        data.put("readings", "/api/v1/readings");

        return data;
    }
}
