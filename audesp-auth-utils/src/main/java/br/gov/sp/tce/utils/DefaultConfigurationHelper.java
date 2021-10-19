package br.gov.sp.tce.utils;

import java.io.InputStream;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultConfigurationHelper implements ConfigurationHelper {

	private static final Properties applicationProperties;
	
	private DefaultConfigurationHelper() {}
	
	static {
		applicationProperties = loadProperties();
	}

	@Override
	public String getProperty(String property) {
		return applicationProperties.getProperty(property);
	}

	private static Properties loadProperties() {
		Properties properties = new Properties();
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
			properties.load(is);			
		} catch (Exception e) {
			log.error("Erro ao ler o arquivo de properties: ", e);
		}
		return properties;
	}
}