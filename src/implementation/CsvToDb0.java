package implementation;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import interfaces.Course;
import interfaces.CsvToDb;

public class CsvToDb0 implements CsvToDb {

	String csvFile;
	List<Course> courses;
	//requires: a list of courses and  a csv file
	//effects: accumulate all the rows in the list of courses where each row consists of a course
	public void csvToDb(List<Course> courses, String csvFile) {
		try {
			
			
			this.courses = courses;
			this.csvFile = csvFile;
			Scanner input = new Scanner(new File(csvFile));
			while (input.hasNextLine()) {
				String row = input.nextLine();
				String col[] = row.split(",");
				
				boolean m = false, t = false, w = false, r = false, f = false, s = false;
				if (col[15].equalsIgnoreCase("M")) {
					m = true;
				}
				if (col[16].equalsIgnoreCase("T")) {
					t = true;
				}
				if (col[17].equalsIgnoreCase("W")) {
					w = true;
				}
				if (col[18].equalsIgnoreCase("R")) {
					r = true;
				}
				if (col[19].equalsIgnoreCase("F")) {
					f = true;
				}
				if (col[20].equalsIgnoreCase("S")) {
					s = true;
				}
				
				String beginT = col[11], endT = col[12];
				
				
				if (beginT.length() == 3 && !beginT.equalsIgnoreCase(".")) {
					beginT = "0"+beginT.charAt(0) + ":" + beginT.substring(1);
				}
				if (endT.length() == 3 && !endT.equalsIgnoreCase(".")) {
					endT = "0"+endT.charAt(0) + ":" + endT.substring(1);
				}
				if (beginT.length() == 4 && !beginT.equalsIgnoreCase(".")) {
					beginT = beginT.substring(0, 2) + ":" + beginT.substring(2);
				}
				if (endT.length() == 4 && !endT.equalsIgnoreCase(".")) {
					endT = endT.substring(0, 2) + ":" + endT.substring(2);
				}
				
				if (!col[11].equalsIgnoreCase(".") && !col[12].equalsIgnoreCase(".")) {
					
					// if we have a begin and end time of the course
					
					Course course = new Course0(Integer.parseInt(col[1]), col[2], col[3], 
							col[4], col[5],
							Float.parseFloat(col[6]), col[8], 
							Integer.parseInt(col[9]),
							Integer.parseInt(col[10]),
							LocalTime.parse(beginT), LocalTime.parse(endT),
							col[13], col[14], m, t, w, r,
							f, s, col[33], col[34]);
					courses.add(course);
				}
				else if(col[11].equalsIgnoreCase(".") && col[12].equalsIgnoreCase(".")) {
					
					//no start and end time specified
					LocalTime t1 = LocalTime.parse("00:01");
					Course course = new Course0(Integer.parseInt(col[1]), col[2], col[3], 
							col[4], col[5],
							Float.parseFloat(col[6]), col[8], 
							Integer.parseInt(col[9]),
							Integer.parseInt(col[10]),
							t1,t1,
							col[13], col[14], m, t, w, r,
							f, s, col[33], col[34]);
					courses.add(course);

				}
				
			}
		System.out.println("To DB");	
		}
		catch(FileNotFoundException e) {System.out.println("File is Not Found!!");}
	}
	//requires:none
	//effects:return the name of the csv file
	public String getCsvFile() {
		return csvFile;
	}
	//requires:none
	//effects:return the list of courses
	public List<Course> getCourses() {
		return courses;
	}

}
