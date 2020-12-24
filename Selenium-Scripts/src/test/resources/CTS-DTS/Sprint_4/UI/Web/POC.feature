Feature: UI with SOAP

  @Service
  Scenario: Send SOAP Request and Pass Result Value to UI
    Given Create Ticet with Order
    And Navigate to Google
    And Pass the Value to Google