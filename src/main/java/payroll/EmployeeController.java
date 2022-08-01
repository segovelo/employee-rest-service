package payroll;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static payroll.Utilities.JSON_FORMAT;

@RestController
class EmployeeController {

  private final EmployeeRepository repository;
  private final EmployeeModelAssembler assembler;
  private DateTimeFormatter fmt = DateTimeFormat.forPattern(JSON_FORMAT);
  
  EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  // Aggregate root
  //tag::get-aggregate-root[]
  @GetMapping(path = "/employees")
  CollectionModel<EntityModel<Employee>> all() {
    List<EntityModel<Employee>> employees =
        repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
    for(EntityModel<Employee> employee : employees) {
    	DateTime dt = fmt.parseDateTime(employee.getContent().getDob());
    	DateTime result = dt.minuteOfDay().setCopy("00");
    
    	employee.getContent().setDob( fmt.print(result));    	
    }
    return CollectionModel.of(
        employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
  } // end::get-aggregate-root[]

  @PostMapping(path = "/employees")
  ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {

    EntityModel<Employee> entityModel = assembler.toModel(repository.save(newEmployee));

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }
  // Single item

  @GetMapping(path = "/employees/{id}")
  EntityModel<Employee> one(@PathVariable Long id) {

    Employee employee =
        repository
            .findById(id) //
            .orElseThrow(() -> new EmployeeNotFoundException(id));

    return assembler.toModel(employee);
  }

  @PutMapping(path = "/employees/{id}")
  ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

    Employee updatedEmployee =
        repository
            .findById(id) //
            .map(
                employee -> {
                  if (newEmployee.getFirstName() != null)
                    employee.setFirstName(newEmployee.getFirstName());
                  if (newEmployee.getLastName() != null)
                    employee.setLastName(newEmployee.getLastName());
                  if (newEmployee.getRole() != null) employee.setRole(newEmployee.getRole());
                  return repository.save(employee);
                }) //
            .orElseGet(
                () -> {
                  newEmployee.setId(id);
                  return repository.save(newEmployee);
                });

    EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }

  @DeleteMapping(path = "/employees/{id}")
  ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
	  if(repository.existsById(id))
		  	repository.deleteById(id);
	  else throw new EmployeeNotFoundException(id) ;
	  return new ResponseEntity<>("Employee id: "+id+" deleted", HttpStatus.OK);
  }
}
