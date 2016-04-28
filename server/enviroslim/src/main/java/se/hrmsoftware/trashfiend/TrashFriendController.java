package se.hrmsoftware.trashfiend;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/trashme")
public class TrashFriendController {

	private static TrashFriendRepository repo;
	private static RestTemplate smhi = new RestTemplate();
	
	public static final String SMHI_URL =
			"http://opendata-download-metfcst.smhi.se/api/category/pmp1.5g/version/1/geopoint/lat/56.90218/lon/14.837558/data.json";
	
	public TrashFriendController() {
		String fileName =
				TrashFriendRepository.class.getClassLoader().getResource("norremark_2015-08-01-2015-12-03.csv").getFile();
		repo = new TrashFriendRepository(fileName);
	}
	
	
	/**
	 * Returns a <code>TrashFriendReport</code> that contains current information about the
	 * Norremark site. Such as how crowdy the place is and the current weather.
	 * 
	 * @return
	 */
	@RequestMapping("/now")
	TrashFriendReport now() {
		WeatherForecast forecast =
				smhi.getForObject(SMHI_URL, WeatherForecast.class);
		int hours = new Date().getHours();
		TimeSeries ts = forecast.getTimeSeries().stream()
				.filter(timeSerie -> timeSerie.getValidTime().getHours() == hours).findFirst().get();
		return new TrashFriendReport(1,
				ts.getClowdy(), ts.getRainFall());
	}
}
