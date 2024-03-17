package implementation;
import java.util.ArrayList;
import java.util.List;

import interfaces.CourSeera;
import interfaces.CourSeeraFactory;
import interfaces.Course;
import interfaces.CsvToDb;
import interfaces.Downloader;

public class CourSeeraFactory0 implements CourSeeraFactory{

	@Override
	//requires: none
	//effects: create an instanc of courseerafactor that return a courseera object whose list of courses consist of all the courses in the dynamic schedule
	public CourSeera createInstance() {
		List<Course>lstCourses = new ArrayList<Course>();
		for (char c='A'; c<='Z'; c++) {
			System.out.println("Letter "+c+" Courses are loading...");
			Downloader d = new downloader0();
			String htmlfile = c + ".html";
			d.downloadHtmlToFile("https://www-banner.aub.edu.lb/catalog/schd_"+c+".htm", htmlfile);
			htmlToCsv0 htmltocsv = new htmlToCsv0();
			String csvFile =c+ ".csv";
			htmltocsv.htmlToCsv(htmlfile,csvFile);
			CsvToDb datab = new CsvToDb0();
			datab.csvToDb(lstCourses, csvFile);
		}
		
		return new Courseera0(lstCourses);
	}
	

}
