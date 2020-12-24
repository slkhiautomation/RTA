Feature:User Story: 4359 - As a user I want to have a new screen that allows me to define a group that contains certain countries to be used for configuring the training and testing customer preferences so that I can configure it based on DLD policies

  Background:
    Given Navigate to Application

  @ABC
  Scenario: Validate user can see the Countries, Sponsor, Occupation tabs on Master Configuration Screen

    When user enter text "drivingInstitute" in "userName" on "LoginPage"
    And user enter text "password" in "password" on "LoginPage"
    And user click on "Signin" button on "LoginPage"
    Then Assert that "Dashboard" appear on "DashboardPage"
#    user enter text "drivingInstitute" in "userName" on "LoginPage"