package it.uniroma2.isssr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * A RestTemplate Bean
 *
 */
@Configuration
public class RestBean {
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
