@tag
Feature: Purchase order from e commerce website

Background:
Given: I landed on E commerce page

@tag2
Scenario Outline: Positive test of submitting the order

Given: User logged in with username <name> and password <password>
When: I add product <productName> to the cart
And: Checkout <productName> and select country <countryName> submit the order
Then: "Thankyou for the order." message is displayed on the confirmation page

Examples:
|name                   |password    |productName    | countryName|
|shrikanttembhe@mail.com|Shrikant@123|ZARA COAT 3    |India   |