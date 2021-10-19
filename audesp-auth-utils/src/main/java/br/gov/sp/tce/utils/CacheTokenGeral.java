package br.gov.sp.tce.utils;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheTokenGeral {

	private long tempoCacheToken;
	private Map<String, Calendar> pilha = new ConcurrentHashMap<>();
	
	public CacheTokenGeral(ConfigurationHelper configuration) {
		tempoCacheToken = 0l;
		try {
			tempoCacheToken = Long.valueOf(configuration.getProperty(Constants.AUDESP_CACHE_TOKEN));
			log.info("Tempo de cache para os Tokens foi definido para: " + tempoCacheToken);
		} catch (Exception e) {
			tempoCacheToken = 10l;
			log.info("Erro ao tentar capturar valor para LOG do Token, Aplicando valor default de 10 segundos");
		}
	}
	
	public boolean tokenOk(String token) {
		if (pilha.containsKey(token)) {
			long tempoCriacao = calcularTempo(token);

			if (tempoCriacao < tempoCacheToken) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void removeToken(String token) {
		pilha.remove(token);
	}

	public void addToken(String token) {
		if (!pilha.containsKey(token)) {
			pilha.put(token, Calendar.getInstance());
		}
	}

	private long calcularTempo(String token) {
		long time = 0;
		long stop = Calendar.getInstance().getTimeInMillis();
		time = stop - pilha.get(token).getTimeInMillis();

		if (time < tempoCacheToken) {
			time = 0;
		}

		return (time / 1000);
	}
}
