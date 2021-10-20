package br.gov.sp.tce.concessoes.ppps.rest.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import br.gov.sp.tce.audesp.ws.exception.WsClientException;
import br.gov.sp.tce.audesp.ws.exception.WsClientFactoryException;
import br.gov.sp.tce.auth.AuthenticationService;
import br.gov.sp.tce.exception.AuthenticationException;

@Path("/login")
@Singleton
public class AuthenticationResource {
	
	@Inject
	protected AuthenticationService service;
	
	@GET()
	@Path("/{email}")
    public String login(@PathParam("email") String email) throws AuthenticationException, WsClientFactoryException, WsClientException {
		return service.authenticate(email);
    }


}
