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

	/**
	 * Indicates wether there are a lot of people at the Norremark site or not.
	 * <ul>
	 *   <li>1 = Not a lot of people, you should go!
	 *   <li>2 = Memium amount of people
	 *   <li>3 = A LOT of pepole, only there if you must!
	 * </ul>
	 *
	 * @return
	 */
	public int getBusyStatus() {
		return busyStatus;
	}

	/**
	 * Amount of clouds. 0 is sunny, 8 is totally cloudy, the numbers in between
	 * 0 and 8 means partial cloudy.
	 *
	 * @return
	 */
	public int getClowdy() {
		return clowdy;
	}

	/**
	 * The kind of rain fall.
	 * <ul>
	 *   <li>0 = no downfall
	 *   <li>1 = snow
	 *   <li>2 = snow and rain
	 *   <li>3 = rain
	 *   <li>4 = drizzle
	 *   <li>5 = freezing rain
	 *   <li>6 = freezing dizzle
	 * </ul>
	 *
	 * @return
	 */
	public int getRainFall() {
		return rainFall;
	}
	
}
