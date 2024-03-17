package UserInteraction;
import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import interfaces.Course;
import interfaces.CourSeera;
import interfaces.CourSeeraFactory;
import interfaces.Downloader;
import interfaces.HtmlToCsv;
import interfaces.CsvToDb;
import interfaces.Instructor;
import interfaces.Schedule;
import interfaces.Room;

import implementation.Course0;
import implementation.Courseera0;
import implementation.CourSeeraFactory0;
import implementation.downloader0;
import implementation.htmlToCsv0;
import implementation.CsvToDb0;
import implementation.instructor0;
import implementation.schedule0;
import implementation.room0;
import implementation.roomComparator;
import implementation.scheduleComp;
import implementation.InstructorComp;

public class UserInteraction {

	public static void main(String[] args) throws IOException {	
	WelcomeCmds();
	input();
		
	}
	public static void WelcomeCmds() {
		//requires:none
		//effects:print a welcome message, list the available commands and an explanation of the requirements and effects of each of the commands
		String cmd1 = "CourSeera-RoomSchedule-Building  #Room";
		String cmd2 = cmd1 + "-year/month/day\n month and day must be 2-digit long";
		String cmd3 = cmd1 + "-DayOfWeek";
		String cmd4 = "CourSeera-WhoWasThereLast- Building  #Room";
		String cmd5 = "CourSeera-WhoIsThereNow-Building  #Room";
		String cmd6 = "CourSeera-ProfSchedule-Instructor'sFirstName  Instructor'sLastName";
		String cmd7 = "CourSeera-whereIsProf-Instructor'sFirstName  Instructor'sLastName";
		String cmd8 = "CourSeera-whereWillProfBe-Instructor'sFirstName  Instructor'sLastName";
		System.out.println("\t\t\t\t\t\t~~~~~Welcome To CourSeera :)~~~~~\n\n\n Use one of the below "
				+ "commands to search in AUB Dynamic Course Schedule\n Watch Out!!  The commands are case-sensitive!\nWait until all file are loaded hen type your command in the space provided!\n");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		System.out.println("cmd1: " + cmd1 + "\t\t\t\t\t\\\\the schedules of the courses given at the provided room,building is all capitals");
		System.out.println(
				"cmd2: " + cmd2 + "\t\t\t\t\t\t\\\\the schedules of the courses given at the room on in the provided date, building is all capitals");
		System.out.println(
				"cmd3: " + cmd3 + "\t\t\t\t\\\\the schedules of the courses given at the room on the provided day, building is all capitals");
		System.out.println("cmd4: " + cmd4
				+ "\t\t\t\t\\\\the course and instructor name for the last time this room was occupied(according to now)");
		System.out.println(
				"cmd5: " + cmd5 + "\t\t\t\t\t\\\\the course and instructor name currently occupying the provided room");
		System.out.println("cmd6: " + cmd6 + "\t\\\\the instructor's weekly schedule(day,time,room), instructor's first and last name is 1st-letter-capitalized");
		System.out.println("cmd7: " + cmd7 + "\t\t\\\\the room where a prof is currently teaching (if any), instructor's first and last name is 1st-letter-capitalized");
		System.out.println("cmd8: " + cmd8 + "\t\\\\the instructor's schedule for today,  instructor's first and last name is 1st-letter-capitalized");
		System.out.println("Type \"end\" to end the program");
	}
	
	public static void input() {
		//requires:none
		//effects:keep on taking input from the user until he/she asks to end the program
		
		CourSeeraFactory cf = new CourSeeraFactory0();
		CourSeera c = cf.createInstance();
		TreeMap<Room,List<Schedule>> roomsSchedule = c.roomSchedule();

		System.out.print("Enter Your Command: ");
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		while(!str.equalsIgnoreCase("end")) {
			
			
			String cmd[] = str.split("-");
			if(cmd.length<3 && str.matches("\\w+-\\w+-\\w+\\s+\\w+")==false &&
					str.matches("\\w+-\\w+-\\w+\\s+\\w+-\\d+/\\d+/\\d+")==false &&
					str.matches("\\w+-\\w+-\\w+\\s+\\w+-\\w+")==false) {
				System.out.println("Invalid Command!!");
				System.out.println("Enter a valid Command: ");
				input = new Scanner(System.in);
				str = input.nextLine();
				cmd = str.split("-");

			}
			if (cmd[0].equalsIgnoreCase("courseera")) {
				if (cmd[1].equalsIgnoreCase("RoomSchedule")) {
					String roomdetails[] = cmd[2].split("\\s+");
					Room room = new room0(roomdetails[0],roomdetails[1]);
					List<Schedule> roomSched;
					if(cmd.length==3) {
						roomSched = c.roomSchedule(room);
						for(Schedule sched:roomSched) {
							System.out.println(sched.getDay() + " " + sched.getFromTime() + "-" + sched.getToTime() + " "
									+ sched.getCourse().getSubject() + " " + sched.getCourse().getCourse_num() + ","
									+ sched.getCourse().getInstructor_first() + " " + sched.getCourse().getInstructor_last());
						}
					}
					if(cmd.length==4) {
						if(cmd[3].matches("2021/(0[1-9]|1[0-2])/(0[1-9]|1[0-9]|2[0-9]|3[0-1])")) {
							cmd[3]=cmd[3].replaceAll("/","-");
							LocalDate date = LocalDate.parse(cmd[3]);
							if(date.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)==0) {System.out.println("No Classes on Sunday!");}
							else {			
								roomSched = c.roomSchedule(room, date);
								for(Schedule sched:roomSched) {
									System.out.println(sched.getDay() + " " + sched.getFromTime() + "-" + sched.getToTime() + " "
											+ sched.getCourse().getSubject() + " " + sched.getCourse().getCourse_num() + ","
											+ sched.getCourse().getInstructor_first() + " " + sched.getCourse().getInstructor_last());
								}
							}
							
						}
						if(isDayofWeek(cmd[3])) {
							DayOfWeek day = dayofWeek(cmd[3]);
							if(day.compareTo(DayOfWeek.SUNDAY)==0) {
								System.out.println("No Classes on Sunday!");
							}
							roomSched = c.roomSchedule(room, day);
							for(Schedule sched:roomSched) {
								System.out.println(sched.getDay() + " " + sched.getFromTime() + "-" + sched.getToTime() + " "
										+ sched.getCourse().getSubject() + " " + sched.getCourse().getCourse_num() + ","
										+ sched.getCourse().getInstructor_first() + " " + sched.getCourse().getInstructor_last());
							}
						}
						
						
					}
					
				}
				if (cmd[1].equalsIgnoreCase("WhoWasThereLast")) {
					String roomdetails[] = cmd[2].split("\\s+");
					String buildingname = roomdetails[0];
					String buildingnumber = roomdetails[1];
					Schedule r = c.whoWasThereLast(new room0(buildingname, buildingnumber));
					System.out.println(r.getDay() + " " + r.getFromTime() + "-" + r.getToTime() + " "
							+ r.getCourse().getSubject() + " " + r.getCourse().getCourse_num() + ","
							+ r.getCourse().getInstructor_first() + " " + r.getCourse().getInstructor_last());
					
				}
				if (cmd[1].equalsIgnoreCase("WhoIsThereNow")) {
					String roomdetails[] = cmd[2].split("\\s+");
					String buildingname = roomdetails[0];
					String buildingnumber = roomdetails[1];
					Schedule r = c.whoIsThereNow(new room0(buildingname, buildingnumber));
					if(r==null) {System.out.println("the room is currently not occupied");}
					else {
						
						System.out.println(r.getDay() + " " + r.getFromTime() + "-" + r.getToTime() + " "
								+ r.getCourse().getSubject() + " " + r.getCourse().getCourse_num() + ","
								+ r.getCourse().getInstructor_first() + " " + r.getCourse().getInstructor_last());
					}
					
				}
				if (cmd[1].equalsIgnoreCase("ProfSchedule")) {
					String instructor[] = cmd[2].split("\\s+");
					String InstructorFirstName = instructor[0];
					String InstructorLastName = instructor[1];
					List<Schedule> r = c.profSchedule(new instructor0(InstructorFirstName, InstructorLastName));
					for (Schedule f : r) {
						System.out.println(f.getDay() + " " + f.getFromTime() + "-" + f.getToTime() + " "+f.getCourse().getBldg() + " "+f.getCourse().getRoom()+" "
								+ f.getCourse().getSubject() + " " + f.getCourse().getCourse_num() + ","
								+ f.getCourse().getInstructor_first() + " " + f.getCourse().getInstructor_last());
					}
					
				}
				if (cmd[1].equalsIgnoreCase("whereIsProf")) {
					String instructor[] = cmd[2].split("\\s+");
					String InstructorFirstName = instructor[0];
					String InstructorLastName = instructor[1];
					Schedule r = c.whereIsProf(new instructor0(InstructorFirstName, InstructorLastName));
					if(r==null) {
						System.out.println("The professor has no classes now!");
					}
					else {
						
						System.out.println(r.getDay() + " " + r.getFromTime() + "-" + r.getToTime() + " " + r.getRoom().getBuilding() + " " + r.getRoom().getRoomNumber() + " "
								+ r.getCourse().getSubject() + " " + r.getCourse().getCourse_num() + ","
								+ r.getCourse().getInstructor_first() + " " + r.getCourse().getInstructor_last());
					}
					
				}
				if (cmd[1].equalsIgnoreCase("whereWillProfBe")) {
					String instructor[] = cmd[2].split("\\s+");
					String InstructorFirstName = instructor[0];
					String InstructorLastName = instructor[1];
					List<Schedule> r = c.whereWillProfBe(new instructor0(InstructorFirstName, InstructorLastName));
					if(r==null) {
						System.out.println("No Classes on Sunday!");
					}
					else {
						
						for (Schedule f : r) {
							System.out.println(f.getDay() + " " + f.getFromTime() + "-" + f.getToTime() + " " + f.getRoom().getBuilding() +" " + f.getRoom().getRoomNumber()+" "
									+ f.getCourse().getSubject() + " " + f.getCourse().getCourse_num() + ","
									+ f.getCourse().getInstructor_first() + " " + f.getCourse().getInstructor_last());
						}
					}
					
				}
			} else if(str.equalsIgnoreCase("end")){
				System.out.println("System is closed sucessfully!");
				}
			else {
				System.out.println("not a courseera Command");
				
			}
			

			System.out.print("Enter Your Command: ");
			input = new Scanner(System.in);
			str = input.nextLine();
			cmd = str.split("-");
		}

		
		

	}
	public static boolean isDayofWeek(String day) {
		//requires: a day of week in string format
		//effects: return true if the string is a valid day of the week and false otherwise
		if (day.equalsIgnoreCase(String.valueOf(DayOfWeek.MONDAY))) {
			return true;
		}
		if (day.equalsIgnoreCase(String.valueOf(DayOfWeek.TUESDAY))) {
			return true;
		}
		if (day.equalsIgnoreCase(String.valueOf(DayOfWeek.WEDNESDAY))) {
			return true;
		}
		if (day.equalsIgnoreCase(String.valueOf(DayOfWeek.THURSDAY))) {
			return true;
		}
		if (day.equalsIgnoreCase(String.valueOf(DayOfWeek.FRIDAY))) {
			return true;
		}
		if (day.equalsIgnoreCase(String.valueOf(DayOfWeek.SATURDAY))) {
			return true;

		}
		return false;
	}
	public static DayOfWeek dayofWeek(String day) {
		//requires: a day of week in string format
		//effects: return a DayOfWeek if the input is a valid day of the week
		if(day.equalsIgnoreCase("monday")) {
			return DayOfWeek.MONDAY;
		}
		if(day.equalsIgnoreCase("tuesday")) {
			return DayOfWeek.TUESDAY;
		}if(day.equalsIgnoreCase("wednesday")) {
			return DayOfWeek.WEDNESDAY;
		}if(day.equalsIgnoreCase("thursday")) {
			return DayOfWeek.THURSDAY;
		}if(day.equalsIgnoreCase("firday")) {
			return DayOfWeek.FRIDAY;
		}if(day.equalsIgnoreCase("saturday")) {
			return DayOfWeek.SATURDAY;
		}
		return DayOfWeek.SUNDAY;
	}
}
