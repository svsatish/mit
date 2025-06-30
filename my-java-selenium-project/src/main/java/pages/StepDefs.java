import io.cucumber.java.en.Given;
import io.cucumber.datatable.DataTable;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.*;

public class StepDefs {

  // 1. Inline arguments
  @Given("I login with {string} and {string}")
  public void loginWithArgs(String username, String password) {
    System.out.println("Logging in with: " + username + " / " + password);
  }

  // 2. One-row DataTable
  @Given("I login with the following credentials")
  public void loginWithTable(DataTable table) {
    Map<String, String> data = table.asMap();
    System.out.println("User: " + data.get("username") + ", Pass: " + data.get("password"));
  }

  // 3. Multi-row DataTable
  @Given("the following users exist")
  public void usersExist(DataTable table) {
    List<Map<String, String>> users = table.asMaps();
    for (Map<String, String> user : users) {
      System.out.println("User: " + user);
    }
  }

  // 4. Scenario Outline
  @Given("I login with username {string} and password {string}")
  public void loginFromExamples(String username, String password) {
    System.out.println("From Examples: " + username + " / " + password);
  }

  // 5. YAML external
  @Given("I login using {string} from {string}")
  public void loginWithYaml(String userKey, String fileName) {
    Map<String, Object> userData = TestDataUtils.loadYaml(fileName, userKey);
    System.out.println("YAML login: " + userData);
  }

  // 6. YAML + overrides
  @Given("I login using {string} from {string} with overrides")
  public void loginWithYamlOverride(String key, String file, DataTable overrides) {
    Map<String, Object> data = TestDataUtils.loadYamlWithOverrides(file, key, overrides);
    System.out.println("Merged Data: " + data);
  }
}