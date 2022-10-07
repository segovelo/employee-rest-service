package payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Value("${load.with.dob}")
  private boolean loadWithDOB;

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository, OrderRepository orderRepository) {

    return args -> {
      if (loadWithDOB) {
        log.info(
            "Preloading "
                + repository.save(
                    new Employee(
                        "Miguel",
                        "Lacalle-Estrada",
                        "Purchasing Director",
                        "1929-05-12T12:25:34")));
        log.info(
            "Preloading "
                + repository.save(
                    new Employee(
                        "Jeffrey Jorgensen", "Procurement Manager", "1993-05-12T12:25:34")));
        log.info(
            "Preloading "
                + repository.save(
                    new Employee("Amanda Kubernetes", "Press Director", "1972-05-12T12:25:34")));
        log.info(
            "Preloading "
                + repository.save(
                    new Employee(
                        "Gerald Furmanton", "General Acccounting Manager", "1963-05-12T12:25:34")));
        log.info(
            "Preloading "
                + repository.save(
                    new Employee("Lorraine Richards", "human Resources", "1991-05-12T12:25:34")));
        log.info(
            "Preloading "
                + repository.save(
                    new Employee(
                        "Juliet Flanagram", "Marketing Deputy Manager", "1994-05-12T12:25:34")));
      } else {
        log.info(
            "Preloading "
                + repository.save(new Employee("Miguel Lacalle-Estrada", "Purchasing Director")));
        log.info(
            "Preloading "
                + repository.save(new Employee("Jeffrey Jorgensen", "Procurement Manager")));
        log.info(
            "Preloading " + repository.save(new Employee("Amanda Kubernetes", "Press Director")));
        log.info(
            "Preloading "
                + repository.save(new Employee("Gerald Furmanton", "General Acccounting Manager")));
        log.info(
            "Preloading " + repository.save(new Employee("Lorraine Richards", "human Resources")));
        log.info(
            "Preloading "
                + repository.save(new Employee("Juliet Flanagram", "Marketing Deputy Manager")));
      }

      orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
      orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
      orderRepository.save(new Order("Hp Z2 G4 WorkStation", Status.IN_PROGRESS));

      orderRepository
          .findAll()
          .forEach(
              order -> {
                log.info("Preloaded " + order);
              });
    };
  }
}
