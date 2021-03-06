Feature:User Story: 7501 - As a user I want to create default group so that any new countries will be automatically assigned to that group

  Background:
    Given Navigate to Application

    @Group-Popup-Case_1
  Scenario: Save Button is disabled if no value is entered
    When user enter text "drivingInstitute" in "userName" on "LoginPage"
    And user enter text "password" in "password" on "LoginPage"
    And user click on "Signin" button on "LoginPage"
    Then Assert that "Dashboard" appear on "DashboardPage"
    And user scroll to "handBook" on "DashboardPage"
    And user scroll to "permitInvoice" on "DashboardPage"
    And user click on "configuration-hub" button on "DashboardPage"
    Then Assert that "masterConfiguration" appear on "DashboardPage"
    And user click on "masterConfiguration" button on "DashboardPage"
    And user click on "Add-Group" button on "ConfigurationPage"
    And Assert that "Save-Button" appear on "ConfigurationPage"
    And Assert that "Save-Button" is "Disabled" on "ConfigurationPage"
    And user click on "Close-Popup" button on "ConfigurationPage"
    And user click on "user-Name" button on "DashboardPage"
    And user click on "log-Out" button on "DashboardPage"

  @Group-Popup-Case_2
  Scenario: Validate Notification Message Contains the Group Name with Correct Wording
    When user enter text "drivingInstitute" in "userName" on "LoginPage"
    And user enter text "password" in "password" on "LoginPage"
    And user click on "Signin" button on "LoginPage"
    Then Assert that "Dashboard" appear on "DashboardPage"
    And user scroll to "handBook" on "DashboardPage"
    And user scroll to "permitInvoice" on "DashboardPage"
    And user click on "configuration-hub" button on "DashboardPage"
    Then Assert that "masterConfiguration" appear on "DashboardPage"
    And user click on "masterConfiguration" button on "DashboardPage"
    And user click on "Add-Group" button on "ConfigurationPage"
    And user enter text "GroupNametxt-EN" in "GroupNametxt-EN" on "ConfigurationPage"
    And user enter text "GroupNametxt-AR" in "GroupNametxt-AR" on "ConfigurationPage"
    And user enter text "Descriptiontxt" in "Descriptiontxt" on "ConfigurationPage"
    And user click on "DefaultToggle" button on "ConfigurationPage"
    Then Execute Query for "DefaultGroup"
    And user click on "Close-Popup" button on "ConfigurationPage"
    And user click on "user-Name" button on "DashboardPage"
    And user click on "log-Out" button on "DashboardPage"

  @Group-Popup-Case_3
  Scenario: Add New Group without making it's Default Group and Validate it is Reflecting in Group List
    When user enter text "drivingInstitute" in "userName" on "LoginPage"
    And user enter text "password" in "password" on "LoginPage"
    And user click on "Signin" button on "LoginPage"
    Then Assert that "Dashboard" appear on "DashboardPage"
    And user scroll to "handBook" on "DashboardPage"
    And user scroll to "permitInvoice" on "DashboardPage"
    And user click on "configuration-hub" button on "DashboardPage"
    Then Assert that "masterConfiguration" appear on "DashboardPage"
    And user click on "masterConfiguration" button on "DashboardPage"
    And user click on "ModifiedDateDesc" button on "ConfigurationPage"
    And user click on "ModifiedDateDesc" button on "ConfigurationPage"
    And user click on "Add-Group" button on "ConfigurationPage"
    And user enter text "GroupNametxt-EN" in "GroupNametxt-EN" on "ConfigurationPage"
    And user enter text "GroupNametxt-AR" in "GroupNametxt-AR" on "ConfigurationPage"
    And user enter text "Descriptiontxt" in "Descriptiontxt" on "ConfigurationPage"
    And user click on "Save-Button" button on "ConfigurationPage"
#    And user click on "ModifiedDateDesc" button on "ConfigurationPage"
#    Then Assert that Value "GroupNametxt-EN" is appeared at "GroupName-List" on "ConfigurationPage"
    And user click on "user-Name" button on "DashboardPage"
    And user click on "log-Out" button on "DashboardPage"

  @Group-Popup-Case_4
  Scenario: Add New Group with making it's Default Group and Validate it is Reflecting in Group List
    When user enter text "drivingInstitute" in "userName" on "LoginPage"
    And user enter text "password" in "password" on "LoginPage"
    And user click on "Signin" button on "LoginPage"
    Then Assert that "Dashboard" appear on "DashboardPage"
    And user scroll to "handBook" on "DashboardPage"
    And user scroll to "permitInvoice" on "DashboardPage"
    And user click on "configuration-hub" button on "DashboardPage"
    Then Assert that "masterConfiguration" appear on "DashboardPage"
    And user click on "masterConfiguration" button on "DashboardPage"
    And user click on "ModifiedDateDesc" button on "ConfigurationPage"
    And user click on "ModifiedDateDesc" button on "ConfigurationPage"
    And user click on "Add-Group" button on "ConfigurationPage"
    And user enter text "GroupNametxt-EN" in "GroupNametxt-EN" on "ConfigurationPage"
    And user enter text "GroupNametxt-AR" in "GroupNametxt-AR" on "ConfigurationPage"
    And user enter text "Descriptiontxt" in "Descriptiontxt" on "ConfigurationPage"
    And user click on "DefaultToggle" button on "ConfigurationPage"
    And user click on "DefaultNotification" button on "ConfigurationPage"
    And user click on "Save-Button" button on "ConfigurationPage"
#    And user click on "ModifiedDateDesc" button on "ConfigurationPage"
#    Then Assert that Value "GroupNametxt-EN" is appeared at "GroupName-List" on "ConfigurationPage"
    And user click on "user-Name" button on "DashboardPage"
    And user click on "log-Out" button on "DashboardPage"

  @Group-Popup-Case_5
  Scenario: Verify user is not able to add duplicate Group with Group Name English
  Configuration Group already exist error should be appeared
    When user enter text "drivingInstitute" in "userName" on "LoginPage"
    And user enter text "password" in "password" on "LoginPage"
    And user click on "Signin" button on "LoginPage"
    Then Assert that "Dashboard" appear on "DashboardPage"
    And user scroll to "handBook" on "DashboardPage"
    And user scroll to "permitInvoice" on "DashboardPage"
    And user click on "configuration-hub" button on "DashboardPage"
    Then Assert that "masterConfiguration" appear on "DashboardPage"
    And user click on "masterConfiguration" button on "DashboardPage"
    And user click on "Add-Group" button on "ConfigurationPage"
    And user enter text "GroupNametxt-EN" in "GroupNametxt-EN" on "ConfigurationPage"
    And user enter text "GroupNametxt-AR" in "GroupNametxt-AR" on "ConfigurationPage"
    And user enter text "Descriptiontxt" in "Descriptiontxt" on "ConfigurationPage"
    And user click on "Save-Button" button on "ConfigurationPage"
    Then Assert that "ErrorToaster" appear on "ConfigurationPage"

  @Group-Popup-Case_6
  Scenario: Verify user is not able to add duplicate Group with Group Name Arabic
  Configuration Group already exist error should be appeared
    When user enter text "drivingInstitute" in "userName" on "LoginPage"
    And user enter text "password" in "password" on "LoginPage"
    And user click on "Signin" button on "LoginPage"
    Then Assert that "Dashboard" appear on "DashboardPage"
    And user scroll to "handBook" on "DashboardPage"
    And user scroll to "permitInvoice" on "DashboardPage"
    And user click on "configuration-hub" button on "DashboardPage"
    Then Assert that "masterConfiguration" appear on "DashboardPage"
    And user click on "masterConfiguration" button on "DashboardPage"
    And user click on "Add-Group" button on "ConfigurationPage"
    And user enter text "GroupNametxt-EN" in "GroupNametxt-EN" on "ConfigurationPage"
    And user enter text "GroupNametxt-AR" in "GroupNametxt-AR" on "ConfigurationPage"
    And user enter text "Descriptiontxt" in "Descriptiontxt" on "ConfigurationPage"
    And user click on "Save-Button" button on "ConfigurationPage"
    Then Assert that "ErrorToaster" appear on "ConfigurationPage"