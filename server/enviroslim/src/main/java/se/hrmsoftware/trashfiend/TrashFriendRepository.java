package se.hrmsoftware.trashfiend;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrashFriendRepository {

	private List<Trash> trashList;

	public TrashFriendRepository(String dataFileName) {
		ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
		String[] columns = new String[] {"date", "time", "velocity", "length"}; // the fields to bind do in your JavaBean
		strategy.setColumnMapping(columns);
		strategy.setType(Trash.class);
		CsvToBean<Trash> csvToBean = new CsvToBean<>();

		try (Reader reader = Files.newBufferedReader(Paths.get(dataFileName))) {
			trashList = csvToBean.parse(strategy, new CSVReader(reader, ';', '"', 1));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Trash> getTrashList(Predicate<Trash> predicate) {
		return trashList.stream().filter(predicate).collect(Collectors.toList());
	}

	public Map<String, List<Trash>> getCollect() {
		return trashList.stream()
				.collect(Collectors.groupingBy(t -> t.getDate() + " " + t.getTime().substring(0,2)+":00"));
	}

	public List<List<Integer>> getPeriodData(List<String[]> slots) {
		Map<DayOfWeek, List<Trash>> collect = trashList.stream()
				.collect(Collectors.groupingBy(t -> LocalDate.parse(t.getDate()).getDayOfWeek()));

		List<List<Integer>> list = collect.keySet().stream().sorted().map(collect::get).map(v -> {
			Map<String, List<Trash>> map = v.stream().collect(Collectors.groupingBy(s -> convertTime(slots, s)));

			List<List<Trash>> trashes = slots.stream()
					.map(s -> Optional.ofNullable(map.get(s[0])).orElse(new ArrayList<>()))
					.collect(Collectors.toList());
			return trashes.stream().map(s -> s.size()).collect(Collectors.toList());
		}).collect(Collectors.toList());

		return IntStream.range(0,slots.size())
				.mapToObj(i ->
						IntStream.range(0, list.size())
						.mapToObj(j -> list.get(j).get(i)).collect(Collectors.toList()))
				.collect(Collectors.toList());
	}

	private String convertTime(List<String[]> slots, Trash t) {
		return slots.stream()
				.filter(s -> t.getTime().compareTo(s[0]) >= 0 && t.getTime().compareTo(s[1]) < 0)
				.findFirst().map(s -> s[0]).orElse("N/A");
	}
}
