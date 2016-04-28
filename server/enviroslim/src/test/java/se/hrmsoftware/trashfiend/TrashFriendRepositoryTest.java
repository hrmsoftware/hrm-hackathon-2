package se.hrmsoftware.trashfiend;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TrashFriendRepositoryTest {
	@Test public void name() throws Exception {
		TrashFriendRepository repository = init();

		assertEquals(65, repository.getCollect().get("2015-12-02-10").size());
	}

	private TrashFriendRepository init() {
		String fileName =
				TrashFriendRepositoryTest.class.getClassLoader().getResource("test.csv").getFile();

		return new TrashFriendRepository(fileName);
	}

	@Test public void name2() throws Exception {
		TrashFriendRepository repository = init();

		List<List<Integer>> periodData = repository
				.getPeriodData(Arrays.asList(new String[] { "10", "14" }, new String[] { "14", "20" }));
		assertEquals(65, periodData);
	}
}