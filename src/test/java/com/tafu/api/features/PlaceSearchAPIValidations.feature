Feature: Validating Place API's
	@AddPlace @Regression
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given user has Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI"  with "POST" http request
    Then the API call is success with response code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name | language | address |
      | Rama | Hindi    | Ayodhya |

  #	| Ayansh | English  | Bangelore |
  
  @DeletePlace @Regression
  Scenario: Verify if the deletePlace functionality is working
    Given DeletePlace Payload
    When user calls "deletePlaceAPI"  with "POST" http request
    Then the API call is success with response code 200
    And "status" in response body is "OK"
