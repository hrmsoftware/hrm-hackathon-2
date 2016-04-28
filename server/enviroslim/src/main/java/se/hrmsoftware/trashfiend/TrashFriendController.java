package se.hrmsoftware.trashfiend;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;

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
		int[] currentStatus = getCurrentStatus();
		return new TrashFriendReport(currentStatus[0],
				ts.getClowdy(), ts.getRainFall(), currentStatus[1]);
	}

	protected int[] getCurrentStatus() {
		LocalDate localDate = LocalDate.now().minusMonths(6);
		int hour = LocalTime.now().getHour();
		String dateString = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
		int size = repo.getTrashList(
				t -> t.getDate().equals(dateString) && Integer.parseInt(t.getTime().substring(0, 2)) == hour).size();
		int max = repo.getCollect().values().stream().mapToInt(v -> v.size()).max().getAsInt();
		return new int[] {(int) (((float)size / (float) max) * 3), size};
	}

	@CrossOrigin("*")
	@RequestMapping("/future")
	TrashEntry future(@RequestParam(name = "type", required = false, defaultValue = "week") String type) {
		if (type.equals("day")) {
			int value = LocalDate.now().getDayOfWeek().getValue()-1;
			List<String> labels = Arrays.asList("M�ndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "L�rdag", "S�ndag");
			return new TrashEntry(Collections.singletonList(labels.get(value)),
					Arrays.asList("8-10", "10-12", "12-14", "14-16", "16-18", "18-20"),
					repo.getPeriodData(Arrays.asList(
							new String[] { "08", "10" },
							new String[] { "10", "12" },
							new String[] { "12", "14" },
							new String[] { "14", "16" },
							new String[] { "16", "18" },
							new String[] { "18", "20" })).stream().map(l -> Collections.singletonList(l.get(value))).collect(
							Collectors.toList()));
		}
		else {
			return new TrashEntry(Arrays.asList("M�ndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "L�rdag", "S�ndag"),
					Arrays.asList("8-12", "12-16", "16-20"),
					repo.getPeriodData(Arrays.asList(
							new String[] { "08", "12" },
							new String[] { "12", "16" },
							new String[] { "16", "20" })));
		}
	}
}
