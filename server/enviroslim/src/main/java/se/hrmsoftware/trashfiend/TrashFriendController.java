package se.hrmsoftware.trashfiend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trashme")
public class TrashFriendController {

	private static TrashFriendRepository repo;
	
	public TrashFriendController() {
		String fileName =
				TrashFriendRepository.class.getClassLoader().getResource("norremark_2015-08-01-2015-12-03.csv").getFile();
		repo = new TrashFriendRepository(fileName);
	}
	
	
	@RequestMapping("/now")
	TrashFriendReport now() {
		return new TrashFriendReport();
	}
	
}
