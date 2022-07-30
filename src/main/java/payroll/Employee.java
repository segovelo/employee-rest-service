package payroll;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import static payroll.Utilities.JSON_FORMAT;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
class Employee {

  @JsonProperty("id") @Id @GeneratedValue 
  private Long id;
  @JsonProperty("firstName")
  private String firstName;
  @JsonProperty("lastName")
  private String lastName;
  @JsonProperty("role")
  private String role;
  @Lob
  @Column(name = "dob", columnDefinition="CLOB")
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=JSON_FORMAT)
  private DateTime dob;
  

public Employee() {}

	public Employee(String name, String role) {
	  String[] parts = name.split(" ");
	  this.firstName = parts[0];
	  this.lastName = parts[1];
	  this.role = role;
	}


  public Employee(String name, String role, String dob) {
	DateTimeFormatter formatter = DateTimeFormat.forPattern(JSON_FORMAT);  
    String[] parts = name.split(" ");
    this.firstName = parts[0];
    this.lastName = parts[1];
    this.role = role;
    this.dob = formatter.parseDateTime(dob);
  }
  
  public Employee(String firstName, String lastName, String role, DateTime dob) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.role = role;
	this.dob = dob;
}
  
  public Employee(String firstName, String lastName, String role, String dob) {
	DateTimeFormatter formatter = DateTimeFormat.forPattern(JSON_FORMAT);
	this.firstName = firstName;
	this.lastName = lastName;
	this.role = role;
	this.dob = formatter.parseDateTime(dob);
}


public String getName() {
    return this.firstName + " " + this.lastName;
  }

  public void setName(String name) {
    String[] parts = name.split(" ");
    this.firstName = parts[0];
    this.lastName = parts[1];
  }

  public Long getId() {
    return this.id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getRole() {
    return this.role;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setRole(String role) {
    this.role = role;
  }
  
  
//  public String getDob() {
//	DateTimeFormatter formatter = DateTimeFormat.forPattern(JSON_FORMAT);  
//	return formatter.print(dob);
//}
//
//public void setDob(String dob) {
//	DateTimeFormatter formatter = DateTimeFormat.forPattern(JSON_FORMAT);
//	this.dob = formatter.parseDateTime(dob);
//}


  public DateTime getDob() {
	return dob;
}

public void setDob(DateTime dob) {
	this.dob = dob;
}

@Override
  public boolean equals(Object o) {

    if (this == o) return true;
    if (!(o instanceof Employee)) return false;
    Employee employee = (Employee) o;
    return Objects.equals(this.id, employee.id)
        && Objects.equals(this.firstName, employee.firstName)
        && Objects.equals(this.lastName, employee.lastName)
        && Objects.equals(this.role, employee.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.firstName, this.lastName, this.role);
  }

@Override
public String toString() {
	return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", role=" + role + ", dob="
			+ dob+ "]";
}

}
