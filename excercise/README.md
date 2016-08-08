In order:

To run test you should have installed:
- Java
- Maven
- Possibly eclipse

To overcome "cannont find symbol" errors:
- Update and refresh project and check again.
- Check if junit4 libarty is added, if not build path > add libaries > junit4 (in eclipse) and rebuild again.

To run tests:
- Open a terminal window/command prompt
- Open project directory in terminal/prompt
- `mvn clean verify`

To run tests from eclipse:
- as maven - right click on project run as > maven test

- as junit - right click on CardPaymentTest run as > junit test

To generate reports:
- Cucumber report is generated after every run automatically and it is located in \target\Cucumber-report

- To run tests and generate surefire report without css which is located in \target\site
   `mvn surefire-report:report`

- To run tests and generate surefire report with css which is located in \target\site
   `mvn test site`
	
Log level can be changed in log4j.xml, and log files are located in \target

==========================================================================================================================================

About the tests:

Main purpose of this task was checking if payment is declined for given data. That's why at HomePage test selects from the list departure and destination place (to imitate user behaviour and show that's something doable). Fly out date is just send to the field (because picking from calendar, list etc. is something for smaller test concerning only this form).
Also that's why test is only taking adult and child type of passenger. Because they were not specified as parameters in cucumber scenario, and testing different kind of ticketcs is not the point of this test. However written methods are able to add more types of passengers.

One Action (like filling flight form) usually is consisting of few smaller methods, making them reusable for future tests.

I added logger to make debug of tests easier. I know there is log4j2 but i'm not familiar with it yet.

Maven surefire is added only in purpose of generating raports.

At the BookingHome page, i had to add thread.sleep (through i know it is bad practice) because i was getting timeout transaction messages from the site.

Rest of the information is in comments.