package br.gov.sp.tce.concessoes.ppps;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.modelmapper.ModelMapper;

import br.gov.sp.tce.auth.AuthorizationService;
import br.gov.sp.tce.auth.AuthorizationServiceImpl;

/**
 *
 */
@ApplicationPath("/concessoes-ppps")
public class ConcessoesPPPsRestApplication extends Application {

    @ApplicationScoped
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @ApplicationScoped
    public AuthorizationService authorization() {
        return new AuthorizationServiceImpl();
    }
    
}
