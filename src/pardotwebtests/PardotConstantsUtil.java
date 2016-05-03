package pardotwebtests;

public final class PardotConstantsUtil {

	public enum PardotPageUrls
	{
		LIST("https://pi.pardot.com/list"),
		PROSPECT("https://pi.pardot.com/prospect"),
		NEWLISTEMAIL("https://pi.pardot.com/email/draft/edit");
		
		private final  String pageUrlString;
		PardotPageUrls(String pageUrlString)
		{
			this.pageUrlString = pageUrlString;
		}
		
		public String getPageUrlString()
		{
			return pageUrlString;
		}
		
		
	}
	
	public enum ListPageElements
	{
		ADDBUTTONID("listxistx_link_create");
		
		private final String elementRef;
		ListPageElements(String elementRef)
		{
			this.elementRef = elementRef;
		}
		
		public String getElementRef()
		{
			return elementRef;
		}
		
		
	}
	
	public enum NewListEmailPageElements
	{
		NAME_ID("name"),
		FOLDER_ICON_CLASS("icon-folder-open-alt"),
		FOLDER_SEARCH_FILTER_ID("ember1156"),
		CAMPAIGN_SEARCH_FILTER_CLASS1("ember-view"),
		CAMPAIGN_SEARCH_FILTER_CLASS2("ember-text-field"),
		CAMPAIGN_SEARCH_FILTER_CLASS3("filter-by"),
		FILTER_SEARCH_RESULTS_CLASS("ember-list-container"),
		CAMPAIGN_ICON_CLASS("icon-bullhorn"),
		CHOOSE_SELECTED_BUTTON_ID("select-asset"),
		SAVE_BUTTON_ID("save_information"),
		RADIO_EMAIL_TYPE_TEXT_ONLY_ID("email_type_text_only"),
		FROM_TEMPLATE_ID("from_template"),
		TEXT_MESSAGE_ID("text_message"),
		SAVE_INFORMATION_BUTTON_ID("save_footer"),
		SENDING_EMAIL_MENUBAR_DISPLAYTEXT("Sending");
		
		private final String elementRef;
		NewListEmailPageElements(String elementRef)
		{
			this.elementRef = elementRef;
		}
		
		public String getElementRef()
		{
			return elementRef;
		}
		
		
	}
	
	public enum ProspectPageElements
	{
		ADDBUTTON_ID("pr_link_create"),
		EMAILTEXTBOX_ID("email"),
		CAMPAIGN_ID("campaign_id"),
		PROFILE_ID("profile_id"),
		
		//optional fields
		FNAME_ID("default_field_3361"),
		LNAME_ID("default_field_3371"),
		
		//add list icon for click
		ADDLIST_ID("toggle-inputs-lists-"),
		//add list dropdown multi-select consisting of options
		ADDLISTDROPDOWN_CLASS(".chzn-results"),
		//ADDLISTDROPDOWN_CLASS(".select-list"),
		ADDLISTDEFAULTDROPDOWN_CLASS(".chzn-single.chzn-default"),
		CREATEPROSPECTBUTTON_CLASS(".btn.btn-primary");
		
		private final String elementRef;
		ProspectPageElements(String elementRef)
		{
			this.elementRef = elementRef;
		}
		
		public String getElementRef()
		{
			return elementRef;
		}
		
		
	}
	
	
	public enum PostCreateProspectPageElements
	{
		PROSPECTPAGE_NAVBAR_CLASS(".navbar-inner"),
		LISTMENU_CLASS(".nav"),
		LIST_DISPLAYTEXT("Lists");
		
		private final String elementRef;
		PostCreateProspectPageElements(String elementRef)
		{
			this.elementRef = elementRef;
		}
		
		public String getElementRef()
		{
			return elementRef;
		}
		
		
	}
}
