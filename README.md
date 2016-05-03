
README file for Automated Tests for https://pi.pardot.com

-author: Ritu Manjunathan

Setup Requirements:
-Java
-Selenium WebDriver (using Firefox driver)
-JUnit


3 Test cases covered

1. verifyDuplicateList()
Creation of duplicate Segmentation Lists. 
Create a List. Attempt to create a duplicate one.
Verify for expected UI error. Rename the list.
Create a new list with the original name.
Verify this is successful.

2. createProspectTest()
Create a prospect and assign it to a list. 


3. sendNewEmailTest()
Create a new email and send.
(Send now button did not have an id, and was disabled, hence
the code to click the button has been commented out).
Known Issue: Was unable to assign a list to the email as part
of this testcase.

To run:
-SegmentationListTests.java has all the test methods that are to be run.
-Default input data for the tests will be read from PardotDefaultTestProperties.java. The values can be changed to test with different inputs.
-Some inputs for certain tests are randomly generated and are not read through the above file.


