package se.hrmsoftware.trashfiend;

import java.time.DayOfWeek;
import java.util.Objects;

public class DayTime {
	private final DayOfWeek day;
	private final String time;

	public DayTime(DayOfWeek day, String time) {
		this.day = day;
		this.time = time;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public String getTime() {
		return time;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		DayTime dayTime = (DayTime) o;
		return day == dayTime.day && Objects.equals(time, dayTime.time);
	}

	@Override public int hashCode() {
		return Objects.hash(day, time);
	}

	@Override public String toString() {
		return "DayTime{" +
				"day=" + day +
				", time='" + time + '\'' +
				'}';
	}
}
