/*
 * AzureIdentityReactiveClient.java
 *
 * 17 mag 2024
 */
package it.pagopa.swclient.mil.wit.identity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.quarkus.logging.Log;
import io.quarkus.rest.client.reactive.ClientFormParam;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * 
 * @author Antonio Tarricone
 */
@RegisterRestClient(configKey = "azure-identity")
public interface AzureIdentityReactiveClient {
	/**
	 * 
	 * @param scope
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@ClientFormParam(name = "grant_type", value = "client_credentials")
	@ClientFormParam(name = "client_id", value = "${azure-identity.client-id}")
	@ClientFormParam(name = "client_assertion", value = "{getClientAssertion}")
	@ClientFormParam(name = "client_assertion_type", value = "urn:ietf:params:oauth:client-assertion-type:jwt-bearer")
	Uni<AccessToken> getAccessToken(@FormParam("scope") String scope);

	/**
	 * 
	 * @param headerName
	 * @return
	 */
	default String getClientAssertion(String headerName) {
		if (!headerName.equals("client_assertion")) {
			throw new IllegalArgumentException("Header name must be client_assertion");
		}

		try {
			return new String(
				Files.readAllBytes(
					Paths.get(
						ConfigProvider.getConfig()
							.getValue(
								"azure-identity.token-file",
								String.class))),
				StandardCharsets.UTF_8);
		} catch (IOException e) {
			Log.errorf(e, "Error reading Azure federated token file");
			throw new RuntimeException("Error reading Azure federated token file", e); // NOSONAR
		}
	}
}
