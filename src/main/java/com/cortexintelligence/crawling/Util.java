package com.cortexintelligence.crawling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Util {

	public static void printTxt(URL url) throws FileNotFoundException, IOException, URISyntaxException {
		printTxt(new File(url.toURI()));
	}
	
	public static void printTxt(File file) throws FileNotFoundException, IOException {
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);

		String line = br.readLine();
		while (line != null) {
			System.out.println(line);
			line = br.readLine();
		}
		br.close();
		reader.close();
	}
	
}
