package payroll;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.core.type.TypeReference;

@SpringBootTest
class EmployeeRestServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCreateObjectFromFile() {
		TypeReference<Employee> employeeType = new TypeReference<Employee>() {};
		Employee employee = Utilities.createObjectFromFile(employeeType, "scr/test/resources/mockrequests/mockEmployee.json"); 
		System.out.println("\n\n\t  Employee  "+ employee.toString());
		assertNotNull(employee);
	}

}
