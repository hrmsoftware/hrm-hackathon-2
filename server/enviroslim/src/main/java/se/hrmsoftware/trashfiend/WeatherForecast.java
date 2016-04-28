package se.hrmsoftware.trashfiend;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecast {

	private String lat;
	private String lon;
	@JsonProperty("timeseries")
	private List<TimeSeries> timeSeries;
	
	public WeatherForecast() {
	}
	
	
	public WeatherForecast(String lat, String lon, List<TimeSeries> timeSeries) {
		this.lat = lat;
		this.lon = lon;
		this.timeSeries = timeSeries;
	}
	
	public String getLat() {
		return lat;
	}
	public String getLon() {
		return lon;
	}
	
	public List<TimeSeries> getTimeSeries() {
		return timeSeries;
	}
	
}
