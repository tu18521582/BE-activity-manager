package com.kmstechnology.activitymigration.db.migration;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service("flywayService")
public class FlywayServiceImpl implements FlywayService {
  private static final Logger LOGGER = LoggerFactory.getLogger(FlywayServiceImpl.class);

  @Autowired private DataSource dataSource;
  @Autowired private FlywayConfig flywayConfig;


  @Override
  public void migrate() {
    LOGGER.info("\n\n=============================== \n" +
            "Start running flyway... \n");

    int totalMigrations = Flyway.configure()
            .dataSource(dataSource)
            .mixed(flywayConfig.isMixed())
            .baselineOnMigrate(flywayConfig.isBaselineOnMigrate())
            .placeholders(flywayConfig.getPlaceholders())
            .schemas(flywayConfig.getSchemas().toArray(new String[0]))
            .load()
            .migrate();

    LOGGER.info("\n\n=============================== \n" +
            "Done Migration. {} migrations executed \n", totalMigrations);
  }
}