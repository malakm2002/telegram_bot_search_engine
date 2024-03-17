package implementation;

import java.time.LocalTime;

import interfaces.Course;

public class Course0 implements Course {
	private int crn, actualEnrol, availSeats;
	private String room, section, courseNum, subject, title, college, building, instructorFirst, instructorLast;
	float creditHrs;
	LocalTime beginT, endT;
	boolean monday = false, tuesday = false, wednesday = false, thursday = false, friday = false, saturday = false;
	
	//requires:none
	//effects:return the crn of the course
	public int getCrn() {
		return crn;
	}
	//requires:none
	//effects:return the subject of the course
	public String getSubject() {
		return subject;
	}
	//requires:none
	//effects:return the course number
	public String getCourse_num() {
		return courseNum;
	}
	//requires:none
	//effects:return the section number in string format
	public String getSection() {
		return section;
	}
	//requires:none
	//effects:return the title of the course
	public String getTitle() {
		return title;
	}
	//requires:none
	//effects:return the number of credits in float format
	public float getCredithrs() {
		return creditHrs;
	}
	//requires:none
	//effects:return the college in which the course is given
	public String getCollege() {
		return college;
	}
	//requires:none
	//effects:return the number of actual enrollment: the number of students registered in the course
	public int getActual_enrol() {
		return actualEnrol;
	}
	//requires:none
	//effects:return the number of seats left in the course
	public int getSeats_available() {
		return availSeats;
	}
	//requires:none
	//effects:return the begin time of the course
	public LocalTime getBegin_Time() {
		return beginT;
	}
	//requires:none
	//effects:return the end time of the course
	public LocalTime getEnd_Time() {
		return endT;
	}
	//requires:none
	//effects:return the name of the building in which the course is given
	public String getBldg() {
		return building;
	}
	//requires:none
	//effects:return the room number in string format
	public String getRoom() {
		return room;
	}
	//requires:none
	//effects:return true if the course is given on monday and false otherwise
	public boolean getMonday() {
		return monday;
	}
	//requires:none
	//effects:return true if the course is given on tuesday and false otherwise
	public boolean getTuesday() {
		return tuesday;
	}
	//requires:none
	//effects:return true if the course is given on wednesday and false otherwise
	public boolean getWednesday() {
		return wednesday;
	}
	//requires:none
	//effects:return true if the course is given on thursday and false otherwise
	public boolean getThursday() {
		return thursday;
	}
	//requires:none
	//effects:return true if the course is given on friday and false otherwise
	public boolean getFriday() {
		return friday;
	}
	//requires:none
	//effects:return true if the course is given on saturday and false otherwise
	public boolean getSaturday() {
		return saturday;
	}
	//requires:none
	//effects:return the instructor's first name
	public String getInstructor_first() {
		return instructorFirst;
	}
	//requires:none
	//effects:return the instructor's last name
	public String getInstructor_last() {
		return instructorLast;
	}
	//requires: crn,subject, coursenumber, section number, title,number of credits, 
	//college,actual enrollement,the available seats, the begin time, the end time
	//the building name, the room number, set the days to true if the course is given on that days, and left false otherwise, 
	//the instructor's first name and the instructor's last name
	//effects:a parameterized constructor to instantiate Course using it
	public Course0(int crn, String subject, String courseNum, String section, String title, float creditHrs,
			String college, int actualEnrol, int availSeats, LocalTime beginT, LocalTime endT, String building,
			String room, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday,
			boolean saturday, String instructorFirst, String instructorLast) {
		super();
		this.crn = crn;
		this.courseNum = courseNum;
		this.section = section;
		this.creditHrs = creditHrs;
		this.actualEnrol = actualEnrol;
		this.availSeats = availSeats;
		this.room = room;
		this.subject = subject;
		this.title = title;
		this.college = college;
		this.building = building;
		this.instructorFirst = instructorFirst;
		this.instructorLast = instructorLast;
		this.beginT = beginT;
		this.endT = endT;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
	}
}
