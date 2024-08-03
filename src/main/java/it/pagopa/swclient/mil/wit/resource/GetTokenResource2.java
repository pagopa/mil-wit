/*
 * GetTokenResource.java
 *
 * 2 ago 2024
 */
package it.pagopa.swclient.mil.wit.resource;

import java.net.URI;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.logging.Log;
import io.quarkus.rest.client.reactive.QuarkusRestClientBuilder;
import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.wit.identity.AccessToken;
import it.pagopa.swclient.mil.wit.identity.AzureIdentityReactiveClient2;
import it.pagopa.swclient.mil.wit.identity.Scope;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * 
 * @author Antonio Tarricone
 */
@Path("/getoken2")
public class GetTokenResource2 {
	/*
	 * 
	 */
	AzureIdentityReactiveClient2 client;

	@Inject
	GetTokenResource2(
		@ConfigProperty(name = "AZURE_AUTHORITY_HOST") String host,
		@ConfigProperty(name = "AZURE_TENANT_ID") String tenantId) {
		client = QuarkusRestClientBuilder.newBuilder()
			.baseUri(URI.create(host + tenantId))
			.build(AzureIdentityReactiveClient2.class);
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Uni<AccessToken> get() {
		Log.debug("Get Token 2!");
		return client.getAccessToken(Scope.VAULT);
	}
}
