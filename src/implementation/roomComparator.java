package implementation;
import java.util.Comparator;

import interfaces.Room;

public class roomComparator implements Comparator<Room> {

	@Override
	//requires: 2 instances of type Room as 2 parameters
	//effects: return 0 if they consist of the same room number in a the same building and an integer otherwise
	public int compare(Room r1, Room r2) {
		String s1 = r1.getBuilding() + " " + r1.getRoomNumber();
		String s2 = r2.getBuilding() + " " + r2.getRoomNumber();
		return s1.compareTo(s2);
	}

}
