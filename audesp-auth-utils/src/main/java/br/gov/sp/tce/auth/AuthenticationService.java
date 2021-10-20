package br.gov.sp.tce.auth;

import br.gov.sp.tce.audesp.ws.exception.WsClientException;
import br.gov.sp.tce.audesp.ws.exception.WsClientFactoryException;
import br.gov.sp.tce.exception.AuthenticationException;


public interface AuthenticationService {
	
	String authenticate(String email) throws AuthenticationException, WsClientFactoryException, WsClientException;
}
