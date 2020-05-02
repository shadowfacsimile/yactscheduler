package in.shadowfacsimile.yact.models;

public enum Statistics {

	CASES("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv",
			"/home/facsimile/yactfiles/time_series_covid19_confirmed_global.json"),
	DEATHS("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv",
			"/home/facsimile/yactfiles/time_series_covid19_deaths_global.json"),
	RECOVERIES(
			"https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv",
			"/home/facsimile/yactfiles/time_series_covid19_recovered_global.json"),
	INDIA("https://api.covid19india.org/states_daily.json", "/home/facsimile/yactfiles/states_daily.json");

	private final String url;
	private final String json;

	private Statistics(String url, String json) {
		this.url = url;
		this.json = json;
	}

	public String getUrl() {
		return url;
	}

	public String getJson() {
		return json;
	}

}
