Feature: A description

  Background:
    Given Navigate to Application
    
    @PLP
      Scenario: Validate user able to Search the product
      When user enter text "Searchproduct" in "searchBar" on "SearchPage"
      And user click on "search" button on "SearchPage"
      Then Assert that "searchResult" appear on "SearchPage"

  @PDP
  Scenario: Validate user able to Verify the Product Details
    When user enter text "Searchproduct" in "searchBar" on "SearchPage"
    And user click on "search" button on "SearchPage"
    Then Assert that "searchResult" appear on "SearchPage"
    Then Assert that "productName" appear on "PDP"
    Then Assert that "productPrice" appear on "PDP"

    @Add2Cart
    Scenario: Validate user able to Add the Product in Cart
      When user enter text "Searchproduct" in "searchBar" on "SearchPage"
      And user click on "search" button on "SearchPage"
      Then Assert that "searchResult" appear on "SearchPage"
      And user click on "searchedProduct" button on "SearchPage"
      Then Assert that "productName" appear on "PDP"
      Then Assert that "productPrice" appear on "PDP"
      And user click on "AddtoCart" button on "PDP"
      Then Assert that "productCount" appear on "PDP"

      @GuestCheckOut
        Scenario: Validate user able to CheckOut as Guest
        When user enter text "Searchproduct" in "searchBar" on "SearchPage"
        And user click on "search" button on "SearchPage"
        Then Assert that "searchResult" appear on "SearchPage"
        And user click on "searchedProduct" button on "SearchPage"
        Then Assert that "productName" appear on "PDP"
        Then Assert that "productPrice" appear on "PDP"
        And user click on "AddtoCart" button on "PDP"
        Then Assert that "productCount" appear on "PDP"
        And user click on "check-Out" button on "PDP"
        Then Assert that "guestCheckOutHeading" appear on "Checkout"
        And user enter text "ZipCode" in "ZipCode" on "Checkout"
        And user click on "guestCheckOut" button on "Checkout"
        And Assert that "checkOutbtn" appear on "Checkout"

    @E2E
 Scenario Outline: Validate user is able to placed the Order as Guest
    Given I should see response for the requested API "<Endpoint>" using Request Body "<RequestBody>"
    When user enter text "Searchproduct" in "searchBar" on "SearchPage"
    And user click on "search" button on "SearchPage"
    Then Assert that "searchResult" appear on "SearchPage"
    And user click on "searchedProduct" button on "SearchPage"
    Then Assert that "productName" appear on "PDP"
    Then Assert that "productPrice" appear on "PDP"
    And user click on "AddtoCart" button on "PDP"
    Then Assert that "productCount" appear on "PDP"
    And user click on "check-Out" button on "PDP"
    Then Assert that "guestCheckOutHeading" appear on "Checkout"
    And user enter text "ZipCode" in "ZipCode" on "Checkout"
    And user click on "guestCheckOut" button on "Checkout"
    And Assert that "checkOutbtn" appear on "Checkout"
    And user click on "check-Out" button on "Checkout"
    And user enter text "firstName" in "firstName" on "ShipperForm"
    And user enter text "lastName" in "lastName" on "ShipperForm"
    And user enter text "address" in "address" on "ShipperForm"
    And user enter text "UnitNumber" in "UnitNumber" on "ShipperForm"
    And user enter text "City" in "City" on "ShipperForm"
    And user enter text "State" in "State" on "ShipperForm"
    And user enter text "ZipCode" in "ZipCode" on "ShipperForm"
    And user enter text "email" in "email" on "ShipperForm"
    And user enter text "phone" in "phone" on "ShipperForm"

      Examples:
        | Endpoint      | RequestBody |
        | GetSpecialURL |   sample 	  |