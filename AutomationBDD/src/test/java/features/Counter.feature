
Feature: Countdown Application

	@CounterTest
  Scenario Outline: Verify that the countdown starts and the remaining time decreases in one-second.
    Given User is on Homepage
    When User enters <time> in seconds
    And Click on Go button to start the counter
    Then Wait for the counter to start the counting down <time>
    And Verify that the countdown is happening every sec & the remaining <time> decreases in one-second
    And Verify that Time Expired alert is displayed
    
    Examples:
    |time|
    |	25 |