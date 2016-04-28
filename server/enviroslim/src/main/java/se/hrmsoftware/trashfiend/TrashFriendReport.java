package se.hrmsoftware.trashfiend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrashFriendReport {

	private final int busyStatus;
	private final int clowdy;
	private final int rainFall;
	
	public TrashFriendReport(int busyStatus, int clowdy, int rainFall) {
		this.busyStatus = busyStatus;
		this.clowdy = clowdy;
		this.rainFall = rainFall;
	}
	
	public int getBusyStatus() {
		return busyStatus;
	}

	public int getClowdy() {
		return clowdy;
	}

	public int getRainFall() {
		return rainFall;
	}
	
}
