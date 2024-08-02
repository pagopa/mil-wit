/*
 * EnvResource.java
 *
 * 2 ago 2024
 */
package it.pagopa.swclient.mil.wit.resource;

import java.util.Map;

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
@Path("/env")
public class EnvResource {
	/**
	 * 
	 * @param requestId
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Uni<Response> get() {
		Log.debug("Env!");
		Map<String, String> env = System.getenv();
		return Uni.createFrom()
			.item(Response
				.status(Status.OK)
				.entity(env == null ? "Null env!" : (env.isEmpty() ? "Empty env!" : env.toString()))
				.build());
	}
}