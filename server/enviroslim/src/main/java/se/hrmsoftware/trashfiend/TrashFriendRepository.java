package se.hrmsoftware.trashfiend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TrashFriendRepository {
	
	public TrashFriendRepository(String dataFileName) {
		try (Stream<String> stream = Files.lines(Paths.get(dataFileName))) {
			stream.forEach(System.out::println);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}	
}
