#Example cucumber test.
Feature: car module

  #In this scenario a h2 database is used to test database integration
  @DatabaseIntegrationTest
  Scenario: add new car
    When a user enters a car with id 1: a red volkswagen kever, built in 1967

    Then a new car is added to the inventory
      | id | brand      | model | color | year |
      | 1  | volkswagen | kever | red   | 1967 |
