package payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class EmployeeRestServiceApplication {

  private static Logger logger = LoggerFactory.getLogger(EmployeeRestServiceApplication.class);

  public static void main(String[] args) {
    try {
      ApplicationContext ctx = SpringApplication.run(EmployeeRestServiceApplication.class, args);
      Environment environment = ctx.getEnvironment();

      logger.info("Employee-Rest-Service Application started successfully !");
      logger.info(
          "Application listening on port: {}", environment.getProperty("local.server.port"));
    } catch (Exception ex) {
      logger.error("Employee-Rest-Service Application failed :( *** ", ex);
      throw new RuntimeException();
    }
  }
}
