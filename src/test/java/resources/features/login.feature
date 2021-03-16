Feature: Swag Lab Login Feature

Scenario: Login using locked user

Given user is already on the login page
When user provides locked username and password
And user clicks on login button
Then error message is displayed 

Scenario: Login using standard user

Given user is already on the login page
When user provides correct username and password
And user clicks on login button
Then Product page is displayed 

Scenario: Logout of the site

Given user is already on the Product page
When user clicks on logout button
Then user is loged out 

