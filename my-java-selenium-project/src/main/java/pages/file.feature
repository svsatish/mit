Feature: Cucumber Data Use Cases

  Scenario: 1. Step arguments inline
    Given I login with "user1" and "pass1"

  Scenario: 2. One-row DataTable
    Given I login with the following credentials
      | username | password |
      | user2    | pass2    |

  Scenario: 3. Multi-row DataTable
    Given the following users exist
      | username | password | role  |
      | user1    | pass1    | admin |
      | user2    | pass2    | user  |

  Scenario Outline: 4. Scenario Outline with Examples
    Given I login with username "<username>" and password "<password>"
    Examples:
      | username | password |
      | userA    | passA    |
      | userB    | passB    |

  Scenario: 5. YAML external data
    Given I login using "valid_user" from "userData.yaml"

  Scenario: 6. YAML external with overrides
    Given I login using "valid_user" from "userData.yaml" with overrides
      | password         | override123 |
      | profile.city     | New York    |