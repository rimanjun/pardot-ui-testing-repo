package pardotwebtests;

import java.util.ArrayList;
import java.util.List;

public class Prospect {
	private String firstName;
	private String lastName;
	private String emailId;
	private String company;
	private String account;
	private String campaign;
	private String profile;
	private String assignTo;
	private String notes;
	private String tag;
	List<String> segmentationLists;
	
	Prospect(String emailId,String campaign,String profile)
	{
		this.emailId = emailId;
		this.campaign = campaign;
		this.profile = profile;
		segmentationLists = new ArrayList<String>();
	}
	
	Prospect(String emailId,String campaign,String profile,String firstName,String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.campaign = campaign;
		this.profile = profile;
		segmentationLists = new ArrayList<String>();
	}
	
	void setEmailId(String emailId)
	{
		this.emailId = emailId;
	}
	
	String getEmailId()
	{
		return this.emailId;
	}
	
	void setCampaign(String campaign)
	{
		this.campaign = campaign;
	}
	
	String getCampaign()
	{
		return this.campaign;
	}
	
	void setProfile(String profile)
	{
		this.profile = profile;
	}
	
	String getProfile()
	{
		return this.profile;
	}
	
	void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	String getFirstName()
	{
		return this.firstName;
	}
	
	void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	String getLastName()
	{
		return this.lastName;
	}
	
	void setSegmentationLists(List<String> lists)
	{
		for(String curList: lists)
		{
			segmentationLists.add(curList);
		}
	}
	
	List<String> getSegmentationLists()
	{
		return segmentationLists;
	}
	
	/* TODO
	 * more getter setter methods required
	 * for remaining private data fields
	 */
}
