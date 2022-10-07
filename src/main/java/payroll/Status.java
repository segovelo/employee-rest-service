package payroll;

import java.util.HashMap;
import java.util.Map;

public enum Status {
  IN_PROGRESS("IN_PROGRESS"), //
  COMPLETED("COMPLETED"), //
  CANCELLED("CANCELLED");

  private final String value;
  private static final Map<String, Status> CONSTANTS = new HashMap<String, Status>();

  static {
    for (Status st : values()) {
      CONSTANTS.put(st.value, st);
    }
  }

  private Status(String val) {
    this.value = val;
  }

  @Override
  public String toString() {
    return this.value;
  }

  public static Status fromValue(String val) {
    Status constant = CONSTANTS.get(val);
    if (constant == null) {
      throw new IllegalArgumentException(val);
    } else return constant;
  }
}
