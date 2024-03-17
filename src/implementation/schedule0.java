package implementation;
import java.time.LocalTime;

import interfaces.Course;
import interfaces.Instructor;
import interfaces.Room;
import interfaces.Schedule;

import java.time.DayOfWeek;


public class schedule0 implements Schedule{
	private Room room;
	private LocalTime beginT,endT;
	private Instructor instructor;
	private Course course;
	private DayOfWeek day;
	
	//requires: a room, a begin time, an end time, an instructor, a course and day of the week
	//effects:create an instance of schedule0
	public schedule0(Room room, LocalTime beginT, LocalTime endT, 
			Instructor instructor, Course course, DayOfWeek day) {
		super();
		this.room = room;
		this.beginT = beginT;
		this.endT = endT;
		this.instructor = instructor;
		this.course = course;
		this.day = day;
	}
	
	@Override
	//requires:none
	//effects:return a Room witha  specific room number and building
	public Room getRoom() {
		return room;
	}
	
	@Override
	//requires:none
	//effects:return a day of the week
	public DayOfWeek getDay() {
		return day;
	}

	

	@Override
	//requires:none
	//effects:returns the begin time of the schedule of the course
	public LocalTime getFromTime() {
		return beginT;
	}

	@Override
	//requires:none
	//effects:returns the end time of the schedule of the course
	public LocalTime getToTime() {
		return endT;
	}

	@Override
	//requires:none
	//effects:return the instructor giving the course
	public Instructor getInstructor() {
		return instructor;
	}

	@Override
	//requires:none
	//effects:return the course given
	public Course getCourse() {
		return course;
	}
	
	
}
