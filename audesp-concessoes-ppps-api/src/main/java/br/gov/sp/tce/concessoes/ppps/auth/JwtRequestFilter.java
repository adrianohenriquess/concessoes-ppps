package br.gov.sp.tce.concessoes.ppps.auth;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.http.HttpStatus;

import br.gov.sp.tce.auth.AuthorizationService;
import br.gov.sp.tce.exception.AuthorizationException;
import br.gov.sp.tce.model.user.UsuarioDelegacoesVO;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class JwtRequestFilter implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_HEADER = "Bearer ";

    @Inject
    AuthorizationService authorization;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String currentPath = requestContext.getUriInfo().getPath();
        if (currentPath != null && currentPath.startsWith("/login")) {
            log.info("token authentication ignored for path ->> " + currentPath);
            return;
        }
        UsuarioDelegacoesVO user = null;
        String authHeader = requestContext.getHeaderString(AUTHORIZATION_HEADER);
        String token = null;
        try {
            if (authHeader == null || !authHeader.startsWith(BEARER_HEADER)) {
                throw new AuthorizationException("Token não informado");
            }

            token = authHeader.substring(7);
            user = authorization.isTokenValid(token, UsuarioDelegacoesVO.class);
            if (user == null) {
                throw new AuthorizationException("Falha ao obter usuário do token");
            }

            authorization.tokenOk(token);
        } catch (AuthorizationException e) {
            log.error("erro ao autenticar usuário", e);
            requestContext.abortWith(Response.status(HttpStatus.SC_UNAUTHORIZED).build());
        }
    }

}