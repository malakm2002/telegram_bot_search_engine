package implementation;
import java.util.Comparator;

import interfaces.Schedule;

public class scheduleComp implements Comparator<Schedule> {

	@Override
	//requires:2 instances of schedule as 2 parameters
	//effects:compare them, if different days, compare the days and return a negative integer if the 1st is less than the second and a positive integer otherwise
	//if same day, returns 0 if same time and a negative integer if the 1st is before the second and a positive integer otherwise
	public int compare(Schedule o1, Schedule o2) {
		if (o1.getDay().compareTo(o2.getDay()) == 0)
			return o1.getFromTime().compareTo(o2.getFromTime());
		return o1.getDay().compareTo(o2.getDay());
	}
}
