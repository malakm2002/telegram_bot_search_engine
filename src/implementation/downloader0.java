package implementation;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import interfaces.Downloader;

public class downloader0 implements Downloader {
	private String url,htmlfile;
	public String getUrl() {
		//requires:none
		//effects:return the url in string format
		return url;
	}
	public String gethtmlFile() {
		//requires:none
		//effects:return the name of the html file
		return htmlfile;
	}
	
	@Override
	//requires:a url in string format, and the name of the htmlfile
	//effects:downlod the html page in the provided url 
	public void downloadHtmlToFile(String url, String file){
		try {
			this.url = url;
			this.htmlfile = file;
			ReadableByteChannel rbc = Channels.newChannel(new URL(url).openStream());
			FileOutputStream fileOutStream = new FileOutputStream(file);
			fileOutStream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			System.out.println("DONE Downloading!");
		}
		catch(IOException e) {e.getMessage();}
	}
}
	

