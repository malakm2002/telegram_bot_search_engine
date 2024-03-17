package implementation;
import java.io.*;

import interfaces.HtmlToCsv;

public class htmlToCsv0 implements HtmlToCsv{
	private String htmlFile,csvFile;
	public String getHtmlFile() {
		//requires:none
		//effects:return the name of the html file
		return htmlFile;
	}
	public String getCsvFile() {
		//requires: none
		//effects:return the name of the csv file
		return csvFile;
	}
	@Override
	//requires: the htmlfile name and the name of the csv file
	//effects:write each rom of the html file to the csvfile, where colums in htmlfile are seperated by commas in csvfile
	public void htmlToCsv(String htmlFile, String csvFile){
		
		try {
			
			
			this.htmlFile = htmlFile;
			this.csvFile = csvFile;
			StringBuilder contentBuilder = new StringBuilder();
			BufferedReader in = new BufferedReader(new FileReader(htmlFile));
			String read;
			while((read=in.readLine())!=null) {contentBuilder.append(read);}
			in.close();
			String htmlContent = contentBuilder.toString();
			
				
			String ignoreHeader = htmlContent.replace(htmlContent.substring(0,htmlContent.indexOf("Fall 2021-2022(202210)")),"");
			String fall=ignoreHeader.replace(ignoreHeader.substring(ignoreHeader.indexOf("Spring 2021-2022(202220)"),ignoreHeader.length()-1), "");
			String[] rows = fall.split("</TR>");
			FileWriter write = new FileWriter(new File(csvFile));
			for(int i = 0;i<rows.length-1;i++) {
				
				String row0 = rows[i].replaceAll("\\s+","");
				row0=row0.replaceAll(",", ":");
				String row = "";
				String[] columnsperRow = row0.split("</TD>");
				int j;
				for(j =0 ;j<columnsperRow.length;j++) {
					String col = columnsperRow[j].replaceAll("\\s+","");
					
					String temp = col.replaceAll("<TD>",",");
					row+=temp;
					
					
				}
				
				if(i>0) {row=row.replaceFirst(",","");}
				String temp[]  = row.split(",");
				for(int k=0;k<temp.length;k++) {
					if(temp[k].contains(":")) {
						temp[k] = "\""+temp[k].replaceAll(":", ",") + "\"";
					}
				}
				write.write(row);
				write.write("\n");
			}
			write.close();
			System.out.println("To CSV");
		}
		catch(IOException e) {System.out.println("IO Exception Thrown!!");}
		catch(StringIndexOutOfBoundsException e) {System.out.println("No Courses Available!");}
		
	}
	

}
