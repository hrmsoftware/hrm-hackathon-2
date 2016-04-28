package se.hrmsoftware.trashfiend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrashFriendReport {

	private final int busyStatus;
	
	public TrashFriendReport(int busyStatus) {
		this.busyStatus = busyStatus;
	}
	
	public int getBusyStatus() {
		return busyStatus;
	}
}
