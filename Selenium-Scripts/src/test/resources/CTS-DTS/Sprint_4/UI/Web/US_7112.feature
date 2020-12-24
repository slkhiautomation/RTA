Feature: User Story 7112 - User should not be able to login if he did not use RTA Application more than "Configured" Days

  @DeActiveUser
  Scenario: As Admin I want the system to automatically deactivate any user who didn't login since more than 90 days
            so that he couldn't access application according to RTA Policy
    When Execute Query for "De-Activation_Days" and Get Column "PARAMETER_VALUE"
    And Execute Query for "De-Activated_Users" and Get Column "USER_LOGIN_ID,ACTIVE_IND"