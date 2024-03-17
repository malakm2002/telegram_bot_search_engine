package implementation;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import interfaces.CourSeera;
import interfaces.Course;
import interfaces.Instructor;
import interfaces.Room;
import interfaces.Schedule;

import java.time.Clock;
import java.time.DayOfWeek;


public class Courseera0 implements CourSeera {
	private List<Course> lstCourses;
	private TreeMap<Room, List<Schedule>> roomsSchedules;
	private roomComparator roomcomp=new roomComparator();;
	//requires:a list of type Course
	//effects:a parameterized constructor to instantiate CourSeera using it
	public Courseera0(List<Course> lstCourses) {
		super();
		this.lstCourses = lstCourses;
	}

	@Override
	//requires:none
	//effects:return a treemap whose keys are of type room and values are of type list of schedule: the list of schedule given in the key room
	public TreeMap<Room, List<Schedule>> roomSchedule() {
		this.roomsSchedules= new TreeMap<Room, List<Schedule>>(roomcomp);
		for (Course c : this.lstCourses) {
			Room room = (Room) new room0(c.getBldg(), c.getRoom());
			
			if (c.getMonday()) {
				MappingSchedule(room, c, DayOfWeek.MONDAY);
			}
			if (c.getTuesday()) {
				MappingSchedule(room, c, DayOfWeek.TUESDAY);
			}
			if (c.getWednesday()) {
				MappingSchedule(room, c, DayOfWeek.WEDNESDAY);
			}
			if (c.getThursday()) {
				MappingSchedule(room, c, DayOfWeek.THURSDAY);
			}
			if (c.getFriday()) {
				MappingSchedule(room, c, DayOfWeek.FRIDAY);
			}
			if (c.getSaturday()) {
				MappingSchedule(room, c, DayOfWeek.SATURDAY);
			}
		}
				return this.roomsSchedules;

	}
	@Override
	//requires: an instantiated room
	//effects:return the list of the schedules of the provided room 
	public List<Schedule> roomSchedule(Room room) {
		
		return this.roomsSchedules.get(room);
	}

	@Override
	//requires:an instantiated room and a specific date whose month and day must be 2-digit long i.e 01 instead of 1
	//effects:return the list of schedules of the provided room in the specific date
	public List<Schedule> roomSchedule(Room room, LocalDate date) {
		List<Schedule> roomSchedAtDate = new ArrayList<Schedule>();
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		for(Map.Entry<Room, List<Schedule>> e:this.roomsSchedules.entrySet()) {
			if(roomcomp.compare(room, e.getKey()) ==0) {
				for(Schedule s:e.getValue()) {
					if(dayOfWeek.compareTo(s.getDay())==0) {
						roomSchedAtDate.add(s);
					}
				}
			}
		}
		Collections.sort(roomSchedAtDate, new scheduleComp());
		return roomSchedAtDate;
	}

	@Override
	//requires: an instantiated room and a day of type DayOfWeek
	//effects:return the list of schedules of the provided room on that day
	public List<Schedule> roomSchedule(Room room, DayOfWeek day) {
		List<Schedule> lstSched = new ArrayList<Schedule>();
		for(Map.Entry<Room,List<Schedule>> e:this.roomsSchedules.entrySet())
			for(Schedule s :e.getValue()) {
				if(roomcomp.compare(s.getRoom(), room) ==0 && s.getDay().compareTo(day) ==0)
					lstSched.add(s);
				
			}
		Collections.sort(lstSched, new scheduleComp());

		return lstSched;
	}

	@Override
	//requires:an instantiated room
	//effects: return the schedule of the course that was lastly given accordng to now(the date and time of the PC), 
	//empty schedule if no course is given now
	public Schedule whoWasThereLast(Room room) {
		 DayOfWeek today = this.today();
		 int i=today.getValue(), count=0;
		 LocalTime now = this.now();
		List<Schedule> todaySched =this.roomSchedule(room, today);
		for(Schedule s:todaySched) {
			if(now.compareTo(s.getFromTime())>=0 &&now.compareTo(s.getToTime())<0) {
				break;
			}
			count++;
		}
				

				if(i>=1&& i<=6&&count!=0) {
					return todaySched.get(count-1);
				}
				if(i>=1&&count==0) {
					List<Schedule> roomSched = this.roomSchedule(room);
					DayOfWeek lastDay = roomSched.get(roomSched.size()-1).getDay();
					List<Schedule> ss = this.roomSchedule(room, lastDay);
					return ss.get(ss.size()-1);
				}		
		

		Schedule s = new schedule0(room, LocalTime.parse("00:01"), LocalTime.parse("00:01"), 
				new instructor0(" "," "), 
				new Course0(0, " ", " ", " ", " ", 0, " ", 0, 0,  LocalTime.parse("00:01"),  LocalTime.parse("00:01"), 
						" ",  " ", false, false, false, false, false, false,  " ",  " "), DayOfWeek.SUNDAY);
		return s;
	
	}
	@Override
	//requires: an instantiated room
	//effects: return the schedule of the course given now, and null if the room is currently not occupied
	public Schedule whoIsThereNow(Room room) {
		DayOfWeek today = this.today();
		 int i=0;
		 LocalTime now = this.now();
		 List<Schedule> todaySched =this.roomSchedule(room, today);
			for(Schedule s: todaySched) {
				if(today.compareTo(s.getDay())==0 && now.compareTo(s.getFromTime())>0 && now.compareTo(s.getToTime())<0) {
						return todaySched.get(i);
						
				}
				i++;
			}
		 
		
		return null;
	}

	@Override
	//requires: an instantiated instructor
	//effects: return the list of schedules of the instructor sorted by day and time 
	public List<Schedule> profSchedule(Instructor instructor) {
		scheduleComp scheduleComparator = new scheduleComp();
		List<Schedule> profSched = new ArrayList<Schedule>();
		InstructorComp  instcomp = new InstructorComp();
		for(Map.Entry<Room,List<Schedule>> e:this.roomsSchedules.entrySet()) {
			for(Schedule s: e.getValue()) {
				if(instcomp.compare(instructor, s.getInstructor())==0) {
					profSched.add(s);
				}
			}
		}
		Collections.sort(profSched, new scheduleComp());

		return profSched;
	}

	@Override
	//requires:an instantiated instructor
	//effects:return the schedule of the instructor if he is teaching now and null otherwise
	public Schedule whereIsProf(Instructor instructor) {
		List<Schedule> profSched = this.profSchedule(instructor);
		DayOfWeek today = this.today();
		LocalTime now = this.now();
		for (Schedule s : profSched) {
			if (today.compareTo(s.getDay()) == 0 && now.compareTo(s.getFromTime()) > 0
					&& now.compareTo(s.getToTime()) < 0) {
				return s;
			}
		}

		return null;
	}

	@Override
	//requires:an instantiated professor
	//effects:returns list of the instrcutor's schedules for today and null if the today is sunday  
	public List<Schedule> whereWillProfBe(Instructor instructor) {
		List<Schedule> profSched = this.profSchedule(instructor);
		List<Schedule> todaySched = new ArrayList<Schedule>();
		DayOfWeek today = this.today();
		if(today.compareTo(DayOfWeek.SUNDAY)==0) {return null;}
		for(Schedule s: profSched) {
			if(s.getDay().compareTo(today)==0) {
				todaySched.add(s);
			}
		}
		Collections.sort(todaySched, new scheduleComp());

		return todaySched;
	}

	//requires:none
	//effects:return a list of courses
	public List<Course> getLstCourses() {
		return lstCourses;
	}
	//requires:none
	//effects:return a treemap whose keys are of type room and values are of type list of schedule: the list of schedule given in the key room
	public TreeMap<Room, List<Schedule>> getRoomsSchedules() {
		return roomsSchedules;
	}
	//requires: an instantaited room, course and day of the week
	//effects: if the room is not already in the map, then it is added with an empty list of schedules
			//otherwise, the schedule of the course on that specific day of the week is added to the list of schedule of the specific room
	private void MappingSchedule(Room room, Course course, DayOfWeek day) {
	
		if (this.roomsSchedules.get(room) == null) {
			this.roomsSchedules.put(room, new ArrayList<Schedule>());
		}

		Schedule s = new schedule0(room, course.getBegin_Time(), course.getEnd_Time(),
				new instructor0(course.getInstructor_first(), course.getInstructor_last()), course, day);
		this.roomsSchedules.get(room).add(s);
	
	}
	private DayOfWeek today() {
		//requires:none
		//effects:return today's day of the week
		Clock cl = Clock.systemUTC();
		 LocalDate lt = LocalDate.now(cl);
		return lt.getDayOfWeek();
	}
	private LocalTime now() {
		//requires:none
		//effects:return now's time
		Calendar rightNow = Calendar.getInstance();
		int hour = rightNow.get(Calendar.HOUR_OF_DAY);
		int minutes = rightNow.get(Calendar.MINUTE);
		String mins=String.valueOf(minutes),hrs=String.valueOf(hour),time="";
		if (mins.length() == 1) {
			 mins = String.valueOf(0)+ String.valueOf(minutes);
		}
		if(String.valueOf(hour).length()==1) {
			hrs=String.valueOf(0)+ String.valueOf(hour);
		}
		time = hrs + ":" + mins;
		return LocalTime.parse(time);
		
	}

}
