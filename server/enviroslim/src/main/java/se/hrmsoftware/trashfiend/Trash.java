package se.hrmsoftware.trashfiend;

import com.opencsv.bean.CsvBind;

import java.math.BigDecimal;

public class Trash {
	@CsvBind
	private String date;
	@CsvBind
	private String time;
	@CsvBind
	private int velocity;
	@CsvBind
	private int length;
	@CsvBind
	private BigDecimal latitude;
	@CsvBind
	private BigDecimal longitude;

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public int getVelocity() {
		return velocity;
	}

	public int getLength() {
		return length;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}
}
