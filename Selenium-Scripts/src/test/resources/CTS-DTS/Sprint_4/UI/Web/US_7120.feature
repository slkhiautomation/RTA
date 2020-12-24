Feature: User Story: 7120 - New Added Countries in Database should be reflect on Configuration Screen
  @Spint4
  Scenario: As a user I want system to get list of countries and store it in DB so that I can group them through
          countries configuration screen
    When Navigate to Application
    And Execute Query for "Insert-Countries"
    And user enter text "drivingInstitute" in "userName" on "LoginPage"
    And user enter text "password" in "password" on "LoginPage"
    And user click on "Signin" button on "LoginPage"
    Then Assert that "Dashboard" appear on "DashboardPage"
    And user scroll to "handBook" on "DashboardPage"
    And user click on "configuration-hub" button on "DashboardPage"
    Then Assert that "masterConfiguration" appear on "DashboardPage"
    And user click on "masterConfiguration" button on "DashboardPage"
    Then Assert that "masterConfiguration" appear on "ConfigurationPage"

  Scenario: In case that we received student details through LAStudentInfoService and we found that country doesn't
            exist in DTS DB then we will Insert in our Enum Table - Service Part

    * def LAStudentInfoService = read('LAStudentServiceInfo.xml')

    * configure ssl = true
    * url 'https://172.27.64.171:11084'
    Given path '/CTSETTrafficLicenseApplicationService'
    Given request LAStudentInfoService
    And header Content-Type = 'application/soap+xml'
    When method post
    Then status 200
    And print response

  Scenario: In case that we received student details through LAStudentInfoService and we found that country doesn't
            exist in DTS DB then we will Insert in our Enum Table - DB Part

    When Verify Country Result inserted in Enum Table
