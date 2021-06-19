package com.kmstechnology.activitymigration;

import com.kmstechnology.activitymigration.db.migration.FlywayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

@PropertySource("classpath:META-INF/build.properties")
@SpringBootApplication(
		exclude = {
						ServletWebServerFactoryAutoConfiguration.class,
						WebMvcAutoConfiguration.class,
						FlywayAutoConfiguration.class
		})
public class ActivityMigrationApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityMigrationApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ActivityMigrationApplication.class, args);

		FlywayService flywayService = (FlywayService) ctx.getBean("flywayService");
		flywayService.migrate();

		ctx.close();
	}
}
