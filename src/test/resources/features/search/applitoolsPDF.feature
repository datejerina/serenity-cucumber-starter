@applitools
Feature: Running applitools framework

  @pdf
  Scenario: Purchasing item and comparing the receipt with Applitools
    Given Nacho navigates to Amazon Website
    And Nacho adds items to the cart
    When Nacho buys the items of the cart
    Then Nacho downloads the receipt
    And Nacho compares the receipt on folder "amazon_receipt_with_displacement" with Applitools baseline


  @differences
  Scenario: Find the amount of differences
    Given Compare the differences of the following pdf file "baseline-modified-01" with Applitools baseline

