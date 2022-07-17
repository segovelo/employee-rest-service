package payroll;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
class Employee {

  private @JsonProperty("id") @Id @GeneratedValue Long id;
  @JsonProperty("firstName")
  private String firstName;
  @JsonProperty("lastName")
  private String lastName;
  @JsonProperty("role")
  private String role;
  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
  private DateTime dob;

public Employee() {}

  public Employee(String name, String role) {
    String[] parts = name.split(" ");
    this.firstName = parts[0];
    this.lastName = parts[1];
    this.role = role;
  }

  public Employee(String firstName, String lastName, String role) {

    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
  }
  
  

  public Employee(String firstName, String lastName, String role, DateTime dob) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.role = role;
	this.dob = dob;
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
    return "Employee{"
        + "id="
        + this.id
        + ", firstName='"
        + this.firstName
        + '\''
        + ", lastName='"
        + this.lastName
        + '\''
        + ", role='"
        + this.role
        + '\''
        + '}';
  }
}
