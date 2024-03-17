package interfaces;
import java.time.LocalTime;
import java.time.DayOfWeek;
public interface Schedule {
	Room getRoom();
	LocalTime getFromTime();
	LocalTime getToTime();
	Instructor getInstructor();
	Course getCourse();
	DayOfWeek getDay();
}