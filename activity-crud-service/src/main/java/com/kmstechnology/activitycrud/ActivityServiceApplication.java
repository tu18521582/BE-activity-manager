package com.kmstechnology.activitycrud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@PropertySource("classpath:META-INF/build.properties")
@EnableJpaAuditing
@SpringBootApplication
public class ActivityServiceApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityServiceApplication.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ActivityServiceApplication.class, args);

		Environment env = ctx.getEnvironment();
		String port = env.getProperty("server.port");
		String[] activeProfiles = env.getActiveProfiles();
		String name = env.getProperty("info.build.name");
		String version = env.getProperty("info.build.version");
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(
							"\n************************************************************************\n"
											+ "\t"
											+ name
											+ ":"
											+ version
											+ "\n"
											+ "\tListening on port: {}\n"
											+ "\tActive profiles  : {}\n"
											+ "************************************************************************",
							port,
							activeProfiles);
		}
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
