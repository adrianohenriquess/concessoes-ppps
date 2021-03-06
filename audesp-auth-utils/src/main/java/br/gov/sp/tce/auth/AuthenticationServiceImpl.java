package br.gov.sp.tce.auth;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.gov.sp.tce.audesp.ws.client.UsuarioWsClient;
import br.gov.sp.tce.audesp.ws.client.WsClientFactory;
import br.gov.sp.tce.audesp.ws.exception.WsClientException;
import br.gov.sp.tce.audesp.ws.exception.WsClientFactoryException;
import br.gov.sp.tce.exception.AuthenticationException;
import br.gov.sp.tce.utils.AESUtil;
import br.gov.sp.tce.utils.ConfigurationHelper;
import br.gov.sp.tce.utils.Constants;

public class AuthenticationServiceImpl implements AuthenticationService {
	
	private ConfigurationHelper configuration;
	
	private Gson gson = new GsonBuilder().create();
	
	public AuthenticationServiceImpl(ConfigurationHelper configuration) {
		this.configuration = configuration;
	}

	@Override
	public String authenticate(String email) throws AuthenticationException, WsClientFactoryException, WsClientException {
		UUID audespChavePrivada = UUID.fromString(configuration.getProperty(Constants.AUDESP_CHAVE_PRIVADA));
		String token = null;
		try {
			UsuarioWsClient usuarioWsClient = WsClientFactory.getWsClient(UsuarioWsClient.class, audespChavePrivada);
			br.gov.sp.tce.audesp.ws.vo.usuario.UsuarioDelegacoesVO usuarioDelegacoes = usuarioWsClient.recuperarUsuarioFase3(email, configuration.getProperty(Constants.CODIGO_SISTEMA));
		
			if (usuarioDelegacoes == null) throw new AuthenticationException("Usuário não encontrado.");
			
			String json = gson.toJson(usuarioDelegacoes);
			token = AESUtil.encripta(audespChavePrivada, json);
		} catch (Exception e) {
			throw new AuthenticationException("Erro ao buscar credencias do usuário.", e);
		}
		return token;
	}

}
