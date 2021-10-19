package br.gov.sp.tce.auth;

import br.gov.sp.tce.exception.AuthorizationException;

public interface AuthorizationService {

	<T> T isTokenValid(String token, Class<T> clazz) throws AuthorizationException;

	void tokenOk(String token) throws AuthorizationException;

}