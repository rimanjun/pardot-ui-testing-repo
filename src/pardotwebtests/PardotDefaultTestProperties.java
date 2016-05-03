package pardotwebtests;

public final class PardotDefaultTestProperties {
	
	public enum ListDefaultInputs
	{
		FOLDERNAME("EMAIL INTERVIEW TEST");
		
		private final String dataValue;
		ListDefaultInputs(String dataValue)
		{
			this.dataValue = dataValue;
		}
		
		public String getDataValue()
		{
			return dataValue;
		}
	}
	
	public enum EmailDefaultInputs
	{
		FOLDERNAME("EMAIL INTERVIEW TEST"),
		CAMPAIGNNAME("Allison Tigers");
		
		private final String dataValue;
		EmailDefaultInputs(String dataValue)
		{
			this.dataValue = dataValue;
		}
		
		public String getDataValue()
		{
			return dataValue;
		}
	}
	
	public enum ProspectDefaultInputs
	{
		EMAILID("sample_email@yahoo.com"),
		CAMPAIGN("Allison Tigers"),
		PROFILE("Allison Tigers 1"),
		LIST("adm");
		//LIST("PSSampleListTest1");
		
		private final String dataValue;
		ProspectDefaultInputs(String dataValue)
		{
			this.dataValue = dataValue;
		}
		
		public String getDataValue()
		{
			return dataValue;
		}
	}

}
