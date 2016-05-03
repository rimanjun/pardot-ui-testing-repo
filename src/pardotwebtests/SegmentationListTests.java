package pardotwebtests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class SegmentationListTests extends AbstractWebTests {

	@Test
	public void logIn() {
		assertEquals("Dashboard - Pardot", webDriver.getTitle());
	}

	@Test
	public void verifyDuplicateList() {
		final String folderName = "EMAIL INTERVIEW TEST";
		final String listPrefix = "ritu_list";
		final String filterTextBoxId = "listx_table_filter";
		String randomListName = randomStringGenerator(listPrefix);

		createList(webDriver, randomListName, folderName, null);
		waitForClick();
		assertEquals(randomListName + " - Pardot", webDriver.getTitle());

		// create list with same name
		createList(webDriver, randomListName, folderName, null);

		// verify for error message at the top of the page
		WebElement we = webDriver
				.findElement(By.cssSelector(".alert.alert-error"));
		String expectedErrorString = "Please correct the errors below and re-submit";
		Assert.assertTrue(we.getText().equals(expectedErrorString),
				"Expected error not found");

		// cancel and return to list page
		webDriver.findElement(By.cssSelector(".btn.btn-default")).click();

		// rename original list

		// narrow result by selecting it through filter
		webDriver.findElement(By.id(filterTextBoxId)).sendKeys(randomListName);

		// get first row, 2nd column - name of list to click
		WebElement table = webDriver.findElement(By.id("listx_table"));

		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		// verify the filter yielded atleast 1 result
		Assert.assertTrue(allRows.size() > 1);

		// pick the 1st result, and it's list name to click
		List<WebElement> firstColumnCells = allRows.get(1)
				.findElements(By.tagName("td"));
		WebElement filteredList = firstColumnCells.get(1)
				.findElement(By.tagName("a"));
		System.out.println("link info:" + filteredList.toString());
		filteredList.click();

		// go from read to edit mode which navigates to Edit Page
		String currentUrl = webDriver.getCurrentUrl();
		currentUrl = currentUrl.replaceFirst("read", "edit");
		webDriver.navigate().to(currentUrl);
		waitForClick();

		// edit name by appending suffix
		webDriver.findElement(By.id("name")).sendKeys("_renamed");
		waitForClick();
		webDriver.findElement(By.id("save_information")).click();

		assertEquals(randomListName + "_renamed - Pardot",
				webDriver.getTitle());

		waitForClick();
		// create list with same name
		createList(webDriver, randomListName, folderName, null);
		assertEquals(randomListName + " - Pardot", webDriver.getTitle());

	}

	@Test
	public void createProspectTest() {
		String randomFirstName = randomStringGenerator("firstName_");
		String randomlastName = randomStringGenerator("lastName_");
		String uniqueEmailId = randomStringGenerator("email_");
		uniqueEmailId = uniqueEmailId + "@yahoo.com";
		Prospect prospect = new Prospect(uniqueEmailId,
				PardotDefaultTestProperties.ProspectDefaultInputs.CAMPAIGN
						.getDataValue(),
				PardotDefaultTestProperties.ProspectDefaultInputs.PROFILE
						.getDataValue());

		prospect.setFirstName(randomFirstName);
		prospect.setLastName(randomlastName);
		List<String> inputList = new ArrayList<String>();
		inputList.add(PardotDefaultTestProperties.ProspectDefaultInputs.LIST
				.getDataValue());
		prospect.setSegmentationLists(inputList);
		createProspect(webDriver, prospect);
		verifyListInProspect(webDriver, prospect);
	}

	@Test
	public void sendNewEmailTest() {
		String randomEmailName = randomStringGenerator("email_");

		createNewEmail(webDriver, randomEmailName,
				PardotDefaultTestProperties.EmailDefaultInputs.FOLDERNAME
						.getDataValue(),
				PardotDefaultTestProperties.EmailDefaultInputs.CAMPAIGNNAME
						.getDataValue(),
				null, PardotDefaultTestProperties.EmailDefaultInputs.EMAIL_TEXT.getDataValue());

	}
	void createList(WebDriver webDriver, String listName, String folderName,
			String tags) {
		final String addListButtonId = "listxistx_link_create";
		// navigate to marketing,segmentation,list page
		webDriver.navigate()
				.to(PardotConstantsUtil.PardotPageUrls.LIST.getPageUrlString());
		// click on create list button
		webDriver.findElement(By.id(addListButtonId)).click();
		waitForClick();

		// input data for list name and folder
		webDriver.findElement(By.id("name")).sendKeys(listName);
		// click on choose button adjacent to folder textbox
		webDriver.findElement(By.className("icon-folder-open-alt")).click();
		waitForClick();

		// search filter
		webDriver.findElement(By.id("ember1156")).sendKeys(folderName);

		List<WebElement> elementsList = webDriver
				.findElements(By.cssSelector(".ember-list-container"));
		elementsList.get(0).click();

		waitForClick();

		// submit
		webDriver.findElement(By.id("select-asset")).click();
		webDriver.findElement(By.id("save_information")).click();
	}

	void createProspect(WebDriver webDriver, Prospect prospectObj) {
		webDriver.navigate().to(
				PardotConstantsUtil.PardotPageUrls.PROSPECT.getPageUrlString());
		// click on add prospect button
		webDriver.findElement(
				By.id(PardotConstantsUtil.ProspectPageElements.ADDBUTTON_ID
						.getElementRef()))
				.click();
		waitForClick();
		waitForClick();

		// input data. Mandatory fields first

		// email id
		webDriver
				.findElement(
						By.id(PardotConstantsUtil.ProspectPageElements.EMAILTEXTBOX_ID
								.getElementRef()))
				.sendKeys(prospectObj.getEmailId());
		waitForClick();
		// campaign
		Select campaignDropdown = new Select(webDriver.findElement(
				By.id(PardotConstantsUtil.ProspectPageElements.CAMPAIGN_ID
						.getElementRef())));
		campaignDropdown.selectByVisibleText(
				PardotDefaultTestProperties.ProspectDefaultInputs.CAMPAIGN
						.getDataValue());
		waitForClick();

		// profile
		Select profileDropdown = new Select(webDriver.findElement(
				By.id(PardotConstantsUtil.ProspectPageElements.PROFILE_ID
						.getElementRef())));
		profileDropdown.selectByVisibleText(
				PardotDefaultTestProperties.ProspectDefaultInputs.PROFILE
						.getDataValue());
		waitForClick();

		// input data for firstname,lastname
		if (prospectObj.getFirstName() != null) {
			webDriver
					.findElement(
							By.id(PardotConstantsUtil.ProspectPageElements.FNAME_ID
									.getElementRef()))
					.sendKeys(prospectObj.getFirstName());
			waitForClick();
		}

		if (prospectObj.getLastName() != null) {
			webDriver
					.findElement(
							By.id(PardotConstantsUtil.ProspectPageElements.LNAME_ID
									.getElementRef()))
					.sendKeys(prospectObj.getLastName());
			waitForClick();
		}

		// list assignment to prospect begins

		if (prospectObj.getSegmentationLists() != null) {
			// click on lists collapsible icon
			webDriver.findElement(
					By.id(PardotConstantsUtil.ProspectPageElements.ADDLIST_ID
							.getElementRef()))
					.click();

			// assign every segmentation list from the array list to dropdown

			for (String curList : prospectObj.getSegmentationLists()) {
				System.out.println("**current seg list**:" + curList);
				// click on default value to expand the drop down
				WebElement webElement = webDriver.findElement(By.cssSelector(
						PardotConstantsUtil.ProspectPageElements.ADDLISTDEFAULTDROPDOWN_CLASS
								.getElementRef()));
				webElement.click();
				webElement = null;
				webElement = webDriver.findElement(By.cssSelector(
						PardotConstantsUtil.ProspectPageElements.ADDLISTDROPDOWN_CLASS
								.getElementRef()));
				System.out.println("verifying current list" + curList);
				WebElement selectedListItem = webElement.findElement(
						By.xpath(".//li[contains(text(),'" + curList + "')]"));
				selectedListItem.click();

			}

		}

		// list assignment ends

		// submit

		webDriver.findElement(By.cssSelector(
				PardotConstantsUtil.ProspectPageElements.CREATEPROSPECTBUTTON_CLASS
						.getElementRef()))
				.click();

	}
	
	/**
	 * Create a new List Email without a template
	 * @param webDriver
	 * @param name
	 * 		mandatory name for the email
	 * @param folderName
	 * 		name of an existing directory
	 * @param campaignName
	 * 		name of an existing campaign
	 * @param tags
	 * 		optional tag
	 */
	void createNewEmail(WebDriver webDriver, String name, String folderName,
			String campaignName, String tags, String message) {
		// navigate to marketing,emails,new list email
		webDriver.navigate().to(PardotConstantsUtil.PardotPageUrls.NEWLISTEMAIL
				.getPageUrlString());

		//1 input data for email name which is mandatory
		webDriver.findElement(
				By.id(PardotConstantsUtil.NewListEmailPageElements.NAME_ID
						.getElementRef()))
				.sendKeys(name);
		waitForClick();

		// 2input data for folder
		// click on choose button adjacent to folder textbox
		webDriver.findElement(By.className(
				PardotConstantsUtil.NewListEmailPageElements.FOLDER_ICON_CLASS
						.getElementRef()))
				.click();
		waitForClick();
		// Go to select folder page. search filter
		webDriver.findElement(
				By.id(PardotConstantsUtil.NewListEmailPageElements.FOLDER_SEARCH_FILTER_ID
						.getElementRef()))
				.sendKeys(folderName);

		List<WebElement> elementsList = webDriver.findElements(By.cssSelector("."+
				PardotConstantsUtil.NewListEmailPageElements.FILTER_SEARCH_RESULTS_CLASS
						.getElementRef()));
		elementsList.get(0).click();
		waitForClick();
		// finish choosing the folder
		webDriver.findElement(
				By.id(PardotConstantsUtil.NewListEmailPageElements.CHOOSE_SELECTED_BUTTON_ID
						.getElementRef()))
				.click();

		//3 input data for campaign
		// click on choose button adjacent to campaign textbox
		webDriver.findElement(By.className(
				PardotConstantsUtil.NewListEmailPageElements.CAMPAIGN_ICON_CLASS
						.getElementRef()))
				.click();
		waitForClick();
		// Go to select campaign page. search filter
		String filterCssString="."+PardotConstantsUtil.NewListEmailPageElements.CAMPAIGN_SEARCH_FILTER_CLASS1.getElementRef()
		+"."+PardotConstantsUtil.NewListEmailPageElements.CAMPAIGN_SEARCH_FILTER_CLASS2.getElementRef()
		+"."+PardotConstantsUtil.NewListEmailPageElements.CAMPAIGN_SEARCH_FILTER_CLASS3
		.getElementRef();
		//input text in search bar
		WebElement searchBoxElement = webDriver.findElement(
				By.cssSelector(filterCssString));
		searchBoxElement.sendKeys(campaignName);
		//select the first item in the filtered list
		waitForClick();
		List<WebElement> campaignElementsList = webDriver
				.findElements(By.cssSelector(".ember-list-container"));			
		try {
			
			WebElement childElement = campaignElementsList.get(0)
					.findElement(By
							.xpath("//div/h4/i[@class='icon-bullhorn']"));
			childElement.click();

		} catch (WebDriverException e) {
			System.out.println("not clickable. trying enter key");
			searchBoxElement.sendKeys(Keys.RETURN);
		}	
		// finish choosing the campaign	
		webDriver.findElement(
				By.id(PardotConstantsUtil.NewListEmailPageElements.CHOOSE_SELECTED_BUTTON_ID
						.getElementRef()))
				.click();
		
		//4 choose format as html only
		webDriver.findElement(
				By.id(PardotConstantsUtil.NewListEmailPageElements.RADIO_EMAIL_TYPE_TEXT_ONLY_ID
						.getElementRef()))
				.click();
		
		//5 uncheck template checkbox
		webDriver.findElement(
				By.id(PardotConstantsUtil.NewListEmailPageElements.FROM_TEMPLATE_ID
						.getElementRef()))
				.click();
		
		//TODO add code to input other non-mandatory field data
			
		// finish saving info on page1
		webDriver.findElement(
				By.id(PardotConstantsUtil.NewListEmailPageElements.SAVE_BUTTON_ID
						.getElementRef()))
				.click();
		
		//expected to reach the next page to compose email
		String existingText = webDriver.findElement(
				By.id(PardotConstantsUtil.NewListEmailPageElements.TEXT_MESSAGE_ID
						.getElementRef())).getText();
		webDriver.findElement(
				By.id(PardotConstantsUtil.NewListEmailPageElements.TEXT_MESSAGE_ID
						.getElementRef())).clear();
		String composingText = PardotDefaultTestProperties.EmailDefaultInputs.EMAIL_TEXT
				.getDataValue() + existingText;
		webDriver.findElement(
				By.id(PardotConstantsUtil.NewListEmailPageElements.TEXT_MESSAGE_ID
						.getElementRef())).sendKeys(
						composingText);
		webDriver.findElement(
				By.id(PardotConstantsUtil.NewListEmailPageElements.SAVE_INFORMATION_BUTTON_ID
						.getElementRef()))
				.click();	
		
		// click on Sending menu item to navigate to sending page	
		webDriver.findElement(By.id("flow_sending")).click();
		
		waitForClick();
		webDriver.findElement(By.id("email-wizard-list-select")).click();
		
		/*
		Select campaignDropdown = new Select(webDriver.findElement(
				By.id(PardotConstantsUtil.ProspectPageElements.CAMPAIGN_ID
						.getElementRef())));
		campaignDropdown.selectByVisibleText(
				PardotDefaultTestProperties.ProspectDefaultInputs.CAMPAIGN
						.getDataValue());
		*/
		waitForClick();
		
	}
	

	void verifyListInProspect(WebDriver webDriver, Prospect prospectObj) {
		// assuming webDriver is already in the just created Prospect page
		waitForClick();
		String navBarDisplayText = PardotConstantsUtil.PostCreateProspectPageElements.LIST_DISPLAYTEXT
				.getElementRef();
		//navigate to Lists page related to Prospects
		webDriver.findElement(By.partialLinkText(navBarDisplayText)).click();

		//Verify your added list appears as a selected line item in the drop down
		if (prospectObj.segmentationLists != null) {
			for (String curList : prospectObj.segmentationLists) {
				List<WebElement> listItems = webDriver.findElements(
						By.cssSelector("li[data-name='" + curList + "']"));
				if (listItems.size() == 0) {
					Assert.assertTrue(false);
				}
			}

		}

	}

}
