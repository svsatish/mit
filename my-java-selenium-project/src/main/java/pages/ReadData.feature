Feature: Read test data from YAML files

  Scenario Outline: Read specific key from YAML node
    Given I read key "<keyPath>" from "<node>" in file "<fileName>"

    Examples:
      | fileName      | node        | keyPath               |
      | userData.yaml | valid_user  | username              |
      | userData.yaml | valid_user  | profile.email         |
      | userData.yaml | valid_user  | profile.address.city  |
      | userData.yaml | valid_user  | addresses[0].zip      |
      | userData.yaml | valid_user  | roles[1]              |

  Scenario: Read entire node structure from YAML
    Given I read all keys from "valid_user" in file "userData.yaml"
