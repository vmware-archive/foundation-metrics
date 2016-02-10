package io.pivotal;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

@Configuration
public class SpringConfiguration {

	private static final String JMX_URL = "service:jmx:rmi://%s:%s/jndi/rmi://%s:%s/jmxrmi";

	@Bean
	public MBeanServerConnectionFactoryBean connection(@Value("${jmx.host}") String host,
			@Value("${jmx.port}") String port, @Value("${jmx.username}") String username,
			@Value("${jmx.password}") String password) {

		port = port != null ? port : "44444";
		System.out.println("PORT IS " + port);
		
		MBeanServerConnectionFactoryBean factoryBean = new MBeanServerConnectionFactoryBean();
		try {
			String serviceURL = createServiceURL(host, port);
			factoryBean.setServiceUrl(serviceURL);
			Map<String, Object> environmentMap = new HashMap<String, Object>();
			String[] credentials = { username, password };
			environmentMap.put("jmx.remote.credentials", credentials);
			factoryBean.setEnvironmentMap(environmentMap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return factoryBean;
	}

	@Bean
	CloudFoundryClient cloudFoundryClient(@Value("${cf.target}") String target, @Value("${cf.username}") String username,
			@Value("${cf.password}") String password) {
		
		CloudCredentials credentials = new CloudCredentials(username, password);
        CloudFoundryClient client = new CloudFoundryClient(credentials, getTargetURL(target), true);
        client.login();
		return client;
	}
	
	private static URL getTargetURL(String target) {
        try {
            return URI.create(target).toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException("The target URL is not valid: " + e.getMessage());
        }
    }

	private String createServiceURL(String host, String port) {
		String serviceURL = String.format(JMX_URL, host, port, host, port);
		System.out.println(serviceURL);
		return serviceURL;
	}

}
