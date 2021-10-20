package br.gov.sp.tce.concessoes.ppps;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.modelmapper.ModelMapper;

import br.gov.sp.tce.auth.AuthenticationService;
import br.gov.sp.tce.auth.AuthenticationServiceImpl;
import br.gov.sp.tce.auth.AuthorizationService;
import br.gov.sp.tce.auth.AuthorizationServiceImpl;
import br.gov.sp.tce.utils.CacheTokenGeral;
import br.gov.sp.tce.utils.ConfigurationHelper;

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
    public ConfigurationHelper configurationHelper() {
        return new ConfigurationHelper(){
            @Override
            public String getProperty(String value) {
                Config config = ConfigProvider.getConfig();
                return config.getValue(value, String.class);
            }
        };
    }

    @ApplicationScoped
    public AuthorizationService authorization(ConfigurationHelper configurationHelper) {
        return new AuthorizationServiceImpl(configurationHelper, new CacheTokenGeral(configurationHelper));
    }
    
    @ApplicationScoped
    public AuthenticationService authentication(ConfigurationHelper configurationHelper) {
        return new AuthenticationServiceImpl(configurationHelper);
    }
    
}
