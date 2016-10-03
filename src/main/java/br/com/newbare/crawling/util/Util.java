package br.com.newbare.crawling.util;

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
	
	public static boolean isPossuiArgumentos(String[] args){
		return args.length==0?false:true;
	}
	public static boolean isPossuiComandosValidos(String[] args){
		if(args.length>0)
			return !args[0].matches("-[fwg]")?true:false;
		else
			return true;
	}
}
