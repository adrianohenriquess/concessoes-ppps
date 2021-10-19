package br.gov.sp.tce.auth;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.gov.sp.tce.exception.AuthorizationException;
import br.gov.sp.tce.utils.AESUtil;
import br.gov.sp.tce.utils.CacheTokenGeral;
import br.gov.sp.tce.utils.ConfigurationHelper;
import br.gov.sp.tce.utils.Constants;

public class AuthorizationServiceImpl implements AuthorizationService {
	
	private Gson gson = new GsonBuilder().create();
	
	private ConfigurationHelper configuration;
	
	private CacheTokenGeral cacheTokenGeral;
	
	public AuthorizationServiceImpl(ConfigurationHelper configuration) {
		this.configuration = configuration;
	}

	@Override
	public <T> T isTokenValid(String token, Class<T> clazz) throws AuthorizationException {
		try {
			String chave = null;
			if (clazz.getCanonicalName().equals("br.gov.sp.tce.model.user.UsuarioDelegacoesVO")) {
				chave = configuration.getProperty(Constants.AUDESP_CHAVE_PRIVADA);
			} else {
				chave = configuration.getProperty(Constants.DELEGACOES_CHAVE_PRIVADA);
			}
			
			cacheTokenGeral.addToken(token);
			return gson.fromJson(AESUtil.decripta(UUID.fromString(chave), token), clazz);
		} catch (IllegalStateException e) {
			throw new AuthorizationException("Token inválido.");
		}		
	}

	@Override
	public void tokenOk(String token) throws AuthorizationException {
		if (!cacheTokenGeral.tokenOk(token)) {
			throw new AuthorizationException("Token inválido.");
		}
	}
}
