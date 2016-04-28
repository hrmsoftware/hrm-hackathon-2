package se.hrmsoftware.trashfiend;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TimeSeries {

	@JsonDeserialize(using=DateDeserializer.class)
	@JsonProperty("validTime")
	private Date validTime;
	@JsonProperty("tcc")
	private int clowdy;
	@JsonProperty("pcat")
	private int rainFall;
	
	public TimeSeries() {}
	
	public TimeSeries(Date validTime, int clowdy, int rainFall) {
		this.validTime = validTime;
		this.clowdy = clowdy;
		this.rainFall = rainFall;
	}
	
	public Date getValidTime() {
		return validTime;
	}

	public int getClowdy() {
		return clowdy;
	}

	public int getRainFall() {
		return rainFall;
	}
}
