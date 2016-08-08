Feature: Pay for a flight

Scenario: Booking up a flight to a declined card payment 
	Given I make a booking from "Dublin" to "Glasgow" on 27/08/2016 for 2 adults and 1 child
	When I pay for booking with card details "5555 5555 5555 5557", "10/18" and "256"
	Then I should get payment declined message