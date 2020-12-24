Feature: API

  @API
  Scenario Outline: Validate the response code for various scenarios like checking "<http_code>" status code
    Given I should see response code "<http_code>" for the requested API "<Endpoint>"
    Examples:
      | Endpoint      | http_code |
      | GetSpecialURL |   200 	  |

  @POST
  Scenario Outline: Validate user is able to placed the Order as Guest
    Given I should see response for the requested API "<Endpoint>" using Request Body "<RequestBody>"
    Examples:
      | Endpoint      | RequestBody |
      | GetSpecialURL |   sample 	|