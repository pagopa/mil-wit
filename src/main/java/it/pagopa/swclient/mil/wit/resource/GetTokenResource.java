/*
 * GetTokenResource.java
 *
 * 2 ago 2024
 */
package it.pagopa.swclient.mil.wit.resource;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.wit.identity.AccessToken;
import it.pagopa.swclient.mil.wit.identity.AzureIdentityReactiveClient;
import it.pagopa.swclient.mil.wit.identity.Scope;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 *   
 * @author Antonio Tarricone
 */
@Path("/getoken")
public class GetTokenResource {
	/*
	 * 
	 */
	@RestClient
	AzureIdentityReactiveClient client;

	/**
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<AccessToken> get() {
		Log.debug("Get Token!");
		return client.getAccessToken(Scope.VAULT);
	}
}
