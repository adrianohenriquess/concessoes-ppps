package br.gov.sp.tce.concessoes.ppps;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.modelmapper.ModelMapper;

/**
 *
 */
@ApplicationPath("/concessoes-ppps")
public class ConcessoesPPPsRestApplication extends Application {

    @ApplicationScoped
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
}
