/*
 * HelloResource.java
 *
 * 2 ago 2024
 */
package it.pagopa.swclient.mil.wit.resource;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * 
 * @author Antonio Tarricone
 */
@Path("/hello")
public class HelloResource {
	/**
	 * 
	 * @param requestId
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Uni<Response> get() {
		Log.debug("Hello!");
		return Uni.createFrom()
			.item(Response
				.status(Status.OK)
				.entity("Hello!")
				.build());
	}
}