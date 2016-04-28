package se.hrmsoftware.trashfiend;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrashFriendControllerTest {
	@Test public void name() throws Exception {
		TrashFriendController test = new TrashFriendController();
		System.out.println(test.getCurrentStatus());
	}
}