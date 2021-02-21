package payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository repository,
		                        OrderRepository orderRepository) {

    return args -> {
    	repository.save(new Employee("Miguel","Lacalle-Estrada", "Purchasing Director"));
        repository.save(new Employee("Jeffrey Jorgensen", "Procurement Manager"));
        repository.save(new Employee("Amanda Swaggerfish", "Press Director"));
        repository.save(new Employee("Gerald Furmanton", "General Acccounting Manager"));
        repository.save(new Employee("Lorraine", "Richards", "Human Resources"));
        repository.save(new Employee("Juliet Flanagram", "Marketing Deputy Manager"));
       repository.findAll().forEach(employee -> {
    	  log.info("Preloading " + employee);
       });
      orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
      orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
      orderRepository.save(new Order("Hp Z2 G4 WorkStation", Status.IN_PROGRESS));
      orderRepository.findAll().forEach(order -> {
        log.info("Preloading " + order);
      });

      
    };
   }
}